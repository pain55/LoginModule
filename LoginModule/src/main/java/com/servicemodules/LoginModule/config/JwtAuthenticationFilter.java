package com.servicemodules.LoginModule.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.servicemodules.LoginModule.service.impl.JwtUtil;
import com.servicemodules.LoginModule.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//this is a very important class in jwt authentication
// this class gets executed when ever a request is fired
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired 
	private JwtUtil jwtUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		requestTokenHeader stores jwt token present every request header named "Authorization"
		final String requestTokenHeader = request.getHeader("Authorization");
		
		System.out.println("This is header-> "+requestTokenHeader);
		
		String username =null;
		String jwtToken = null;
		
		
//		In http 1.0 w3 standards every authorization header must start with Bearer
//		In some applications like OAuth 2.0 have type defined in request thats why we keeping the Bearer in start of the token
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			
			try {
				jwtToken = requestTokenHeader.substring(7);
				username = this.jwtUtil.extractUsername(jwtToken);
			}
			catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Jwt token has expired");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Invalid!!, Token doesn't start with Bearer");
		}
		
//		validating the token whether he authorized or not
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);;
			}
			
		}
		else {
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}

}
