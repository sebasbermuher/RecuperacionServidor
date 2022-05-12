package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOOrder;
import org.iesalixar.servidor.dao.DAOOrderDetails;
import org.iesalixar.servidor.dao.DAOOrderDetailsImpl;
import org.iesalixar.servidor.dao.DAOOrderImpl;
import org.iesalixar.servidor.model.Order;
import org.iesalixar.servidor.model.OrderDetail;

/**
 * Servlet implementation class OrderReportServlet
 */
public class OrderReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderReportServlet() {
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

		DAOOrder daoOrder = new DAOOrderImpl();
		DAOOrderDetails daoOrderDetails = new DAOOrderDetailsImpl();
		
		Order order = daoOrder.getOrder(Integer.parseInt(orderNumber));
		ArrayList<OrderDetail> orderDetails = daoOrderDetails.getAllOrderDetailsFromOrder(Integer.parseInt(orderNumber));
		
		request.setAttribute("order", order);
		request.setAttribute("orderDetails", orderDetails);
		request.getRequestDispatcher("/WEB-INF/view/orderReport.jsp").forward(request, response);

	}

}
