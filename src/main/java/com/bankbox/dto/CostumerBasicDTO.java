package com.bankbox.dto;

public class CostumerBasicDTO {
	private final String firstName;

	public CostumerBasicDTO(String fullName) {
		this.firstName = convertFullNameIntoFirstName(fullName);
	}

	public String getFirstName() {
		return firstName;
	}

	private String convertFullNameIntoFirstName(String fullName) {
		String space = " ";
		if (!fullName.contains(space)) return fullName;
		return fullName.substring(0, fullName.indexOf(space));
	}
}
