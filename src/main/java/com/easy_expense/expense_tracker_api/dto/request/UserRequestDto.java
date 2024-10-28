package com.easy_expense.expense_tracker_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull(message = "User name must not be null.")
    @NotBlank(message = "User name must not be blank.")
    @Size(min = 3, message = "User name must be at least 3 characters.")
    private String name;

    @NotNull(message = "Password name must not be null.")
    @NotBlank(message = "Password name must not be blank.")
    @Size(min = 5, message = "Password must be at least 5 characters.")
    private String password;

    @NotNull(message = "User email must not be null.")
    @NotBlank(message = "User email must not be blank.")
    @Email(message = "Please enter valid mail.")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth must be past date")
    private LocalDate dateOfBirth;

    private Integer age;

}
