package com.ops.businessrule.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Order model
 * @author Vishnu K N
 *
 */
public class Order {

	private Long orderId;
	private Long customerId;
	private LocalDate orderDate;
	private List<LineItem> lineItems;
	private boolean paymentStatus;
	private String status;
	private List<LineItem> gifts;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public List<LineItem> getGifts() {
		return gifts;
	}
	public void setGifts(List<LineItem> gifts) {
		this.gifts = gifts;
	}
}
