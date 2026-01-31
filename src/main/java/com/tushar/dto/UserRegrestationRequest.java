package com.tushar.dto;

import com.tushar.tenent.TenantType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegrestationRequest {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String tenantName;
	
	@NotBlank
	private TenantType tenantType;

}
