package com.induscollege.expense_manager.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.induscollege.expense_manager.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    public Optional<Category> findByName(String categoryName);
}
