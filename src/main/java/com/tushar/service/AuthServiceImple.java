package com.tushar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tushar.entity.User;
import com.tushar.repository.IUserRepository;
import com.tushar.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements IAuthService {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwttokenProvider;

	@Override
	public String login(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
		if(!passwordEncoder.matches(password, user.getPassword()))
			throw new RuntimeException("Invalied credentials");
		
		return jwttokenProvider.generateToken(user.getId(), user.getTenantId().getId());
	}

}
