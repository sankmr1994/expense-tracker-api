package com.easy_expense.expense_tracker_api.CustomException;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
