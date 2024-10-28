package com.easy_expense.expense_tracker_api.CustomException;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String errorMsg){
        super(errorMsg);
    }
}
