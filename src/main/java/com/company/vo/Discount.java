package com.company.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Discount {
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Number count;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Number avg3discount;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Number avg5discount;
	
	private Integer final_discount;
	
	public void setFinal_discount(Number discount) {
		this.final_discount = discount.intValue();
	}
}