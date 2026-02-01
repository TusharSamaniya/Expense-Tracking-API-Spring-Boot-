package com.tushar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.Subscription;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {
	
	Optional<Subscription> findbyTenant_TenantAndStatus(String tenantId, String status);

}
