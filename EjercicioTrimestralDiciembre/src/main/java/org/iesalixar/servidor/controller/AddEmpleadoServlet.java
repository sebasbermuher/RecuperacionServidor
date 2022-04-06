package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOEmployeeImpl;
import org.iesalixar.servidor.dao.DAOOficinaImpl;
import org.iesalixar.servidor.model.Employee;
import org.iesalixar.servidor.model.Oficinas;

/**
 * Servlet implementation class AddEmpleadoServlet
 */
public class AddEmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AddEmpleadoServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmpleadoServlet() {
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
		DAOOficinaImpl daoOfficeImpl = new DAOOficinaImpl();

		ArrayList<Employee> empleados = daoImpl.getAllEmployees();
		ArrayList<Oficinas> oficinas = daoOfficeImpl.getAllOficinas();

		request.setAttribute("empleados", empleados);
		request.setAttribute("oficinas", oficinas);

		request.getRequestDispatcher("../WEB-INF/view/admin/addEmpleado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numEmpleado = Integer.parseInt(request.getParameter("employeeNumber"));
		String nombre = request.getParameter("firstName");
		String apellido = request.getParameter("lastName");
		String extension = request.getParameter("extension");
		String puesto = request.getParameter("jobTitle");
		String mail = request.getParameter("mail");
		String oficina = request.getParameter("officeCode");
		int jefe = Integer.parseInt(request.getParameter("jefe"));

		if (numEmpleado >= 0 && jefe >= 0 && nombre != null && apellido != null && extension != null && puesto != null
				&& oficina != null) {
			Employee empleado = new Employee();
			DAOEmployeeImpl daoImpl = new DAOEmployeeImpl();

			empleado.setEmployeeNumber(numEmpleado);
			empleado.setLastName(apellido);
			empleado.setFirstName(nombre);
			empleado.setExtension(extension);
		 	empleado.setEmail(mail);
			empleado.setOfficeCode(oficina);
			empleado.setReportsTo(jefe);
			empleado.setJobTitle(puesto);

			daoImpl.insertEmployee(empleado);
			
			logger.log(Level.INFO, "El empleado " + empleado.getFirstName() + " " + empleado.getLastName()  + " se ha añadido correctamente.");

			response.sendRedirect(request.getContextPath() + "/Admin/Empleados");

		}
	}
}