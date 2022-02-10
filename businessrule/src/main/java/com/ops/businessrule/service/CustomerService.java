package com.ops.businessrule.service;

import com.ops.businessrule.model.Customer;

/**
 * Service to handle operations related to Customer
 * @author Vishnu K N
 */
public interface CustomerService {

	/**
	 * Get the customer based on address id
	 * @param Long customerId
	 * @return Customer
	 */
	Customer getCustomerById(Long customerId);
}
