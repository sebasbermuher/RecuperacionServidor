package org.iesalixar.servidor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOProductImpl;
import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.ProductLine;
import org.iesalixar.servidor.model.Productos;

/**
 * Servlet implementation class ProductLineReportServlet
 */

public class ProductLineReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductLineReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String productLine = request.getParameter("pl");
		int pl = Integer.parseInt(request.getParameter("pl"));

		DAOProductLineImpl daoProductLine = new DAOProductLineImpl();
//		DAOProductImpl daoProduct = new DAOProductImpl();

		ProductLine proLine = daoProductLine.getCategorias2(Integer.toString(pl));
		request.setAttribute("proLine", proLine);

		request.getRequestDispatcher("/WEB-INF/view/productLineReport.jsp").forward(request, response);

	}

}
