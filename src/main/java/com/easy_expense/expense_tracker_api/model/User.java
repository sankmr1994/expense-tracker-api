package com.easy_expense.expense_tracker_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_users")
public class User extends BaseModel {

    private String name;

    @Column(unique = true)
    private String password;

    @Column(unique = true)
    private String email;

    private LocalDate dateOfBirth;

    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<UserRole> userRoles;

    public void addRole(UserRole role) {
        if (this.userRoles == null) {
            userRoles = new HashSet<>();
        }
        this.userRoles.add(role);
    }

}
