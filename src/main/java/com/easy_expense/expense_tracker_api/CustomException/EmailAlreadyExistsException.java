package com.easy_expense.expense_tracker_api.CustomException;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String error) {
        super(error);
    }
}
