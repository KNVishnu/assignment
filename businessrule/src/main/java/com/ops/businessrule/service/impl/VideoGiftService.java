package com.ops.businessrule.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.Product;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.AddressService;
import com.ops.businessrule.service.CustomerService;
import com.ops.businessrule.service.OrderProcessingService;
import com.ops.businessrule.service.ProductService;

/**
 * Video gift service to add gifts
 * @author Vishnu K N
 *
 */
public class VideoGiftService extends ShippingPackagingSlipService implements OrderProcessingService {

	private ProductService productService;
	
	public VideoGiftService(ProductService productService, AddressService addressService, CustomerService customerService) {
		super(addressService, customerService);
		this.productService = productService;
	}

	@Override
	public String process(Order order, LineItem lineItem) {
		Product product = productService.getProductById(lineItem.getProductId());
		if (product.getName().equalsIgnoreCase("Learning to Ski")) {
			List<Product> freeAidVideos = productService.getProductByName("Free Aid");
			if (!freeAidVideos.isEmpty()) {
				LineItem giftItem = new LineItem();
				giftItem.setProductId(freeAidVideos.get(0).getProductId());
				order.setGifts(Arrays.asList(giftItem));
			}
		}
		StringBuilder output = new StringBuilder("Free Aid gift added, ");
		output.append(super.process(order, lineItem));
		return output.toString();
	}
	
	@Override
	public boolean isSupportedServive(ProductType productType) {
		return productType.equals(ProductType.VIDEO);
	}
}
