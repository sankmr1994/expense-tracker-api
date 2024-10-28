package com.easy_expense.expense_tracker_api.security;

import com.easy_expense.expense_tracker_api.model.User;
import com.easy_expense.expense_tracker_api.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private final Long userId;

    private final String userName;

    private final String password;

    private final List<GrantedAuthority> grantedAuthorities;


    public UserInfoUserDetails(User user) {
        this.userId = user.getId();
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.grantedAuthorities = getRolesFromUser(user.getUserRoles());
    }

    public List<GrantedAuthority> getRolesFromUser(Set<UserRole> userRoles) {
        return userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getName())).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public Long userId() {
        return userId;
    }
}
