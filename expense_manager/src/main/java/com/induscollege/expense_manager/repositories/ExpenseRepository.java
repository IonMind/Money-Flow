package com.induscollege.expense_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.induscollege.expense_manager.models.Expense;

/**
 * ExpenseRepository
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

}