package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.model.Customer;

/**
 * Servlet implementation class InicioServlet
 */

public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InicioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DAOCustomerImpl daoCustomer = new DAOCustomerImpl();
		ArrayList<Customer> listaCustomer = (ArrayList) daoCustomer.getAllCustomers();

		request.setAttribute("customer", listaCustomer);
		
		request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
	}

}
