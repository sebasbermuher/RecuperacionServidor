package org.iesalixar.servidor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOUsuarioImpl;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.utils.PasswordHashGenerator;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InicioServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("usuario");
		String password = request.getParameter("password");

		DAOUsuarioImpl dao = new DAOUsuarioImpl();

		if (email != null && password != null) {

			Usuario user = dao.getUsuario(email);

			if (user != null) {

				if (PasswordHashGenerator.checkPassword(password, user.getPassword())) {

					HttpSession sesion = request.getSession();

					sesion.setAttribute("usuario", user.getUsuario());
					sesion.setAttribute("email", user.getEmail());
					sesion.setAttribute("role", user.getRole());

					logger.log(Level.INFO, "El usuario " + user.getUsuario() + " con role " + user.getRole() + " se ha conectado correctamente.");

					response.sendRedirect(request.getContextPath() + "/Home");

				} else {
					request.setAttribute("error", "La contraseña es incorrecta.");

					logger.log(Level.WARN, "La contraseña es incorrecta.");

					doGet(request, response);

					return;
				}
			} else {
				request.setAttribute("error", "El usuario introducido no es correcto.");

				logger.log(Level.WARN, "El usuario introducido no es correcto.");

				doGet(request, response);

				return;
			}

		} else {

			request.setAttribute("error", "Login erroneo.");
			logger.log(Level.WARN, "Login erroneo.");

			doGet(request, response);

			return;

		}
	}

}