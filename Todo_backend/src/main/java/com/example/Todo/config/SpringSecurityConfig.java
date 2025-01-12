package com.example.Todo.config;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SpringSecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http

	    .csrf(AbstractHttpConfigurer::disable)

	    .authorizeHttpRequests((authz) -> authz

	        .anyRequest().authenticated()

	    )

	    .httpBasic(httpBasic -> {})

	    .exceptionHandling((exceptions) -> exceptions

	        .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
	        

	    );
		return http.build();
	}
}
