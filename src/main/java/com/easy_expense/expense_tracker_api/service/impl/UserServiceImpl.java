package com.easy_expense.expense_tracker_api.service.impl;

import com.easy_expense.expense_tracker_api.CustomException.EmailAlreadyExistsException;
import com.easy_expense.expense_tracker_api.dto.request.UserRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.ExpenseResponseDto;
import com.easy_expense.expense_tracker_api.dto.response.UserResponseDto;
import com.easy_expense.expense_tracker_api.model.User;
import com.easy_expense.expense_tracker_api.model.UserRole;
import com.easy_expense.expense_tracker_api.repository.RoleRepository;
import com.easy_expense.expense_tracker_api.repository.UserRepository;
import com.easy_expense.expense_tracker_api.security.UserInfoUserDetails;
import com.easy_expense.expense_tracker_api.service.UserService;
import com.easy_expense.expense_tracker_api.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        validateEmail(userRequestDto.getEmail());
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        user.setAge(DateUtils.calculateAge(userRequestDto.getDateOfBirth()));

        //Encode password
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        //Add default role to user
        UserRole userRole = roleRepository.findByName("ROLE_USER");
        user.addRole(userRole);


        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser() {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userInfoUserDetails.userId());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not found for id : " + userInfoUserDetails.userId());
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user.get(), userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        UserResponseDto oldUser = getUser();
        User user = new User();
        oldUser.setName(userRequestDto.getName() != null ? userRequestDto.getName() : oldUser.getName());
        oldUser.setPassword(userRequestDto.getPassword() != null ? passwordEncoder.encode(userRequestDto.getPassword()) : oldUser.getPassword());
        oldUser.setEmail(userRequestDto.getEmail() != null ? userRequestDto.getEmail() : oldUser.getEmail());
        oldUser.setDateOfBirth(userRequestDto.getDateOfBirth() != null ? userRequestDto.getDateOfBirth() : oldUser.getDateOfBirth());
        if (userRequestDto.getDateOfBirth() != null) {
            oldUser.setAge(DateUtils.calculateAge(userRequestDto.getDateOfBirth()));
        }
        BeanUtils.copyProperties(oldUser, user);
        userRepository.save(user);
        return oldUser;
    }

    @Override
    public void deleteUserById() {
        UserResponseDto userResponseDto = getUser();
        userRepository.deleteById(userResponseDto.getId());
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not found for id : " + authentication.getName());
        }
        return user.get();
    }

    public void validateEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
    }
}
