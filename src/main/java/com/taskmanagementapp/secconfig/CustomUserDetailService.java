package com.taskmanagementapp.secconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.model.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private UserRepository userRepository;
	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
	               .orElseThrow(() ->
	                       new UsernameNotFoundException("User not found with username or email:" + username));
		
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
	}

}
