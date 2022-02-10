package com.ops.businessrule.service;

import com.ops.businessrule.exception.NoBusinessRuleException;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;

/**
 * Service to handle the order post successful payment
 * @author Vishnu K N
 */
public interface OrderProcessingService {

	/**
	 * Process specific instructions based on the product type
	 * @param Order order
	 * @param LineItem lineItem
	 * @return String
	 */
	String process(Order order, LineItem lineItem) throws NoBusinessRuleException;
	
	/**
	 * Check the service supported for the product type
	 * @param ProductType productType
	 * @return boolean
	 */
	boolean isSupportedServive(ProductType productType);
}
