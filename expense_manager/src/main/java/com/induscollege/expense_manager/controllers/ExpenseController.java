package com.induscollege.expense_manager.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.induscollege.expense_manager.models.Expense;
import com.induscollege.expense_manager.services.ExpenseService;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok().body(expenseService.addExpense(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable long id, @RequestBody Expense expense) {
        return ResponseEntity.ok().body(expenseService.updateExpense(id,expense));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable long id) {
        return ResponseEntity.ok().body(expenseService.getExpense(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Delted");
    }

}
