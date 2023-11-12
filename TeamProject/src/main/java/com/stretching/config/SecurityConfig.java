package com.stretching.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.stretching.role.UserRole;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/mypage").authenticated()
			.requestMatchers("/mypage/**").authenticated()
			.requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.usernameParameter("loginId")
			.passwordParameter("password")
			.loginPage("/signin")
			.defaultSuccessUrl("/")
			.failureUrl("/signin")
		.and()
			.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true).deleteCookies("JSESSIONID")
		.and()
			.exceptionHandling()
			.authenticationEntryPoint((AuthenticationEntryPoint) new AuthenticationEntryPoint() {
				@Override
	        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	            response.sendRedirect("/authentication_fail");

	        }
	    })
	    .accessDeniedHandler((AccessDeniedHandler) new AccessDeniedHandler() {
	        @Override
	        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
	            response.sendRedirect("/authorization_fail");

	        }
	    });
		
			
		return http.build();
			
	}
}
