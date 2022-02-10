package com.ops.businessrule.service;

import com.ops.businessrule.model.Agent;

/**
 * Service to handle operations related to Agent
 * @author Vishnu K N
 */
public interface AgentService {

	/**
	 * Get the agent based on agent id
	 * @param Long agentId
	 * @return Agent
	 */
	Agent getAgentById(Long agentId);
}
