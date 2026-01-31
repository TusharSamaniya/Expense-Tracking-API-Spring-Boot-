package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.dto.UserRegrestationRequest;
import com.tushar.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserRegrestationRequest request){
		userService.register(request);
		return ResponseEntity.ok("User registered successfully");
	}
}
