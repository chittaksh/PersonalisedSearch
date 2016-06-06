package search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataAccess;

/**
 * Servlet implementation class Register
 */
@WebServlet("/search/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strFirstName = request.getParameter("firstname").trim();
		String strLastName = request.getParameter("lastname").trim();
		String strEmail = request.getParameter("email").trim();
		String strPassword = request.getParameter("password").trim();
		String strConPassword = request.getParameter("conpassword").trim();
		String strContact = request.getParameter("contact");
		String strAddress = request.getParameter("address").trim();
		String strCity = request.getParameter("city").trim();
		String strZip = request.getParameter("zip").trim();

		// Check password and Confirm Password
		if (strPassword.equals(strConPassword))
		{
			try
			{
				DataAccess.setUser(strFirstName, strLastName,  strEmail, strPassword, strContact, strAddress, strCity, Integer.parseInt(strZip));

				response.sendRedirect("Login");
			}
			catch (Exception ex)
			{
				// Failed to Insert Record.
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("Register.jsp")
						.include(request, response);
			}
		}
		else
		{
			// Password and Confirm Password are different.
			request.setAttribute("errorMessage",
					"Password and confirm Password did not match.");
			request.getRequestDispatcher("Register.jsp")
					.include(request, response);
		}
	}

}
