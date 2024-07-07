package com.induscollege.expense_manager.models;

import java.util.Date;
import java.util.Optional;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public static long capturedUserId;  //userid captured from requst, same for all expense operation per user;

    @NotNull
    private long userId;

    @NotNull
    private float amount;

    @NotNull
    private String name;
    
    private String description;

    @CreationTimestamp
    private Date createdAt;
    
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Category category;

    @PrePersist
    private void  setUserId(){
    this.setUserId(capturedUserId);
    }
    
    public void updateExpense(Expense that){
        this.setAmount(Optional.ofNullable(that.getAmount()).orElse(this.amount));
        this.setName(Optional.ofNullable(that.getName()).orElse(this.name));
        this.setDescription(Optional.ofNullable(that.getDescription()).orElse(this.description));
        this.setCategory(Optional.ofNullable(that.getCategory()).orElse(this.category));
    }
}
