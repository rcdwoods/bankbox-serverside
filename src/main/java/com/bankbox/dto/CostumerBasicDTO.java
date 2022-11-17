package com.bankbox.dto;

public class CostumerBasicDTO {
	private String firstName;
	public String cpf;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fullName) {
		this.firstName = convertFullNameIntoFirstName(fullName);
	}

	private String convertFullNameIntoFirstName(String fullName) {
		String space = " ";
		if (!fullName.contains(space)) return fullName;
		return fullName.substring(0, fullName.indexOf(space));
	}
}
