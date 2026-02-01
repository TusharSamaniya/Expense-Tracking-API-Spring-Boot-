package com.tushar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.entity.Expense;
import com.tushar.entity.Subscription;
import com.tushar.entity.Tenant;
import com.tushar.repository.IExpenseRepository;
import com.tushar.repository.ISubscriptionRepository;
import com.tushar.repository.ITenantRepository;

@Service
public class ExpenseServiceImple implements IExpenseService {
	
	@Autowired
	private IExpenseRepository expenseRepository;
	
	@Autowired
	private ISubscriptionRepository subscriptionRepository;
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


	public void checkExpenseLimit(String tenantId) {

        Subscription sub = subscriptionRepository.findbyTenant_TenantAndStatus(tenantId, "ACTIVE")
        		.orElseThrow(() -> new RuntimeException("No active subscription"));
        int used = expenseRepository.countByTenant_TenantId(tenantId);
        
        if(used >= sub.getPlan().getExpenseLimit()) {
        	throw new RuntimeException("Plan limit exceeded. Upgrade required.");
        }
        
    }
	
	public Expense createExpense(Expense expense) {
		checkExpenseLimit(expense.getTenant().getTenantId());
		return expenseRepository.save(expense);
	}

}
