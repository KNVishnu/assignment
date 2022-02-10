package com.ops.businessrule.service.impl;

import com.ops.businessrule.model.Agent;
import com.ops.businessrule.service.AgentService;

/**
 * Default agent service to handle Agent related operations
 * @author Vishnu K N
 *
 */
public class DefaultAgentService implements AgentService {

	/**
	 * Get Agent details based on agent Id
	 * @param Long agentId
	 * @return Agent
	 */
	@Override
	public Agent getAgentById(Long agentId) {
		//This implementation is to fetch the agent from repository
		//considering this coding challenge is related to business rule
		//returning empty agent object
		return new Agent();
	}

}
