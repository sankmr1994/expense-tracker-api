package com.easy_expense.expense_tracker_api;

import java.time.*;

public class Demo {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDate.of(1994, Month.JANUARY, 19).atStartOfDay();
        System.out.println(dateTime);
    }
}
