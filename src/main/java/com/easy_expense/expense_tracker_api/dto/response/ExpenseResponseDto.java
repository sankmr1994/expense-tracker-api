package com.easy_expense.expense_tracker_api.dto.response;

import com.easy_expense.expense_tracker_api.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal amount;

    private CategoryResponseDto categoryResponseDto;

    private LocalDateTime expenseDate;

    @JsonIgnore
    private User user;
}
