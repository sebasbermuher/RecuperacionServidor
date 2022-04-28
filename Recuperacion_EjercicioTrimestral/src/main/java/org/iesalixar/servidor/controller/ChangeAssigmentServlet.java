package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.dao.DAOEmployeeImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Employee;

/**
 * Servlet implementation class ChangeAssigmentServlet
 */
public class ChangeAssigmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ChangeAssigmentServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeAssigmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String customerNumber = request.getParameter("cn");

		if (customerNumber != null) {

			DAOCustomerImpl daoCustomers = new DAOCustomerImpl();
			DAOEmployeeImpl daoEmployees = new DAOEmployeeImpl();

			Customer cliente = daoCustomers.getCustomer(Integer.parseInt(customerNumber));
			ArrayList<Employee> empleados = daoEmployees.getAllEmployees();

			request.setAttribute("cliente", cliente);
			request.setAttribute("empleados", empleados);

			request.getRequestDispatcher("/WEB-INF/view/admin/changeAssigment.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int customerNumber = Integer.parseInt(request.getParameter("cn"));
		int salesRepEmployeeNumber = Integer.parseInt(request.getParameter("salesRepEmployeeNumber"));

		if (customerNumber != 0 && salesRepEmployeeNumber != 0) {

			DAOCustomerImpl daoImpl = new DAOCustomerImpl();

			daoImpl.updateEmployeeCustomer(customerNumber, salesRepEmployeeNumber);

		}
		response.sendRedirect(request.getContextPath() + "/Home");

		DAOCustomerImpl daoCustomers = new DAOCustomerImpl();
		String cn = request.getParameter("cn");
		Customer cliente = daoCustomers.getCustomer(Integer.parseInt(cn));

		logger.log(Level.INFO, "Se ha cambiado la asignacion del empleado  al cliente " + cliente.getCustomerNumber()
				+ " --- " + cliente.getCustomerName() + " por el empleado " + cliente.getSalesRepEmployeeNumber());
	}

}
