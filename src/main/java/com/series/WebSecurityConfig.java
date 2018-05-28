package com.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.series.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//		TODO ignore assets here...
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
//				anyone
				.antMatchers(HttpMethod.GET, "/series").permitAll()
				.antMatchers(HttpMethod.GET, "/users").permitAll()
//				.antMatchers("/", "/signup").permitAll()
//				.antMatchers(HttpMethod.POST, "/users").permitAll()
//				.antMatchers(HttpMethod.GET, "/series").permitAll()
//				.antMatchers(HttpMethod.GET, "/series/{^[\\d]$}").permitAll()
//				.antMatchers(HttpMethod.POST, "/series").permitAll()
//				
//				.antMatchers(HttpMethod.POST, "/series").permitAll()
//
////				only authenticated
				.antMatchers("/hello").authenticated()
//				.antMatchers("/users/**").authenticated()
//				
////				only registered user
////				--- leaving comments or rate a series, for example
//			    
////				only site administrator
//				.antMatchers("/series/**").hasRole("ADMIN")
//				.antMatchers("/genre/**").hasRole("ADMIN")
				
				.anyRequest().permitAll()
				
				.and()
//			
			.formLogin()
				.loginPage("/login")
//				logout page
				.permitAll();
////				.successHandler(loginSuccessHandler())
////				.failureHandler(loginFailureHandler())
	}

//	public AuthenticationSuccessHandler loginSuccessHandler() {
//		return (request, response, authentication) -> response.sendRedirect("/");
//	}
	
//	public AuthenticationFailureHandler loginFailureHandler() {
//		return (request, response, authentication) -> {
//			response.sendRedirect("/login");
//		};
//	}
	
	@Bean
	public EvaluationContextExtension securityExtension() {
		return new EvaluationContextExtensionSupport() {
			
			@Override
			public String getExtensionId() {
				return "security";
			}
			
			@Override
			public Object getRootObject() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				return new SecurityExpressionRoot(authentication) {};
			}
		};
	}
	
}
