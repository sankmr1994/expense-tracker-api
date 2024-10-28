package com.easy_expense.expense_tracker_api.security;

import com.easy_expense.expense_tracker_api.model.User;
import com.easy_expense.expense_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        return userOptional.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found : " + userEmail));
    }
}
