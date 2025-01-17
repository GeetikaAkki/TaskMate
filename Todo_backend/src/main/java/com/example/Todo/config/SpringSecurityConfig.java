package com.example.Todo.config;

import java.beans.Customizer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.example.Todo.security.JwtAuthenticationEntryPoint;
import com.example.Todo.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter authenticationFilter;
	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http

	    .csrf(AbstractHttpConfigurer::disable)

	    .authorizeHttpRequests((authz) -> {
	    	//authz.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
	   // authz.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
	    //authz.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
	   // authz.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
	   // authz.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
	    	authz.requestMatchers("/api/auth/**").permitAll();
	    	authz.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
	    

	        authz.anyRequest().authenticated();

	    })

	    .httpBasic(httpBasic -> {})

	    .exceptionHandling((exceptions) -> exceptions

	        .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
	        
	    );
		http.exceptionHandling(exception->exception 
				.authenticationEntryPoint(authenticationEntryPoint));
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	
/*	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails geetika=User.builder()
		.username("geetika")
		.password(passwordEncoder().encode("akki"))
		.roles("USER")
		.build();
		
		UserDetails admin=User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(geetika, admin);
		
	}*/
}
