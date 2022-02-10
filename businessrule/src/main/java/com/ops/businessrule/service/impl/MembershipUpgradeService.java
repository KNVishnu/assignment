package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.CustomerService;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * Membership upgrade service to upgrade membership
 * @author Vishnu K N
 *
 */
public class MembershipUpgradeService extends MembershipActivationService implements OrderProcessingService {
	
	public MembershipUpgradeService(CustomerService customerService) {
		super(customerService);
	}

	@Override
	public String process(Order order, LineItem lineItem) {
		super.process(order, lineItem);
		return "Membership is upgraded for the customer & Mail notification sent successfully";
	}
	
	@Override
	public boolean isSupportedServive(ProductType productType) {
		return productType.equals(ProductType.UPGRADE);
	}
}
