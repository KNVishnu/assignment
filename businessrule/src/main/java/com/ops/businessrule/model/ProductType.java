package com.ops.businessrule.model;

/**
 * Enum defines different product types
 * @author Vishnu K N
 *
 */
public enum ProductType {
	
	BOOK("book"),
	ACTIVATION("membership-activation"),
	PHYSICAL("physical"),
	UNKNOWN("unknown"),
	UPGRADE("membership-upgrade"),
	VIDEO("video");
	
	private String name;
	
	ProductType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
