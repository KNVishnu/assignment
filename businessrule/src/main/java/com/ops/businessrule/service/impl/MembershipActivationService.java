package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Customer;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.CustomerService;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * Membership activation service to add membership and activate
 * @author Vishnu K N
 *
 */
public class MembershipActivationService implements OrderProcessingService {

	private CustomerService customerService;
	
	public MembershipActivationService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Override
	public String process(Order order, LineItem lineItem) {
		Customer customer = customerService.getCustomerById(order.getCustomerId());
		customer.setMembershipId(lineItem.getProductId());
		return "Membership is activated for the customer & Mail notification sent successfully";
	}

	@Override
	public boolean isSupportedServive(ProductType productType) {
		return productType.equals(ProductType.ACTIVATION);
	}

}
