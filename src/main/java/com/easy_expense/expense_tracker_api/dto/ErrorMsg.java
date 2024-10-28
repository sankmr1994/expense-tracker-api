package com.easy_expense.expense_tracker_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMsg {

    private Integer statusCode;

    private String error;

    private LocalDateTime timestamp;
}
