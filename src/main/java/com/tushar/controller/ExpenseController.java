package com.tushar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.entity.Expense;
import com.tushar.service.IExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	
	@Autowired
	private IExpenseService expenseService;
	
	@PostMapping
	public Expense addExpense(@RequestBody Expense expense, @RequestHeader("X-TENANT-ID") String tenantId) {
		return expenseService.createExpense(expense, tenantId);
	}
	
	@GetMapping
	public List<Expense> getExpense(@RequestHeader("X-TENANT-ID") String tenantId){
		return expenseService.getExpenses(tenantId);
	}

}
