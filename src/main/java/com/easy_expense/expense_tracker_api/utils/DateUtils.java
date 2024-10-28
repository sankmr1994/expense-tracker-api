package com.easy_expense.expense_tracker_api.utils;

import java.time.LocalDate;
import java.time.Period;

public interface DateUtils {
    static Integer calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
