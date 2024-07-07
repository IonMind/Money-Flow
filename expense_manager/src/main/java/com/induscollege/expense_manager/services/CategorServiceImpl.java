package com.induscollege.expense_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.induscollege.expense_manager.models.Category;

import com.induscollege.expense_manager.repositories.CategoryRepository;



@Service
public class CategorServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(long id) {

    }

    @Override
    public Category addCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseGet(() -> categoryRepository.save(categoryRepository
                .save(Category.builder().name(categoryName).build())));
    }

}
