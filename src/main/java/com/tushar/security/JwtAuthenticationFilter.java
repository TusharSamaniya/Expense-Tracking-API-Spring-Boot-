package com.tushar.security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tushar.tenent.TenantContext;

import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthenticationFilter implements Filter{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest http = (HttpServletRequest) request;
		String header = http.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer")) {
			String token = header.substring(7);
			Claims claims = tokenProvider.getClaims(token);
			
			Long tenantId = claims.get("tenantId", Long.class);
			TenantContext.setTenantId(tenantId);
		}
		
		try {
			chain.doFilter(request, response);
		}finally {
			TenantContext.clear();
		}
		
	}
}
