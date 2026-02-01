package com.tushar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tushar.entity.Expense;
import com.tushar.entity.Tenant;
import com.tushar.repository.IExpenseRepository;
import com.tushar.repository.ITenantRepository;

public class ExpenseServiceImple implements IExpenseService {
	
	@Autowired
	private IExpenseRepository expenseRepository;
	@Autowired
	private ITenantRepository tenantRepository;

	@Override
	public Expense createExpense(Expense expense, String tenantId) {
		Tenant tenant = tenantRepository.findByTenantId(tenantId);
		expense.setTenant(tenant);
		return expenseRepository.save(expense);
	}

	@Override
	public List<Expense> getExpenses(String tenantId) {
		return expenseRepository.findByTenant_TenantId(tenantId);
	}

}
