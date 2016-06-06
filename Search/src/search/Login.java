package search;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.SQLHelper;
import model.DataAccess;
import model.Roles;
import model.UserDetail;

/**
 * Servlet implementation class Login
 */
@WebServlet("/search/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		getServletContext().setAttribute("Roles", Roles.values());

		try {
			SQLHelper.getSQLClass();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strEmail = request.getParameter("email").trim();
		String strPassword = request.getParameter("password").trim();

		try {
			UserDetail User = DataAccess.tryLogin(strEmail, strPassword);
			if (User != null) {
				HttpSession session = request.getSession();
				session.setAttribute("User", User);

				if (User.getRole().equals(Roles.Manager)) {
					response.sendRedirect("ManagerHome");
					return;
				} else {
					response.sendRedirect("UserHome");
					return;
				}
			} else {
				// Failed to find the User.
				request.setAttribute("errorMessage", "Invalid E-mail or Password. Please try again.");
				request.getRequestDispatcher("Login.jsp").include(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("Login.jsp").include(request, response);
		}
	}
}
