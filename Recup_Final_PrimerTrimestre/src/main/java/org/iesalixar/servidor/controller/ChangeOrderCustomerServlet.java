package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOCustomer;
import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.dao.DAOOrder;
import org.iesalixar.servidor.dao.DAOOrderImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Order;

/**
 * Servlet implementation class ChangeOrderCustomerServlet
 */
public class ChangeOrderCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeOrderCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderNumber = request.getParameter("orderNumber");

		DAOCustomer daoCustomer = new DAOCustomerImpl();
		DAOOrder daoOrder = new DAOOrderImpl();

		Order order = daoOrder.getOrder(Integer.parseInt(orderNumber));
		List<Customer> customers = daoCustomer.getAllCustomers();

		request.setAttribute("order", order);
		request.setAttribute("customers", customers);

		request.getRequestDispatcher("/WEB-INF/view/admin/changeOrderCustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderNumber = (String) request.getParameter("orderNumber");
		String customerNumber = (String) request.getParameter("customer");
		
 
		if (customerNumber != null && orderNumber != null) {
			DAOOrder daoOrder = new DAOOrderImpl();
			daoOrder.updateCustomerOrder(Integer.parseInt(orderNumber), Integer.parseInt(customerNumber));
			
			if (daoOrder.updateCustomerOrder(Integer.parseInt(orderNumber), Integer.parseInt(customerNumber))==true) {
				request.setAttribute("error", "Cliente cambiado correctamente.");

				doGet(request, response);
				return;

			} else {
				request.setAttribute("error", "Error al cambiar de cliente.");

				doGet(request, response);
				return;

			}
		}

//		response.sendRedirect(request.getContextPath() + "/Home");

	}
}
