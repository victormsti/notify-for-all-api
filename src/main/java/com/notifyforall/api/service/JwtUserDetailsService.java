package com.notifyforall.api.service;

import com.notifyforall.api.model.UserApp;
import com.notifyforall.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserApp> usuarioOpt = userRepository.findByUsername(username);
		if (usuarioOpt.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado com o username: " + username);
		}
		UserApp userApp = usuarioOpt.get();
		return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(), new ArrayList<>());
	}
}
