package com.tushar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.Expense;

public interface IExpenseRepository extends JpaRepository<Expense, Long> {
	
	List<Expense> findByTenant_TenantId(String tenantId);
	int countByTenant_TenantId(String tenantId);

}
