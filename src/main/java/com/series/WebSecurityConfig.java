package com.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.series.config.JwtAuthenticationEntryPoint;
import com.series.config.JwtAuthenticationFilter;
import com.series.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
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
			
//				login
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				
//				series
//				.antMatchers(HttpMethod.GET, "/series").permitAll()
//				.antMatchers(HttpMethod.GET, "/series/{^[\\d]$}").permitAll()
//				.antMatchers(HttpMethod.POST, "/series/{^[\\d]$}").hasRole("ADMIN")
//				.antMatchers(HttpMethod.PUT, "/series/{^[\\d]$}").hasRole("ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/series/{^[\\d]$}").permitAll()
				.antMatchers("/series/**").permitAll()
				
//				comments
				.antMatchers(HttpMethod.GET, "/series/{^[\\d]$}/comments").permitAll()
				.antMatchers(HttpMethod.POST, "/series/{^[\\d]$}/comments").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/series/{^[\\d]$}/comments/{^[\\d]$}").hasRole("ADMIN")
				
//				ratings
				.antMatchers(HttpMethod.GET, "/series/{^[\\d]$}/ratigns").permitAll() // or should it be permitted for logged in users only
				
//				users
				.antMatchers(HttpMethod.POST, "/users").permitAll()
//				.antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users").permitAll()
				.antMatchers(HttpMethod.DELETE, "/users/{^[\\d]$}").permitAll()
//				.antMatchers("/users/**").authenticated()
				
////			watchlist
				.antMatchers(HttpMethod.GET, "/users/{^[\\d]$}/watchlist").permitAll()
				.antMatchers(HttpMethod.POST, "/users/{^[\\d]$}/watchlist/{^[\\d]$}").permitAll()
//				.antMatchers(HttpMethod.DELETE, "/users/{^[\\d]$}/watchlist/{^[]$}").permitAll()
//				
				.antMatchers(HttpMethod.GET, "/watchlist").permitAll()
				
////			history
				.antMatchers(HttpMethod.GET, "/users/{^[\\d]$}/watchlist").permitAll()
				.antMatchers(HttpMethod.POST, "/users/{^[\\d]$}/watchlist/{^[\\d]$}").permitAll()
//				.antMatchers(HttpMethod.DELETE, "/users/{^[]$}/watchlist/{^[]$}").permitAll()
				
				
				
//				temporary matcher used for testing purposes only
//				TODO remove this matcher when not needed anymore
				.antMatchers("/hello").authenticated()
				
				.and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	
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
