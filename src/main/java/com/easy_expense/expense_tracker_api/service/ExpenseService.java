package com.easy_expense.expense_tracker_api.service;

import com.easy_expense.expense_tracker_api.dto.request.ExpenseRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * This Service interface for managing expenses
 *
 * @author SanthoshKumar D
 * @since 26 OCT, 2024
 */
public interface ExpenseService {

    /**
     * This for reading all the expense from the database
     *
     * @return list of ExpenseResponseDto
     */
    List<ExpenseResponseDto> getAllExpenses(Pageable page);

    /**
     * This for read expense by ID from the database
     *
     * @param id
     * @return ExpenseResponseDto
     */
    ExpenseResponseDto getExpenseById(Long id);


    /**
     * This for creating new expense from the database
     *
     * @param expenseRequestDto
     * @return ExpenseResponseDto
     */
    ExpenseResponseDto saveExpense(ExpenseRequestDto expenseRequestDto);

    /**
     * This for updating existing expense from the database
     *
     * @param id
     * @param expenseRequestDto
     * @return ExpenseResponseDto
     */
    ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto expenseRequestDto);

    /**
     * This for deleting the expense from the database
     *
     * @param id
     */
    void deleteExpenseById(Long id);

    /**
     * This for read expense by name from the database
     *
     * @param keyword
     * @param page
     * @return list of ExpenseResponseDto
     */
    List<ExpenseResponseDto> findByName(String keyword, Pageable page);

    /**
     * This for read expense by category from the database
     *
     * @param category
     * @param page
     * @return list of ExpenseResponseDto
     */
    List<ExpenseResponseDto> findByCategory(String category, Pageable page);

    /**
     * This for read expense by startDate and endDate from the database
     *
     * @param startDate
     * @param endDate
     * @param page
     * @return list of ExpenseResponseDto
     */
    List<ExpenseResponseDto> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable page);
}
