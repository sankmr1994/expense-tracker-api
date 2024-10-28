package com.easy_expense.expense_tracker_api.CustomException;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
