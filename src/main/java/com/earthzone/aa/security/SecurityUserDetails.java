package com.earthzone.aa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.earthzone.aa.model.User;



public class SecurityUserDetails implements UserDetails{

	private User user;
	
	public SecurityUserDetails(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		System.out.println("SecUserDetails authority "+user.getRole() + user.getRole().name());
		List<GrantedAuthority> role=new ArrayList<>();
		role.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
		return role;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		//System.out.println("SecUserDetails pass");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		//System.out.println("SecUserDetails username");
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		//System.out.println("SecUserDetails enable");
		return user.getEnabled();
	}


}
