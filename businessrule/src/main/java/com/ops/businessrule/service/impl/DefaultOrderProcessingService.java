package com.ops.businessrule.service.impl;

import com.ops.businessrule.exception.NoBusinessRuleException;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * Default order processing service to handle new products
 * @author Vishnu K N
 */
public class DefaultOrderProcessingService implements OrderProcessingService {

	/**
	 * Order placed for unknown/new product and business rule is yet to be defined
	 * @param Order order
	 * @param LineItem lineItem
	 * @return String 
	 * @throws NoBusinessRuleException 
	 */
	@Override
	public String process(Order order, LineItem lineItem) throws NoBusinessRuleException {
		//Assuming this error will handled internally not sent to the customer
		throw new NoBusinessRuleException("Business Rule not available for this product");
	}
	
	@Override
	public boolean isSupportedServive(ProductType productType) {
		return false;
	}
}
