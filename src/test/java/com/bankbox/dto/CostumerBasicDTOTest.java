package com.bankbox.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostumerBasicDTOTest {

	@Test
	void mustTransformFullNameInFirstName() {
		String fullName = "Richard Pereira do Nascimento";
		CostumerBasicDTO costumerBasicDTO = new CostumerBasicDTO(fullName);
		Assertions.assertThat(costumerBasicDTO.getFirstName()).isEqualTo("Richard");
	}

	@Test
	void mustKeepFullNameAsFirstNameWhenDoesNotExistSpacesOnIt() {
		String fullName = "Richard";
		CostumerBasicDTO costumerBasicDTO = new CostumerBasicDTO(fullName);
		Assertions.assertThat(costumerBasicDTO.getFirstName()).isEqualTo("Richard");
	}
}
