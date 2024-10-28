package com.easy_expense.expense_tracker_api.controller;

import com.easy_expense.expense_tracker_api.dto.request.CategoryRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.CategoryResponseDto;
import com.easy_expense.expense_tracker_api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This Controller is for mapping categories
 *
 * @author SanthoshKumar D
 * @since 26 OCT, 2024
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * API for reading the categories
     *
     * @return category list
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    /**
     * API for reading the category by ID
     *
     * @param id
     * @return categoryResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    /**
     * API for creating the category
     *
     * @param categoryRequest
     * @return categoryResponse
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequest) {
        CategoryResponseDto categoryResponse = categoryService.saveCategory(categoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    /**
     * API for updating the category
     *
     * @param id
     * @param categoryRequestDto
     * @return categoryResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(id, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    /**
     * API for deleting the category
     *
     * @param id
     * @return successfully string
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Expense deleted successfully...", HttpStatus.OK);
    }
}
