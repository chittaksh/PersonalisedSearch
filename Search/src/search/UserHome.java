package search;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import mungee.SortResults;
import services.BingSearch;
import services.GoogleSearch;

/**
 * Servlet implementation class UserHome
 */
@WebServlet("/search/UserHome")
public class UserHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserHome() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("UserHome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			HttpSession session = request.getSession();
			
			String searchTerm = request.getParameter("searchterm");
			
			DataAccess.setCounter((UserDetail)session.getAttribute("User"), searchTerm);

			List<Result> oldResult = DataAccess.getResults((UserDetail) session.getAttribute("User"), searchTerm);
			List<Result> resGoogle = GoogleSearch.main(searchTerm);
			List<Result> resBing = BingSearch.main(searchTerm);

			List<Result> cleanResults = SortResults.sortThree(oldResult, resGoogle, resBing);

			//request.setAttribute("cleanRecords", cleanResults);
			//request.setAttribute("oldRecords", oldResult);
			request.setAttribute("searchTerm", searchTerm);

			session.setAttribute("newResults", cleanResults);
			session.setAttribute("oldResults", oldResult);
			//session.setAttribute("searchTerm", searchTerm);
			
			//response.sendRedirect("UserHome.jsp");
			request.getRequestDispatcher("UserHome.jsp").include(request, response);
			
		} catch (Exception ex) {
			// Failed to Insert Record.
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("UserHome.jsp")
					.include(request, response);
		}
	}

}
