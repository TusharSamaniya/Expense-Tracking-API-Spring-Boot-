package com.tushar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.Tenant;

public interface ITenantRepository extends JpaRepository<Tenant, Long> {
	
	Tenant findByTenantId(String tenantId);

}
