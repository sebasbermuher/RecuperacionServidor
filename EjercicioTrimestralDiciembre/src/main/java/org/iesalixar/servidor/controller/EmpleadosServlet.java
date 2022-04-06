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
import org.iesalixar.servidor.model.Oficinas;

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
		DAOEmployeeImpl daoEmployee = new DAOEmployeeImpl();
		ArrayList<Employee> listaEmployees = (ArrayList) daoEmployee.getAllEmployees();
				
		DAOOficinaImpl daoOficina = new DAOOficinaImpl();
		ArrayList<Oficinas> oficinas = daoOficina.getAllOficinas();

		request.setAttribute("employee", listaEmployees);
		request.setAttribute("offices", oficinas);
		
		request.getRequestDispatcher("../WEB-INF/view/admin/mostrarEmpleados.jsp").forward(request, response);
	}

}
