package com.easy_expense.expense_tracker_api.controller;

import com.easy_expense.expense_tracker_api.dto.request.UserRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.UserResponseDto;
import com.easy_expense.expense_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUser() {
        UserResponseDto userResponseDto = userService.getUser();
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser() {
        userService.deleteUserById();
        return new ResponseEntity<>("User deleted successfully...", HttpStatus.OK);
    }
}
