package br.com.fiap.postech.hackapay.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

	@Serial
	private static final long serialVersionUID = 0;

	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl() {
		super();
	}

	public UserDetailsImpl(User user) {
		this();
		this.username = user.getLogin();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
}
