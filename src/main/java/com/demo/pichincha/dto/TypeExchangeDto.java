package com.demo.pichincha.dto;

import java.math.BigDecimal;

public class TypeExchangeDto {
	private Integer coint_one;
	private String desc_one;
	private Integer coint_two;
	private String desc_two;
	private BigDecimal buy;
	private BigDecimal sale;

	public TypeExchangeDto() {
	}

	public TypeExchangeDto(Integer coint_one, String desc_one, Integer coint_two, String desc_two, BigDecimal buy,
			BigDecimal sale) {
		this.coint_one = coint_one;
		this.desc_one = desc_one;
		this.coint_two = coint_two;
		this.desc_two = desc_two;
		this.buy = buy;
		this.sale = sale;
	}

	public Integer getCoint_one() {
		return coint_one;
	}

	public void setCoint_one(Integer coint_one) {
		this.coint_one = coint_one;
	}

	public String getDesc_one() {
		return desc_one;
	}

	public void setDesc_one(String desc_one) {
		this.desc_one = desc_one;
	}

	public Integer getCoint_two() {
		return coint_two;
	}

	public void setCoint_two(Integer coint_two) {
		this.coint_two = coint_two;
	}

	public String getDesc_two() {
		return desc_two;
	}

	public void setDesc_two(String desc_two) {
		this.desc_two = desc_two;
	}

	public BigDecimal getBuy() {
		return buy;
	}

	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}

	public BigDecimal getSale() {
		return sale;
	}

	public void setSale(BigDecimal sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "TypeExchangeDto [coint_one=" + coint_one + ", desc_one=" + desc_one + ", coint_two=" + coint_two
				+ ", desc_two=" + desc_two + ", buy=" + buy + ", sale=" + sale + "]";
	}

}
