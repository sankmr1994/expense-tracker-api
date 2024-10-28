package com.easy_expense.expense_tracker_api.CustomException;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String errorMsg) {
        super(errorMsg);
    }
}
