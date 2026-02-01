package com.tushar.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tushar.dto.UserRegrestationRequest;
import com.tushar.entity.Tenant;
import com.tushar.entity.User;
import com.tushar.repository.ITenantRepository;
import com.tushar.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ITenantRepository tenantRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(UserRegrestationRequest request) {
		
		Tenant tenant = Tenant.builder()
				.name(request.getTenantName())
				.type(request.getTenantType())
				.createdAt(LocalDateTime.now())
				.build();
		tenantRepository.save(tenant);
		
		User user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.tenantId(tenant)
				.build();
		
		userRepository.save(user);
	}

}
