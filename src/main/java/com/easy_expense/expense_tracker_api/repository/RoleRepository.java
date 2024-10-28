package com.easy_expense.expense_tracker_api.repository;

import com.easy_expense.expense_tracker_api.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findByName(String name);
}
