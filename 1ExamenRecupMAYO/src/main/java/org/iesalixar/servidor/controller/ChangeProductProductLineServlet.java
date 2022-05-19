package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOProduct;
import org.iesalixar.servidor.dao.DAOProductImpl;
import org.iesalixar.servidor.dao.DAOProductLine;
import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.Order;
import org.iesalixar.servidor.model.ProductLine;
import org.iesalixar.servidor.model.Productos;

/**
 * Servlet implementation class ChangeProductProductLineServlet
 */
public class ChangeProductProductLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ChangeProductProductLineServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeProductProductLineServlet() {
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

		DAOProduct daoProduct = new DAOProductImpl();
		DAOProductLine daoProductLine = new DAOProductLineImpl();

		List<Productos> product = daoProduct.getAllProducts();
		List<ProductLine> productLine = daoProductLine.getAllProductLine();

		request.setAttribute("product", product);
		request.setAttribute("productLine", productLine);

		request.getRequestDispatcher("../WEB-INF/view/admin/changeProductProductLine.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productName = request.getParameter("productName");
		String productLine2 = request.getParameter("productLine2");

		DAOProduct daoProduct = new DAOProductImpl();
		DAOProductLine daoProductLine = new DAOProductLineImpl();

		List<ProductLine> product = daoProductLine.getAllProductLine();
		List<Productos> productLine = daoProduct.getAllProducts();

		request.setAttribute("product", product);
		request.setAttribute("productLine", productLine);

		DAOProduct daoProducto2 = new DAOProductImpl();
		DAOProductLine daoPL = new DAOProductLineImpl();

		ArrayList<Productos> prodLog = daoProducto2.getProduct(productName);
		ProductLine prodLinLog = daoPL.getProductLine(productLine2);

		logger.log(Level.INFO, prodLinLog.getProductLine() + " Actualizado");

		if (daoProduct.actualizarProducto(productLine2, productName) == true) {
			request.setAttribute("correcto", "ProductLine del producto cambiado correctamente.");

			doGet(request, response);
			return;

		} else {
			request.setAttribute("error", "Error al cambiar el productLine del producto.");

			doGet(request, response);
			return;

		}

	}
}
