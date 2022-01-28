package com.company.vo;

import org.springframework.data.annotation.Id;

@lombok.Data
public class Item {
	@Id
	private String _id;
	
	private String item_id;
	private String itemp_description;
	private String date;
	private Integer three_or_more_item_discount;
	private Integer five_or_more_item_discount;
}