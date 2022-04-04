package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOProductImpl;
import org.iesalixar.servidor.dao.DAOProductLineImpl;
import org.iesalixar.servidor.model.ProductLine;
import org.iesalixar.servidor.model.Productos;

/**
 * Servlet implementation class EditarProductServlet
 */
public class EditarProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EditarProductServlet.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		DAOProductImpl dao = new DAOProductImpl();
		DAOProductLineImpl daoProductLine = new DAOProductLineImpl();
		
		//Para mostrar los valores del select de productLine
		List<Productos> products = dao.getAllProducts();
		List<ProductLine> productLine = daoProductLine.getAllProductLine();
		request.setAttribute("products", products);
		request.setAttribute("productLine", productLine);

				
		if (!sesion.isNew()) {

			Productos producto = dao.getProducts(request.getParameter("productCode"));
			
			sesion.setAttribute("product", producto);
			
			request.getRequestDispatcher("../WEB-INF/view/admin/updateProduct.jsp").forward(request, response);

			return;
		}

		sesion.invalidate();
		response.sendRedirect(request.getContextPath() + "/");
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productCode = request.getParameter("productCode");
		String productName = request.getParameter("productName");
		String productLine = request.getParameter("productLine");
		String productScale = request.getParameter("productScale");
		String productVendor = request.getParameter("productVendor");
		String productDescription = request.getParameter("productDescription");
		int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
		Double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
		Double msrp = Double.parseDouble(request.getParameter("msrp"));

		
		DAOProductImpl dao = new DAOProductImpl();

		Productos product = dao.getProducts(productCode);
		
		product.setProductName(productName);
		product.setProductLine(productLine);
		product.setProductScale(productScale);
		product.setProductVendor(productVendor);
		product.setProductDescription(productDescription);
		product.setQuantityInStock(quantityInStock);
		product.setBuyPrice(buyPrice);
		product.setMsrp(msrp);

		dao.updateProduct(product);

		response.sendRedirect(request.getContextPath() + "/Admin/Inicio");
		
		logger.log(Level.INFO, "El producto se ha editado correctamente.");

	}

}