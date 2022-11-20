package com.bankbox.domain;

public enum BankName {
	BRADESCO("Bradesco S/A", "", "../../assets/imgs/banks/bradesco.png"),
	BANCO_DO_BRASIL("Banco do Brasil S/A", "", "../../assets/imgs/banks/banco_do_brasil.png"),
	ITAU("Itaú Unibanco S/A", "#EF761C", "../../assets/imgs/banks/itau.png"),
	SANTANDER("Santander S/A", "", "../../assets/imgs/banks/santander.png"),
	NUBANK("Nubank S/A", "#781BC9", "../../assets/imgs/banks/nubank.png");

	private final String formattedName;
	private final String backgroundColor;
	private final String imgUrl;

	BankName(String formattedName, String backgroundColor, String imgUrl) {
		this.formattedName = formattedName;
		this.backgroundColor = backgroundColor;
		this.imgUrl = imgUrl;
	}

	public String getFormattedName() {
		return formattedName;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
