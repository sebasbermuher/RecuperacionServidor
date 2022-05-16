package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOOrder;
import org.iesalixar.servidor.dao.DAOOrderDetails;
import org.iesalixar.servidor.dao.DAOOrderDetailsImpl;
import org.iesalixar.servidor.dao.DAOOrderImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Order;
import org.iesalixar.servidor.model.OrderDetail;

/**
 * Servlet implementation class OrderReportServlet
 */
public class OrderReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OrderReportServlet.class);


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
		ArrayList<OrderDetail> orderDetails = daoOrderDetails.getDetailsFromOrder(Integer.parseInt(orderNumber));
		
		request.setAttribute("order", order);
		request.setAttribute("orderDetails", orderDetails);
		request.getRequestDispatcher("/WEB-INF/view/orderReport.jsp").forward(request, response);
		
		
		
		Order orderLog = daoOrder.getOrder(Integer.parseInt(orderNumber));
		logger.log(Level.INFO, "Se ha consultado los productos del pedido número : "+ orderLog.getOrderNumber());

	}

}
