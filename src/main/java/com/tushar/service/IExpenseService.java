package com.tushar.service;

import java.util.List;

import com.tushar.entity.Expense;

public interface IExpenseService {
	
	public Expense createExpense(Expense expense, String tenantId);

    public List<Expense> getExpenses(String tenantId);
    
    public void checkExpenseLimit(String tenantId);


}
