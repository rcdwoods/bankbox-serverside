package com.bankbox.repository;

import com.bankbox.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
	Optional<Costumer> findByCpf(String cpf);
	@Query(value = "SELECT c.name FROM Costumer c WHERE c.cpf = :cpf")
	String findNameByCpf(String cpf);
}
