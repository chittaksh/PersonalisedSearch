package search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataAccess;
import model.Result;
import model.UserDetail;
import mungee.SortResults;
import services.BingSearch;
import services.GoogleSearch;

/**
 * Servlet implementation class ManagerHome
 */
@WebServlet("/search/ManagerHome")
public class ManagerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerHome() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.sendRedirect("ManagerHome.jsp");
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

			DataAccess.setCounter((UserDetail) session.getAttribute("User"), searchTerm);

			List<Result> oldResult = DataAccess.getResults((UserDetail) session.getAttribute("User"), searchTerm);
			List<Result> resGoogle = GoogleSearch.main(searchTerm);
			List<Result> resBing = BingSearch.main(searchTerm);

			List<Result> cleanResults = SortResults.sortThree(oldResult, resGoogle, resBing);

			// request.setAttribute("cleanRecords", cleanResults);
			// request.setAttribute("oldRecords", oldResult);
			request.setAttribute("searchTerm", searchTerm);

			session.setAttribute("newResults", cleanResults);
			session.setAttribute("oldResults", oldResult);
			// session.setAttribute("searchTerm", searchTerm);

			// response.sendRedirect("UserHome.jsp");
			request.getRequestDispatcher("ManagerHome.jsp").include(request, response);

		} catch (Exception ex) {
			// Failed to Insert Record.
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("ManagerHome.jsp").include(request, response);
		}
	}
}
