package com.induscollege.expense_manager.services;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.induscollege.expense_manager.models.Expense;
import com.induscollege.expense_manager.repositories.ExpenseRepository;

import jakarta.transaction.Transactional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    @Transactional
    public Expense addExpense(Expense expense) {
        expense.setCategory(categoryService
        .addCategory(expense.getCategory() == null ? "others" : expense.getCategory().getName()));
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(long id) {
        expenseRepository.delete(this.findById(id));
    }

    @Override
    public Expense updateExpense(long id, Expense expense) {
        var savedExpense = this.findById(id);
        savedExpense.updateExpense(expense);
        return this.findById(savedExpense.getId());
    }

    private Expense findById(long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("no course associated to student with id : " + id));
    }

    @Override
    public Expense getExpense(long id) {
        return this.findById(id);
    }

}
