package org.iesalixar.servidor.dao;

import java.util.ArrayList;

import org.iesalixar.servidor.model.Order;

public interface DAOOrder {

	public Order getOrder(int orderNumber);
	public ArrayList<Order> getAllOrders();
	public boolean insertOrder(Order order);
	public boolean updateOrder(Order order);
	public boolean removeOrder(int orderNumber);
	public boolean updateCustomerOrder(int orderNumber, int customerNumber);

}
