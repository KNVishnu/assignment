package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Address;
import com.ops.businessrule.service.AddressService;

/**
 * Default address service to handle Address related operations
 * @author Vishnu K N
 *
 */
public class DefaultAddressService implements AddressService {

	/**
	 * Get Address details based on address Id
	 * @param Long addressId
	 * @return Address
	 */
	@Override
	public Address getAddressById(Long addressId) {
		//This implementation is to fetch the address from repository
		//considering this coding challenge is related to business rule
		//returning empty address object
		return new Address();
	}

}
