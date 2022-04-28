package org.iesalixar.servidor.dao;

import java.util.List;

import org.iesalixar.servidor.model.Payment;

public interface DAOPayment {
	
	public List<Payment> getAllPayments();
	public Payment getPayment(int customerNumber,String checkNumber);
	public boolean removePayment(int customerNumber,String checkNumber);
	public boolean updatePayment(Payment payment);
	public boolean insertPayment(Payment payment);

}
