package com.ops.businessrule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;

import com.ops.businessrule.exception.NoBusinessRuleException;
import com.ops.businessrule.model.Address;
import com.ops.businessrule.model.Customer;
import com.ops.businessrule.model.LineItem;
import com.ops.businessrule.model.Order;
import com.ops.businessrule.model.Product;
import com.ops.businessrule.model.ProductType;
import com.ops.businessrule.service.impl.AgentCommissionPaymentService;
import com.ops.businessrule.service.impl.DefaultAddressService;
import com.ops.businessrule.service.impl.DefaultAgentService;
import com.ops.businessrule.service.impl.DefaultCustomerService;
import com.ops.businessrule.service.impl.DefaultProductService;
import com.ops.businessrule.service.impl.MembershipActivationService;
import com.ops.businessrule.service.impl.MembershipUpgradeService;
import com.ops.businessrule.service.impl.RoyaltyPackagingSlipService;
import com.ops.businessrule.service.impl.ShippingPackagingSlipService;
import com.ops.businessrule.service.impl.VideoGiftService;
import com.ops.businessrule.util.OrderProcessingServiceHelper;

public class BusinessRuleEngineTest {
	
	private static CustomerService customerService;
	private static AddressService addressService;
	private static AgentService agentService;
	private static ProductService productService;
	
	private static ShippingPackagingSlipService shippingService;
	private static RoyaltyPackagingSlipService royaltyService;
	private static AgentCommissionPaymentService agentCommissionService;
	private static MembershipActivationService activationService;
	private static MembershipUpgradeService upgradeService;
	private static VideoGiftService giftService;
	
	private static OrderProcessingServiceHelper helper;
	
	private Order physicalOrder = new Order();
	private Order bookOrder = new Order();
	private Order unknownOrder = new Order();
	private Order membershipActivationOrder = new Order();
	private Order membershipUpgradeOrde = new Order();
	private Order vidoOrder = new Order();
	
	@BeforeAll
	public static void init() {
		customerService = Mockito.mock(DefaultCustomerService.class);
		addressService = Mockito.mock(DefaultAddressService.class);
		agentService = Mockito.mock(DefaultAgentService.class);
		productService = Mockito.mock(DefaultProductService.class);
		
		shippingService = new ShippingPackagingSlipService(addressService, customerService);
		royaltyService = new RoyaltyPackagingSlipService(addressService, customerService);
		agentCommissionService = new AgentCommissionPaymentService(agentService);
		activationService = new MembershipActivationService(customerService);
		upgradeService =  new MembershipUpgradeService(customerService);
		giftService = new VideoGiftService(productService, addressService, customerService);
		
		helper = Mockito.mock(OrderProcessingServiceHelper.class);
	}
	
	@Test
	public void generatePackagingSlipAndAgentCommissionTest() {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		
		Customer customer = new Customer();
		customer.setAddressId(10L);
		
		physicalOrder.setCustomerId(100L);
		physicalOrder.setLineItems(Arrays.asList(lineItem));
		
		Product product = new Product();
		product.setProductType(ProductType.PHYSICAL);
				
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.PHYSICAL)).thenReturn(Arrays.asList(agentCommissionService, shippingService));
			Mockito.when(productService.getProductById(1L)).thenReturn(product);
			Mockito.when(customerService.getCustomerById(100L)).thenReturn(customer);
			Mockito.when(addressService.getAddressById(10L)).thenReturn(new Address());

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(physicalOrder);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("generatePackagingSlipTest failed");
		}
		assertEquals("Commission payment generated to the agent, Package slip generated and sent for shipping successfully", output);
	}
	
	@Test
	public void generatePackagingSlipAndAgentCommissionForBookTest() {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		bookOrder.setCustomerId(100L);
		bookOrder.setLineItems(Arrays.asList(lineItem));
		
		Customer customer = new Customer();
		customer.setAddressId(10L);
		
		Product product = new Product();
		product.setProductType(ProductType.BOOK);
		
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.BOOK)).thenReturn(Arrays.asList(agentCommissionService, royaltyService));
			Mockito.when(productService.getProductById(1L)).thenReturn(product);
			Mockito.when(customerService.getCustomerById(100L)).thenReturn(customer);
			Mockito.when(addressService.getAddressById(10L)).thenReturn(new Address());

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(bookOrder);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("generatePackagingSlipAndAgentCommissionForBookTest failed");
		}
		assertEquals("Commission payment generated to the agent, Package slip generated and sent for shipping & royalty successfully",
				output);
	}

	@Test
	public void unknownProductTest() {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		unknownOrder.setLineItems(Arrays.asList(lineItem));
		
		Product product = new Product();
		product.setProductType(ProductType.UNKNOWN);
		
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.UNKNOWN)).thenReturn(Collections.emptyList());
			Mockito.when(productService.getProductById(1L)).thenReturn(product);

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(unknownOrder);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("unknownProductTest failed");
		}
		assertEquals("", output);
	}

	@Test
	public void membershipActivationTest() {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		membershipActivationOrder.setLineItems(Arrays.asList(lineItem));
		membershipActivationOrder.setCustomerId(100L);
		
		Customer customer = new Customer();
		Product product = new Product();
		product.setProductType(ProductType.ACTIVATION);
		
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.ACTIVATION)).thenReturn(Arrays.asList(activationService));
			Mockito.when(productService.getProductById(1L)).thenReturn(product);
			Mockito.when(customerService.getCustomerById(100L)).thenReturn(customer);

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(membershipActivationOrder);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("membershipActivationTest failed");
		}
		assertEquals("Membership is activated for the customer & Mail notification sent successfully",
				output);
	}

	@Test
	public void membershipUpgradeTest() {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		membershipUpgradeOrde.setLineItems(Arrays.asList(lineItem));
		membershipUpgradeOrde.setCustomerId(100L);
		
		Customer customer = new Customer();
		Product product = new Product();
		product.setProductType(ProductType.UPGRADE);
		
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.UPGRADE)).thenReturn(Arrays.asList(upgradeService));
			Mockito.when(productService.getProductById(1L)).thenReturn(product);
			Mockito.when(customerService.getCustomerById(100L)).thenReturn(customer);

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(membershipUpgradeOrde);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("membershipUpgradeTest failed");
		}
		assertEquals("Membership is upgraded for the customer & Mail notification sent successfully",
				output);
	}

	@Test
	public void addGiftTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1L);
		vidoOrder.setLineItems(Arrays.asList(lineItem));
		vidoOrder.setCustomerId(100L);
		
		Customer customer = new Customer();
		Product product = new Product();
		product.setName("Learning to Ski");
		product.setProductType(ProductType.VIDEO);
		
		Product freeAidProduct = new Product();
		freeAidProduct.setProductId(2L);
		
		String output;
		try {
			Mockito.when(helper.getServices(ProductType.VIDEO)).thenReturn(Arrays.asList(giftService));
			Mockito.when(productService.getProductById(1L)).thenReturn(product);
			Mockito.when(productService.getProductByName("Free Aid")).thenReturn(Arrays.asList(freeAidProduct));
			Mockito.when(customerService.getCustomerById(100L)).thenReturn(customer);

			BusinessRuleEngine engine = new BusinessRuleEngine(helper, productService);
			output = engine.run(vidoOrder);
		} catch (NoBusinessRuleException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new AssertionFailedError("addGiftTest failed");
		}
		assertEquals("Free Aid gift added, Package slip generated and sent for shipping successfully",
				output);
	}

}
