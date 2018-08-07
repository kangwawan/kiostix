package com.birumerah.kiostix.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.mapper.UserMapper;
import com.birumerah.kiostix.model.User;
import com.birumerah.kiostix.model.UserMenu;

@Service("userService")
public class UserService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userMapper.findOneByUsername(username);
		
		logger.debug(">> user.username : "+user.getUsername());
		logger.debug(">> user.password : "+user.getPassword());
		logger.debug(">> user.isenabled : "+user.isEnabled());
		
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());

	}

	public List<UserMenu> findMenuByUsername(String username) {
		return userMapper.findMenuByUsername(username);
	}

	public Integer getRoleIdByRoleName(String roleName) {
		return userMapper.getRoleIdByRoleName(roleName);
	}

	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
}