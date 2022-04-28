package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOCustomerImpl;
import org.iesalixar.servidor.dao.DAOProductImpl;
import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.ProductLine;
import org.iesalixar.servidor.model.Productos;

/**
 * Servlet implementation class AddProductServlet
 */

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		DAOEmployeeImpl daoEmployee = new DAOEmployeeImpl();
//		ArrayList<Employee> empleados = daoEmployee.getAllEmployees();
//		request.setAttribute("empleados", empleados);

		DAOProductLineImpl daoPL = new DAOProductLineImpl();
		ArrayList<ProductLine> pl = daoPL.getAllProductLine();
		request.setAttribute("pl", pl);

		request.getRequestDispatcher("../WEB-INF/view/admin/addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Productos product = new Productos();
		DAOProductImpl daoProduct = new DAOProductImpl();

		String productCode = request.getParameter("productCode");
		String productName = request.getParameter("productName");
		String productLine = request.getParameter("productLine");
		String productScale = request.getParameter("productScale");
		String productVendor = request.getParameter("productVendor");
		String productDescription = request.getParameter("productDescription");
		int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
		Double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
		Double MSRP = Double.parseDouble(request.getParameter("MSRP"));

//		if (productCode != null && productName != null && productLine != null && productScale != null
//				&& productVendor != null && productDescription != null && quantityInStock != 0 && buyPrice != 0.0
//				&& MSRP != 0.0) {

		product.setProductCode(productCode);
		product.setProductName(productName);
		product.setProductLine(productLine);
		product.setProductScale(productScale);
		product.setProductVendor(productVendor);
		product.setProductDescription(productDescription);
		product.setQuantityInStock(quantityInStock);
		product.setBuyPrice(buyPrice);
		product.setMsrp(MSRP);

		daoProduct.insertProduct(product);

		response.sendRedirect(request.getContextPath() + "/Home");
	}

}
