package com.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.series.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		.passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}
			
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
		});
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//		ignore assets here...
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/signup").permitAll()
				.antMatchers(HttpMethod.GET, "/series").permitAll()
				.antMatchers(HttpMethod.GET, "/series/{^[\\d]$}").permitAll()
				.antMatchers("/series/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
//				.successHandler(loginSuccessHandler())
//				.failureHandler(loginFailureHandler());
	}

//	public AuthenticationSuccessHandler loginSuccessHandler() {
//		return (request, response, authentication) -> response.sendRedirect("/");
//	}
	
//	public AuthenticationFailureHandler loginFailureHandler() {
//		return (request, response, authentication) -> {
//			response.sendRedirect("/login");
//		};
//	}
	
}
