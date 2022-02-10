package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Address;
import com.ops.businessrule.model.Customer;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.AddressService;
import com.ops.businessrule.service.CustomerService;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * Shipping Packaging slip service handles generating the packaging slip and sends to shipment
 * @author Vishnu K N
 *
 */
public class ShippingPackagingSlipService implements OrderProcessingService {
	
	protected AddressService addressService;
	protected CustomerService customerService;
	
	public ShippingPackagingSlipService(AddressService addressService, CustomerService customerService) {
		//This will be autowired in the spring boot application
		this.customerService = customerService;
		this.addressService = addressService;
	}

	@Override
	public String process(Order order, LineItem lineItem) {
		Customer customer = customerService.getCustomerById(order.getCustomerId());
		Address address = addressService.getAddressById(customer.getAddressId());
		generateSlipAndSendForShipping(address, order, lineItem);
		return "Package slip generated and sent for shipping successfully";
	}
	
	protected void generateSlipAndSendForShipping(Address address, Order order, LineItem lineItem) {
		System.out.println("Package slip generated and sent for shipping successfully for the provided address of the provided order");
	}
	
	@Override
	public boolean isSupportedServive(ProductType productType) {
		return productType.equals(ProductType.PHYSICAL);
	}

}
