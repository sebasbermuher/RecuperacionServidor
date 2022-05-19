package org.iesalixar.servidor.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Productos product;
	private int quantityOrdered;
	private double priceEach;
	private int orderLineNumber;
	private String productName;

	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(Productos product, int quantityOrdered, double priceEach, int orderLineNumber) {
		super();
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public Productos getProduct() {
		return product;
	}

	public void setProduct(Productos product) {
		this.product = product;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}