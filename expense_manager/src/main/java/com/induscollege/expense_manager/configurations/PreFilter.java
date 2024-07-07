package com.induscollege.expense_manager.configurations;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.induscollege.expense_manager.models.Expense;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PreFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {
        Expense.capturedUserId = Optional.ofNullable(Long.parseLong(request.getHeader("userId")))
                .orElseThrow(() -> new NoSuchElementException("(userId) header is requireds"));
        filterChain.doFilter(request, response);
    }

}
