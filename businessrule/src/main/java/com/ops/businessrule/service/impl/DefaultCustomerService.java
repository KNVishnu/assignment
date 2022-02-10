package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Customer;
import com.ops.businessrule.service.CustomerService;

/**
 * Default customer service to handle Customer related operations
 * @author Vishnu K N
 *
 */
public class DefaultCustomerService implements CustomerService {

	/**
	 * Get Customer details based on customer Id
	 * @param Long customerId
	 * @return Customer
	 */
	@Override
	public Customer getCustomerById(Long customerId) {
		//This implementation is to fetch the customer from repository
		//considering this coding challenge is related to business rule
		//returning empty customer object
		return new Customer();
	}
}
