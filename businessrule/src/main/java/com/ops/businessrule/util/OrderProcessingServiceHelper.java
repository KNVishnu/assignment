package com.ops.businessrule.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.OrderProcessingService;

/**
 * This service helper class identify and return the service class for the product
 * @author Vishnu K N
 */
public class OrderProcessingServiceHelper {
	
	/**
	 * 
	 * @return List Order Processing Services
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<OrderProcessingService> getServices(ProductType productType) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//This implementation can be simplified through autowiring in spring boot application
		//Also, this list of services can be cached based on the product type to improve performance
		List<OrderProcessingService> supportedServices = new ArrayList<>();
		Reflections reflections = new Reflections("com.ops.businessrule");
		Set<Class<?>> classes = reflections.get(Scanners.SubTypes.of(OrderProcessingService.class).asClass());
		for (Class<?> classs : classes) {
			OrderProcessingService service = (OrderProcessingService)Class.forName(classs.getName()).newInstance();
			if (service.isSupportedServive(productType)) {
				supportedServices.add(service);
			}
		}
		return supportedServices;
	}
}
