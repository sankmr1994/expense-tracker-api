package com.easy_expense.expense_tracker_api.mapper;

import com.easy_expense.expense_tracker_api.dto.request.ExpenseRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper EXPENSE_MAPPER = Mappers.getMapper(ExpenseMapper.class);

    /**
     * Mapper method for converting from RequestDto object to Entity object
     *
     * @param expenseRequestDto
     * @return Expense
     */
    Expense mapRequestDtoToEntity(ExpenseRequestDto expenseRequestDto);


    /**
     * Mapper method for converting from Entity object to ResponseDto object
     *
     * @param expense
     * @return ExpenseResponseDto
     */
    @Mapping(target = "categoryResponseDto", source = "category")
    ExpenseResponseDto mapEntityToResponseDto(Expense expense);

    /**
     * Mapper method for converting from ResponseDto object to Entity object
     *
     * @param expenseResponseDto
     * @return Expense
     */
    Expense mapResponseDtoToEntity(ExpenseResponseDto expenseResponseDto);

}
