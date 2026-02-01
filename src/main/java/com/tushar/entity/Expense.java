package com.tushar.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private String category;
	private String description;
	private LocalDate expenseDate;
	private String merchant;
	private String receiptUrl;
	
	@ManyToOne
	@JoinColumn(name = "user_id_fk")
	private User createdBy;
	
	@ManyToOne
	@JoinColumn(name = "tenant_id_fk")
	private Tenant tenant;
	private LocalDateTime createdAt = LocalDateTime.now();

}
