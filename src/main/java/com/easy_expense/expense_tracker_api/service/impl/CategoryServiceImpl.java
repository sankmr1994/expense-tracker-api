package com.easy_expense.expense_tracker_api.service.impl;

import com.easy_expense.expense_tracker_api.CustomException.CategoryAlreadyExistsException;
import com.easy_expense.expense_tracker_api.CustomException.CategoryNotFoundException;
import com.easy_expense.expense_tracker_api.dto.request.CategoryRequestDto;
import com.easy_expense.expense_tracker_api.dto.response.CategoryResponseDto;
import com.easy_expense.expense_tracker_api.mapper.CategoryMapper;
import com.easy_expense.expense_tracker_api.model.Category;
import com.easy_expense.expense_tracker_api.model.User;
import com.easy_expense.expense_tracker_api.repository.CategoryRepository;
import com.easy_expense.expense_tracker_api.security.UserInfoUserDetails;
import com.easy_expense.expense_tracker_api.service.CategoryService;
import com.easy_expense.expense_tracker_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserService userService;

    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryResponseDto> getAllCategories() {
        UserInfoUserDetails userInfoUserDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return categoryRepository.findAll()
                .stream().map(categoryMapper::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category Not found for id : " + id);
        }
        return categoryMapper.mapEntityToResponseDto(category.get());
    }

    public Category getCategoryEntityById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category Not found for id : " + id);
        }
        return category.get();
    }

    @Override
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequest) {
        boolean isExists = categoryRepository.existsByName(categoryRequest.getName());
        if (isExists) {
            throw new CategoryAlreadyExistsException("Category already exists for the name : " + categoryRequest.getName());
        }
        Category category = categoryMapper.mapRequestDtoToEntity(categoryRequest);
        User user = userService.getLoggedInUser();
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapEntityToResponseDto(savedCategory);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto existingCategoryResponseDto = getCategoryById(id);
        existingCategoryResponseDto.setId(existingCategoryResponseDto.getId());
        existingCategoryResponseDto.setName(categoryRequestDto.getName() != null ? categoryRequestDto.getName() : existingCategoryResponseDto.getName());
        existingCategoryResponseDto.setDescription(categoryRequestDto.getDescription() != null ? categoryRequestDto.getDescription() : existingCategoryResponseDto.getDescription());
        existingCategoryResponseDto.setIcon(categoryRequestDto.getIcon() != null ? categoryRequestDto.getIcon() : existingCategoryResponseDto.getIcon());
        Category category = categoryMapper.mapResponseDtoToEntity(existingCategoryResponseDto);
        category.setId(existingCategoryResponseDto.getId());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.mapEntityToResponseDto(updatedCategory);
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        CategoryResponseDto categoryResponseDto = getCategoryById(id);
        categoryRepository.deleteById(categoryResponseDto.getId());
    }


}
