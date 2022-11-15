package com.bankbox.security;

import com.bankbox.domain.Costumer;
import com.bankbox.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CostumerRepository costumerRepository;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Optional<Costumer> costumer = costumerRepository.findByCpf(cpf);
		if (costumer.isEmpty()) throw new IllegalArgumentException("Costumer does not exist");
		return UserPrincipal.create(costumer.get());
	}
}
