package com.easy_expense.expense_tracker_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ExpenseRequestDto {

    @NotNull(message = "Expense name must not be null.")
    @NotBlank(message = "Expense name must not be blank.")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;

    private String description;

    @NotNull(message = "Expense amount must not be null.")
    private BigDecimal amount;

    @NotNull(message = "Expense category must not be null.")
    private Long categoryId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Expense date must not be null.")
    private LocalDateTime expenseDate;

}
