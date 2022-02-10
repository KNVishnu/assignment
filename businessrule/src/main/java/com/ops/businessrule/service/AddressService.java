package com.ops.businessrule.service;

import com.ops.businessrule.model.Address;

/**
 * Service to handle operations related to Address
 * @author Vishnu K N
 */
public interface AddressService {

	/**
	 * Get the address based on address id
	 * @param Long addressId
	 * @return Address
	 */
	Address getAddressById(Long addressId);
}
