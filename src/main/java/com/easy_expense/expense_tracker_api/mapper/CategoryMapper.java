package com.easy_expense.expense_tracker_api.mapper;

import com.easy_expense.expense_tracker_api.dto.request.CategoryRequestDto;
import com.easy_expense.expense_tracker_api.dto.request.ExpenseRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.CategoryResponseDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.model.Category;
import com.easy_expense.expense_tracker_api.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    /**
     * Mapper method for converting from RequestDto object to Entity object
     *
     * @param categoryRequestDto
     * @return Category
     */
    Category mapRequestDtoToEntity(CategoryRequestDto categoryRequestDto);

    /**
     * Mapper method for converting from Entity object to ResponseDto object
     *
     * @param category
     * @return CategoryResponseDto
     */
    CategoryResponseDto mapEntityToResponseDto(Category category);


    /**
     * Mapper method for converting from ResponseDto object to Entity object
     *
     * @param existingCategoryResponseDto
     * @return Category
     */
    Category mapResponseDtoToEntity(CategoryResponseDto existingCategoryResponseDto);

}
