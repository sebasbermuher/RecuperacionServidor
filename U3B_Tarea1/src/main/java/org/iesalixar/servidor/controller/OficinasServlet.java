package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.dao.DAOOficinaImpl;
import org.iesalixar.servidor.model.Oficinas;

/**
 * Servlet implementation class OficinasServlet
 */

public class OficinasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OficinasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOOficinaImpl dao = new DAOOficinaImpl();
		String oficina = request.getParameter("infoOficina");
		request.setAttribute("varOficinas", dao.getAllOficinas());
		request.setAttribute("oficinas", dao.getOffice(oficina));

		request.getRequestDispatcher("/WEB-INF/view/oficinas.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("codeOficina");
		response.sendRedirect(request.getContextPath() + "/Oficinas?infoOficina=" + code);
	}
}
