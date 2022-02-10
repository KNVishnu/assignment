package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Agent;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.AgentService;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * Default agent commission payment service to handles generating the payment and sends to agent
 * @author Vishnu K N
 *
 */
public class AgentCommissionPaymentService implements OrderProcessingService {

	private AgentService agentService;
	
	public AgentCommissionPaymentService(AgentService agentService) {
		this.agentService = agentService;
	}
	@Override
	public String process(Order order, LineItem lineItem) {
		Agent agent = agentService.getAgentById(lineItem.getAgentId());
		generateCommissionPayment(agent, lineItem);
		return "Commission payment generated to the agent";
	}
	
	private void generateCommissionPayment(Agent agent, LineItem lineItem) {
		System.out.println("Commission payment generated to the agent");
	}
	
	@Override
	public boolean isSupportedServive(ProductType productType) {
		return productType.equals(ProductType.PHYSICAL) || productType.equals(ProductType.BOOK);
	}

}
