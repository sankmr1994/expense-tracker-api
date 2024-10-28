package com.easy_expense.expense_tracker_api.controller;

import com.easy_expense.expense_tracker_api.dto.Login;
import com.easy_expense.expense_tracker_api.dto.request.UserRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.JwtTokenResponse;
import com.easy_expense.expense_tracker_api.dto.response.UserResponseDto;
import com.easy_expense.expense_tracker_api.security.UserInfoUserDetailsService;
import com.easy_expense.expense_tracker_api.service.JwtService;
import com.easy_expense.expense_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserInfoUserDetailsService userDetailsService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService, UserInfoUserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody Login login) throws Exception {
        authenticate(login.getUserName(), login.getPassword());
        String token = jwtService.generateToken(login.getUserName());
        return new ResponseEntity<>(new JwtTokenResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException disabledException) {
            throw new Exception("User Disabled");
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Invalid user Request!");
        }
    }

}
