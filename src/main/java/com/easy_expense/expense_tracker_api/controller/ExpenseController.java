package com.easy_expense.expense_tracker_api.controller;

import com.easy_expense.expense_tracker_api.dto.request.ExpenseRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


/**
 * This Controller is for mapping expense
 *
 * @author SanthoshKumar D
 * @since 26 OCT, 2024
 */
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * API for reading the expenses
     *
     * @return expense list
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpense(Pageable page) {
        return new ResponseEntity<>(expenseService.getAllExpenses(page), HttpStatus.OK);
    }

    /**
     * API for reading the expense by ID
     *
     * @param id
     * @return expenseResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDto expenseResponseDto = expenseService.getExpenseById(id);
        return new ResponseEntity<>(expenseResponseDto, HttpStatus.OK);
    }

    /**
     * API for creating the expense
     *
     * @param expenseRequestDto
     * @return ExpenseResponseDto
     */
    @PostMapping
    public ResponseEntity<ExpenseResponseDto> saveExpense(@Valid @RequestBody ExpenseRequestDto expenseRequestDto) {
        ExpenseResponseDto expenseResponseDto = expenseService.saveExpense(expenseRequestDto);
        return new ResponseEntity<>(expenseResponseDto, HttpStatus.CREATED);
    }

    /**
     * API for updating the expense
     *
     * @param id
     * @param expenseRequestDto
     * @return expenseResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequestDto expenseRequestDto) {
        ExpenseResponseDto expenseResponseDto = expenseService.updateExpense(id, expenseRequestDto);
        return new ResponseEntity<>(expenseResponseDto, HttpStatus.OK);
    }

    /**
     * API for deleting the expense
     *
     * @param id
     * @return successfully string
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return new ResponseEntity<>("Expense deleted successfully...", HttpStatus.OK);
    }

    /**
     * API for reading the expense by name
     *
     * @param keyword
     * @param page
     * @return expenseResponseDto
     */
    @GetMapping("/name")
    public ResponseEntity<List<ExpenseResponseDto>> getExpenseByName(@RequestParam String keyword, Pageable page) {
        List<ExpenseResponseDto> expenseResponseDtoList = expenseService.findByName(keyword, page);
        return new ResponseEntity<>(expenseResponseDtoList, HttpStatus.OK);
    }

    /**
     * API for reading the expense by category
     *
     * @param category
     * @param page
     * @return expenseResponseDto
     */
    @GetMapping("/category")
    public ResponseEntity<List<ExpenseResponseDto>> getExpenseByCategory(@RequestParam String category, Pageable page) {
        List<ExpenseResponseDto> expenseResponseDtoList = expenseService.findByCategory(category, page);
        return new ResponseEntity<>(expenseResponseDtoList, HttpStatus.OK);
    }

    /**
     * API for reading the expense by startDate and endDate
     *
     * @param startDate
     * @param endDate
     * @param page
     * @return expenseResponseDto
     */
    @GetMapping("/date")
    public ResponseEntity<List<ExpenseResponseDto>> getExpenseByDate(@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate, Pageable page) {
        List<ExpenseResponseDto> expenseResponseDtoList = expenseService.findByDateBetween(startDate, endDate, page);
        return new ResponseEntity<>(expenseResponseDtoList, HttpStatus.OK);
    }
}
