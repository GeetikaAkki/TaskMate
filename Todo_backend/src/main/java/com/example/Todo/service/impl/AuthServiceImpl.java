package com.example.Todo.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Todo.dto.JwtAuthResponse;
import com.example.Todo.dto.LoginDto;
import com.example.Todo.dto.RegisterDto;
import com.example.Todo.entity.Role;
import com.example.Todo.entity.User;
import com.example.Todo.exception.TodoAPIException;
import com.example.Todo.repository.RoleRepository;
import com.example.Todo.repository.UserRepository;
import com.example.Todo.security.JwtTokenProvider;
import com.example.Todo.service.AuthService;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	

	  @Override
	    public String register(RegisterDto registerDto) {

	        // check username is already exists in database
	        if(userRepository.existsByUsername(registerDto.getUsername())){
	            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
	        }

	        // check email is already exists in database
	        if(userRepository.existsByEmail(registerDto.getEmail())){
	            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
	        }

	        User user = new User();
	        user.setName(registerDto.getName());
	        user.setUsername(registerDto.getUsername());
	        user.setEmail(registerDto.getEmail());
	        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
	        Role userRole = roleRepository.findByName("ROLE_USER");
	        if (userRole == null) {
	            throw new TodoAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found");
	        }
	        

	        Set<Role> roles = new HashSet<>();
	        Role userRole1 = roleRepository.getReferenceById(userRole.getId());
	        roles.add(userRole1);

	        user.setRoles(roles);

	        userRepository.save(user);

	        return "User Registered Successfully!.";
	    }


	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(),
				loginDto.getPassword()
				));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtTokenProvider.generateToken(authentication);
		Optional <User> userOptional=userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
		String role=null;
		if(userOptional.isPresent()) {
			User loggedInUser=userOptional.get();
			Optional <Role> optionalRole=loggedInUser.getRoles().stream().findFirst();
			if(optionalRole.isPresent()) {
				Role useRole = optionalRole.get();
				role=useRole.getName();
				
			}
		} JwtAuthResponse jwtAuthResponse= new JwtAuthResponse();
		jwtAuthResponse.setRole(role);
		jwtAuthResponse.setAccessToken((token));
		return jwtAuthResponse;
	}

}
