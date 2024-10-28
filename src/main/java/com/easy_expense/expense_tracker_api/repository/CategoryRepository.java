package com.easy_expense.expense_tracker_api.repository;

import com.easy_expense.expense_tracker_api.model.Category;
import com.easy_expense.expense_tracker_api.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
