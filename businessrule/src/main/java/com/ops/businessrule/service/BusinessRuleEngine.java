package com.ops.businessrule.service;

import java.util.List;

import com.ops.businessrule.exception.NoBusinessRuleException;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.Product;
import com.ops.businessrule.service.impl.DefaultProductService;
import com.ops.businessrule.util.OrderProcessingServiceHelper;

/**
 * Business Rule Engine to execute special instruction according to the product type
 * @author Vishnu K N
 *
 */
public class BusinessRuleEngine {
	
	private OrderProcessingServiceHelper helper;
	private ProductService productService;
	
	public BusinessRuleEngine() {
		this.helper = new OrderProcessingServiceHelper();
		this.productService = new DefaultProductService();
	}

	public BusinessRuleEngine(OrderProcessingServiceHelper helper, ProductService productService) {
		this.helper = helper;
		this.productService = productService;
	}

	/**
	 * Identify the list of services to be processed and consume them in sequence.
	 * @param order
	 * @return
	 * @throws NoBusinessRuleException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public String run(Order order) throws NoBusinessRuleException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		StringBuilder output = new StringBuilder();
		for (LineItem item : order.getLineItems()) {
			Product product = productService.getProductById(item.getProductId());
			List<OrderProcessingService> services = helper.getServices(product.getProductType());
			for(OrderProcessingService service : services) {
				if (output.length() > 0) output.append(", ");
				output.append(service.process(order, item));
			}
		}
		return output.toString();
	}
}
