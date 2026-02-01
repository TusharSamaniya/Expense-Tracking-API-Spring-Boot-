package com.tushar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tushar.entity.Expense;
import com.tushar.entity.Subscription;
import com.tushar.entity.Tenant;
import com.tushar.repository.IExpenseRepository;
import com.tushar.repository.ISubscriptionRepository;

public class ExpenseServiceImple implements IExpenseService {
	
	@Autowired
	private IExpenseRepository expenseRepository;
	
	@Autowired
	private ISubscriptionRepository subscriptionRepository;

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


	private void checkExpenseLimit(String tenantId) {

        Subscription sub = subscriptionRepository
                .findByTenant_TenantIdAndStatus(tenantId, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("No active subscription"));

        int used = expenseRepository.countByTenant_TenantId(tenantId);

        if (used >= sub.getPlan().getExpenseLimit()) {
            throw new RuntimeException("Plan limit exceeded. Upgrade required.");
        }
    }

}
