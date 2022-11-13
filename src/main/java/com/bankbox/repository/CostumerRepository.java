package com.bankbox.repository;

import com.bankbox.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
}
