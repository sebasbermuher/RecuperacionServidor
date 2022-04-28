package org.iesalixar.servidor.dao;

import java.util.ArrayList;


import org.iesalixar.servidor.model.Customer;


public interface DAOCustomer {
	
	public Customer getCustomer(int customerNumber);
	public ArrayList<Customer> getAllCustomers();
	public boolean insertCustomer(Customer customer);
	public boolean updateCustomer(Customer customer);
	public boolean updateEmployeeCustomer(int customerNumber, int employee);
	public boolean removeCustomer(int customerNumber);
	public int getNumberCustomer();	
	

}



