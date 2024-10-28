package com.easy_expense.expense_tracker_api.service.impl;

import com.easy_expense.expense_tracker_api.CustomException.ExpenseNotFoundException;
import com.easy_expense.expense_tracker_api.dto.request.ExpenseRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.mapper.ExpenseMapper;
import com.easy_expense.expense_tracker_api.model.Category;
import com.easy_expense.expense_tracker_api.model.Expense;
import com.easy_expense.expense_tracker_api.model.User;
import com.easy_expense.expense_tracker_api.repository.ExpenseRepository;
import com.easy_expense.expense_tracker_api.security.UserInfoUserDetails;
import com.easy_expense.expense_tracker_api.service.CategoryService;
import com.easy_expense.expense_tracker_api.service.ExpenseService;
import com.easy_expense.expense_tracker_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CategoryService categoryService;

    private final UserService userService;

    private final ExpenseMapper expenseMapper;


    @Override
    public List<ExpenseResponseDto> getAllExpenses(Pageable page) {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Expense> expenses = expenseRepository.findByUserId(userInfoUserDetails.userId(), page).toList();
        return expenses.stream().map(expenseMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public ExpenseResponseDto getExpenseById(Long id) {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userInfoUserDetails.userId(), id);
        if (expense.isEmpty()) {
            throw new ExpenseNotFoundException("Expense Not found for id : " + id);
        }
        return expenseMapper.mapEntityToResponseDto(expense.get());
    }

    @Override
    public ExpenseResponseDto saveExpense(ExpenseRequestDto expenseRequestDto) {
        Expense expense = expenseMapper.mapRequestDtoToEntity(expenseRequestDto);
        User user = userService.getLoggedInUser();
        expense.setUser(user);
        Category category = categoryService.getCategoryEntityById(expenseRequestDto.getCategoryId());
        expense.setCategory(category);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.mapEntityToResponseDto(savedExpense);
    }

    @Override
    public ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto expenseRequestDto) {
        ExpenseResponseDto existingExpenseResponseDto = getExpenseById(id);
        existingExpenseResponseDto.setId(existingExpenseResponseDto.getId());
        existingExpenseResponseDto.setName(expenseRequestDto.getName() != null ? expenseRequestDto.getName() : existingExpenseResponseDto.getName());
        existingExpenseResponseDto.setDescription(expenseRequestDto.getDescription() != null ? expenseRequestDto.getDescription() : existingExpenseResponseDto.getDescription());
        existingExpenseResponseDto.setAmount(expenseRequestDto.getAmount() != null ? expenseRequestDto.getAmount() : existingExpenseResponseDto.getAmount());
        Expense expense = expenseMapper.mapResponseDtoToEntity(existingExpenseResponseDto);
        if (expenseRequestDto.getCategoryId() != null) {
            expense.setCategory(categoryService.getCategoryEntityById(expenseRequestDto.getCategoryId()));
        } else {
            expense.setCategory(categoryService.getCategoryEntityById(existingExpenseResponseDto.getCategoryResponseDto().getId()));
        }
        Expense updatedExpense = expenseRepository.save(expense);
        return expenseMapper.mapEntityToResponseDto(updatedExpense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        ExpenseResponseDto expenseResponseDto = getExpenseById(id);
        expenseRepository.deleteById(expenseResponseDto.getId());
    }

    @Override
    public List<ExpenseResponseDto> findByName(String keyword, Pageable page) {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return expenseRepository.findByUserIdAndNameContaining(userInfoUserDetails.userId(), keyword, page).stream().map(expenseMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponseDto> findByCategory(String category, Pageable page) {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return expenseRepository.findByUserIdAndCategoryName(userInfoUserDetails.userId(), category, page).stream().map(expenseMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponseDto> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable page) {
        if (startDate == null) {
            startDate = LocalDate.of(2021, Month.SEPTEMBER, 10);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();
        endDateTime = endDateTime.minusMinutes(1);

        System.out.println(startDateTime + " ========= " + endDateTime);
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return expenseRepository.findByUserIdAndExpenseDateBetween(userInfoUserDetails.userId(), startDateTime, endDateTime, page).stream().map(expenseMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }


}
