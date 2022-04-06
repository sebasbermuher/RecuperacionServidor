package org.iesalixar.servidor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOProductImpl;

/**
 * Servlet implementation class MostrarProductosServlet
 */
public class MostrarProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarProductosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoria = (String) request.getParameter("productLine");
		DAOProductImpl dao = new DAOProductImpl();

		request.setAttribute("categoria", categoria);
		request.setAttribute("productos", dao.getProduct(categoria));

		request.getRequestDispatcher("WEB-INF/view/mostrarProductos.jsp").forward(request, response);
	}
}
