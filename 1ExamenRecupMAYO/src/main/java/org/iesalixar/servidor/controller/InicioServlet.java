package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.ProductLine;

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
	
//		DAOOrderImpl daoOrder = new DAOOrderImpl();
//		ArrayList<Order> listaOrder = (ArrayList) daoOrder.getAllOrders();
//		
//		request.setAttribute("order", listaOrder);
		
		DAOProductLineImpl daoProductLine = new DAOProductLineImpl();
		ArrayList<ProductLine> listaProductLine = (ArrayList) daoProductLine.getAllProductLine();

		request.setAttribute("productLine", listaProductLine);
		
		request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
	}

}
