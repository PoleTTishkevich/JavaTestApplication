package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.AppRoleRepository;
import com.tishkevich.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;
	
	private final AppRoleRepository appRoleRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository, AppRoleRepository appRoleRepository) {
		this.userRepository = userRepository;
		this.appRoleRepository = appRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount appUser = this.userRepository.findByUsername(username);

		if (appUser == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		System.out.println("Found User: " + appUser);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appRoleRepository.getRoleNames(appUser.getId());

		List<GrantedAuthority> grantList = new ArrayList<>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				System.out.println(role);
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		return new User(appUser.getUsername(), //
				appUser.getPassword(), grantList);
	}

}