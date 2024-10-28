package com.easy_expense.expense_tracker_api.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {

    String generateToken(String userName);

    String extractUsername(String token);


    Date extractExpiration(String token);

    public Boolean validateToken(String token, UserDetails userDetails);

}
