package com.ops.businessrule.model;

/**
 * Product model
 * @author Vishnu K N
 *
 */
public class Product {

	private Long productId;
	private String name;
	private ProductType productType;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
}
