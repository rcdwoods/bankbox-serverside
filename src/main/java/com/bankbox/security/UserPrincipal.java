package com.bankbox.security;

import com.bankbox.domain.Costumer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
	private final String username;
	private final String password;

	public UserPrincipal(Costumer costumer) {
		this.username = costumer.getCpf();
		this.password = costumer.getPassword();
	}

	public static UserPrincipal create(Costumer costumer) {
		return new UserPrincipal(costumer);
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return this.password;
	}
}
