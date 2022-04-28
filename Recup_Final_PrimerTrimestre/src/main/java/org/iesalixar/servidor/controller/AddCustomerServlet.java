package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.dao.DAOEmployeeImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Employee;

/**
 * Servlet implementation class AddCustomerServelt
 */
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DAOEmployeeImpl daoEmployee = new DAOEmployeeImpl();
		ArrayList<Employee> empleados = daoEmployee.getAllEmployees();
		request.setAttribute("empleados", empleados);

		request.getRequestDispatcher("../WEB-INF/view/admin/addCustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Customer customer = new Customer();
		DAOCustomerImpl daoCust = new DAOCustomerImpl();

		int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
		String customerName = request.getParameter("customerName");
		String contactFirstName = request.getParameter("contactFirstName");
		String contactLastName = request.getParameter("contactLastName");
		String phone = request.getParameter("phone");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postalCode = request.getParameter("postalCode");
		String country = request.getParameter("country");
		int salesRepEmployeeNumber = Integer.parseInt(request.getParameter("salesRepEmployeeNumber"));
		Double creditLimit = Double.parseDouble(request.getParameter("creditLimit"));

		if (customerNumber != 0 && customerName != null && contactFirstName != null && contactLastName != null
				&& phone != null && addressLine1 != null && addressLine2 != null && city != null && state != null
				&& postalCode != null && country != null && salesRepEmployeeNumber != 0 && creditLimit != 0.0) {

			customer.setCustomerNumber(customerNumber);
			customer.setCustomerName(customerName);
			customer.setContactFirstName(contactFirstName);
			customer.setContactLastName(contactLastName);
			customer.setPhone(phone);
			customer.setAddressLine1(addressLine1);
			customer.setAddressLine2(addressLine2);
			customer.setCity(city);
			customer.setState(state);
			customer.setPostalCode(postalCode);
			customer.setCountry(country);
			customer.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
			customer.setCreditLimit(creditLimit);
			daoCust.insertCustomer(customer);

		}

		response.sendRedirect(request.getContextPath() + "/Home");
	}

}
