package com.yo.security;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yo.entity.LoginData;

public class CustomUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomUser(LoginData data) {
		super();
		this.data = data;
	}
	
	
	@Autowired
	private LoginData data;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+data.getRole().name()));
	}

	@Override
	public String getPassword() {
		return data.getPassword();
	}

	@Override
	public String getUsername() {
		return data.getUsername();
	}

}
