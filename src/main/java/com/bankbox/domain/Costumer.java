package com.bankbox.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Costumer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String cpf;
	private String password;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	private List<BankAccount> bankAccounts;

	public Costumer() {
		this.bankAccounts = new ArrayList<>();
	}

	public BigDecimal getBalance() {
		return bankAccounts.stream()
			.map(BankAccount::getBalance)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public String getFirstName() {
		String space = " ";
		if (!name.contains(space)) return name;
		return name.substring(0, name.indexOf(space));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getPassword() {
		return password;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void addBankAccount(BankAccount bankAccount) {
		this.bankAccounts.add(bankAccount);
	}
}
