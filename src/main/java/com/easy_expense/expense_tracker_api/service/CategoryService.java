package com.easy_expense.expense_tracker_api.service;

import com.easy_expense.expense_tracker_api.dto.request.CategoryRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.CategoryResponseDto;
import com.easy_expense.expense_tracker_api.model.Category;

import java.util.List;


/**
 * This Service interface for managing categories
 *
 * @author SanthoshKumar D
 * @since 26 OCT, 2024
 */
public interface CategoryService {

    /**
     * This for reading all the categories from the database
     *
     * @return list of CategoryResponseDto
     */
    List<CategoryResponseDto> getAllCategories();

    /**
     * This for read category by ID
     *
     * @param id
     * @return CategoryResponseDto
     */
    CategoryResponseDto getCategoryById(Long id);

    /**
     * This for read category by ID
     *
     * @param id
     * @return Category
     */
    Category getCategoryEntityById(Long id);

    /**
     * This for creating new category
     *
     * @param categoryRequest
     * @return CategoryResponseDto
     */
    CategoryResponseDto saveCategory(CategoryRequestDto categoryRequest);


    /**
     * This for updating existing category
     *
     * @param id
     * @param categoryRequestDto
     * @return CategoryResponseDto
     */
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    /**
     * This for deleting the category
     *
     * @param id
     */
    void deleteCategoryById(Long id);

}
