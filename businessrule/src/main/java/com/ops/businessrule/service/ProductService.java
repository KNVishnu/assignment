package com.ops.businessrule.service;

import java.util.List;

import com.ops.businessrule.model.Product;

/**
 * Service to handle operations related to Product
 * @author Vishnu K N
 */
public interface ProductService {

	/**
	 * Get the product based on product id
	 * @param Long productId
	 * @return Product
	 */
	Product getProductById(Long productId);

	/**
	 * Get the product based on name
	 * @param Long productId
	 * @return List Product
	 */
	List<Product> getProductByName(String name);
}
