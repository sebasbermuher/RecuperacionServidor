package org.iesalixar.servidor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.dao.DAOOrderImpl;
import org.iesalixar.servidor.dao.DAOPaymentImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Order;

/**
 * Servlet implementation class CustomerReportServlet
 */

public class CustomerReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CustomerReportServlet.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerReportServlet() {
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

//		if (customerNumber != null) {

		DAOCustomerImpl daoCustomer = new DAOCustomerImpl();
		DAOPaymentImpl daoPayments = new DAOPaymentImpl();
		DAOOrderImpl daoOrders = new DAOOrderImpl();

		Customer cliente = daoCustomer.getCustomer(Integer.parseInt(customerNumber));
		request.setAttribute("cliente", cliente);

		Order order = daoOrders.getOrder(Integer.parseInt(customerNumber));
		request.setAttribute("order", order);

		logger.log(Level.INFO, "Se ha pedido el informe del cliente  " + cliente.getCustomerNumber() +" --- " + cliente.getCustomerName());
		
		request.getRequestDispatcher("/WEB-INF/view/customerReport.jsp").forward(request, response);

//		} else {
//			response.sendRedirect(request.getContextPath() + "/Home");
//		}

	}

}
