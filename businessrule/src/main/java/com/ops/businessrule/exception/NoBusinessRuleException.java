package com.ops.businessrule.exception;

/**
 * Exception to handle when business rule not available for new product
 * @author Vishnu K N
 *
 */
public class NoBusinessRuleException extends Exception {

	private static final long serialVersionUID = -955958735465070544L;

	public NoBusinessRuleException(String message) {
		super(message);
	}
}
