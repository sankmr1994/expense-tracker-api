package com.easy_expense.expense_tracker_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtTokenResponse {

    private final String jwtToken;

}
