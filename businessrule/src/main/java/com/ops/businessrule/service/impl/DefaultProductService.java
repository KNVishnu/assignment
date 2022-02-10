package com.ops.businessrule.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ops.businessrule.model.Product;
import com.ops.businessrule.service.ProductService;

/**
 * Default address service to handle Address related operations
 * @author Vishnu K N
 *
 */
public class DefaultProductService implements ProductService {

	/**
	 * Get the product based on product id
	 * @param Long productId
	 * @return Product
	 */
	@Override
	public Product getProductById(Long productId) {
		//This implementation is to fetch the product from repository
		//considering this coding challenge is related to business rule
		//returning empty product object
		return new Product();
	}

	/**
	 * Get the product based on name
	 * @param Long productId
	 * @return List Product
	 */
	@Override
	public List<Product> getProductByName(String name) {
		//This implementation is to fetch the product list from repository
		//considering this coding challenge is related to business rule
		//returning empty product list object
		return new ArrayList<>();
	}
}
