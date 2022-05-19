package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOProductImpl;
import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.ProductLine;
import org.iesalixar.servidor.model.Productos;

/**
 * Servlet implementation class AddProductLineServlet
 */
public class AddProductLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductLineServlet() {
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

//		DAOProductLineImpl daoPL = new DAOProductLineImpl();
//		ArrayList<ProductLine> pl = daoPL.getAllProductLine();
//		request.setAttribute("pl", pl);

		request.getRequestDispatcher("../WEB-INF/view/admin/addProductLine.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductLine proLine = new ProductLine();
		DAOProductLineImpl daoProductLine = new DAOProductLineImpl();

		String productLine = request.getParameter("productLine");
		String textDescription = request.getParameter("textDescription");

		proLine.setProductLine(productLine);
		proLine.setTextDescription(textDescription);

		boolean añadido = daoProductLine.createProductLine(proLine);

		if (añadido) {

			request.setAttribute("añadido", "ProductLine añadido correctamente.");
			doGet(request, response);
			return;

		} else {

			request.setAttribute("error", "ProductLine existente.");
			doGet(request, response);
			return;
		}

	}

}
