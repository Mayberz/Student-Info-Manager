package com.yo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yo.entity.LoginData;
import com.yo.reposiory.IAuthRepositry;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IAuthRepositry repositry;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginData data=repositry.findByUsername(username);
		if (data==null) {
			throw new UsernameNotFoundException("user not found");
		}
		else {
			return new CustomUser(data);	
		}
	}

}
