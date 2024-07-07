package com.induscollege.expense_manager.services;

import com.induscollege.expense_manager.models.Category;


public interface CategoryService {
    public Category addCategory(String categoryName);
    // public Category addExpense(Expense... expenses);
    public void deleteCategory(long id);
    
    public void updateCategory(long id);
}
