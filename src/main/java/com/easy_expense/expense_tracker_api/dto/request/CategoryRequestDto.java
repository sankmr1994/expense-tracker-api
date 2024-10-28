package com.easy_expense.expense_tracker_api.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDto {

    private String name;

    private String description;

    private String icon;
}
