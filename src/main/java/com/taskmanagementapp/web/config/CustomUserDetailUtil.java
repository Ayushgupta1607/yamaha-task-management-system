package com.taskmanagementapp.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.web.model.entity.User;
import com.taskmanagementapp.web.repository.UserRepository;

/**
 * Spring Security User Detail Service Implementation
 * 
 * @author Ayush
 */
@Service
public class CustomUserDetailUtil implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailUtil(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username or email:" + username));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
	}

}
