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
 * Royalty Packaging slip service handles generating the packaging slip and sends to shipment & Royalty
 * @author Vishnu K N
 *
 */
public class RoyaltyPackagingSlipService extends ShippingPackagingSlipService implements OrderProcessingService {
	
	public RoyaltyPackagingSlipService(AddressService addressService, CustomerService customerService) {
		super(addressService, customerService);
	}

	@Override
	public String process(Order order, LineItem lineItem) {
		Customer customer = customerService.getCustomerById(order.getCustomerId());
		Address address = addressService.getAddressById(customer.getAddressId());
		generateSlipAndSendForShipping(address, order, lineItem);
		generateSlipAndSendForRoyalty(address, order, lineItem);
		return "Package slip generated and sent for shipping & royalty successfully";
	}
	
	protected void generateSlipAndSendForRoyalty(Address address, Order order, LineItem lineItem) {
		System.out.println("Package slip generated and sent for royalty successfully for the provided address of the provided order");
	}
	
	@Override
		public boolean isSupportedServive(ProductType productType) {
			return productType.equals(ProductType.BOOK);
		}

}
