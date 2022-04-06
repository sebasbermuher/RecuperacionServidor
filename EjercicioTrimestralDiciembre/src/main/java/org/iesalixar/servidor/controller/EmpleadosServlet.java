package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.servidor.dao.DAOEmployeeImpl;
import org.iesalixar.servidor.dao.DAOOficinaImpl;
import org.iesalixar.servidor.model.Employee;

/**
 * Servlet implementation class EmpleadosServlet
 */
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpleadosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOEmployeeImpl daoImpl = new DAOEmployeeImpl();
		DAOOficinaImpl dao = new DAOOficinaImpl();

		request.setAttribute("offices", dao.getAllOficinas());

		ArrayList<Employee> listaEmployees = (ArrayList) daoImpl.getAllEmployees();

		request.setAttribute("employee", listaEmployees);

		request.getRequestDispatcher("../WEB-INF/view/admin/mostrarEmpleados.jsp").forward(request, response);
	}

}
