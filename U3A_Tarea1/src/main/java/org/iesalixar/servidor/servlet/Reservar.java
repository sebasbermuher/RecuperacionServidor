package org.iesalixar.servidor.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.model.Reserva;

/**
 * Servlet implementation class Reservar
 */
@WebServlet("/Reservar")
public class Reservar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reservar() {
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

		HttpSession sesion = request.getSession();

		if (!sesion.isNew() && sesion.getAttribute("LOGEADO") != null && ((boolean) sesion.getAttribute("LOGEADO"))) {
			request.getRequestDispatcher("WEB-INF/view/reservar.jsp").forward(request, response);

			return;
		}

		sesion.invalidate();
		response.sendRedirect(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		if (!session.isNew() && session.getAttribute("LOGEADO") != null
				&& ((boolean) session.getAttribute("LOGEADO"))) {

			if (request.getParameter("reservar") != null && request.getParameter("reservar").equals("RESERVAR")) {

				if (request.getParameter("fechaInicio") != null && request.getParameter("fechaFin") != null
						&& request.getParameter("numPersonas") != null) {

					String fInicio = request.getParameter("fInicio");
					String fFin = request.getParameter("fFin");
					int numPer = Integer.parseInt(request.getParameter("numPer"));
					String[] checkbox = request.getParameterValues("checkbox");

					Reserva registro = new Reserva();

					registro.setfInicio(fInicio);
					registro.setfFin(fFin);
					registro.setNumPer(numPer);
					registro.setServicios(checkbox);

					session.setAttribute("userBean", registro);
					
					session.setAttribute("fInicio",fInicio);
				}

				response.sendRedirect(request.getContextPath() + "/Confirmar");
				return;

			} else {

				response.sendRedirect(request.getContextPath() + "/Reservar");
				return;

			}

		}

	}

}