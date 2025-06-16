package com.movie_rest_api.movie.configuration;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuth {
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.authorizeRequests().requestMatchers("/theater/**").hasRole("ADMIN")
		.requestMatchers("/movie/**").hasRole("ADMIN")
		.requestMatchers("/customer/**").permitAll()
		.requestMatchers("/bookings/**").hasRole("user").anyRequest().authenticated().and().httpBasic();
		return http.build();
	}
	
	@Bean
	public UserDetailsService UserDetails() {
		UserDetails Userlogin = User.builder().username("user").password(passwordEncoder().encode("1234")).roles("user").build();
		UserDetails adminlogin = User.builder().username("admin").password(passwordEncoder().encode("Abc")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(Userlogin,adminlogin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
