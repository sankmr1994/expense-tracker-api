package com.easy_expense.expense_tracker_api.dto.response;

import com.easy_expense.expense_tracker_api.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long id;

    private String name;

    private String description;

    private String icon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
