package com.easy_expense.expense_tracker_api.service;

import com.easy_expense.expense_tracker_api.dto.request.UserRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.UserResponseDto;
import com.easy_expense.expense_tracker_api.model.User;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser();

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUserById();

    User getLoggedInUser();

}
