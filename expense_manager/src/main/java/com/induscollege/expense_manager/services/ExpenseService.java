package com.induscollege.expense_manager.services;

import com.induscollege.expense_manager.models.Expense;

public interface ExpenseService {
    public Expense addExpense(Expense expense);

    public void deleteExpense(long id);

    public Expense updateExpense(long id,Expense expense);

    public Expense getExpense(long id);
    
}
