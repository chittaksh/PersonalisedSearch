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

/**
 * Servlet implementation class RedirectTo
 */
@WebServlet("/search/RedirectTo")
public class RedirectTo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectTo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		try {
			
			String status = request.getParameter("status");
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession session = request.getSession();
			List<Result> results;
			Result rs = new Result();
			UserDetail user = (UserDetail) session.getAttribute("User");

			if (status.equals("new")) {

				results = (List<Result>) session.getAttribute("newResults");
				rs = results.get(id);
				
			} else {
				results = (List<Result>) session.getAttribute("oldResults");
				for(Result res:results){
					if(res.getId()==id){
						rs = res;
					}
				}
			}

			DataAccess.setResult(user, rs);

			if (rs.getUrl().toString().contains("http")) {
				response.sendRedirect(rs.getUrl().toString());
			} else {
				response.sendRedirect("http://" + rs.getUrl().toString());
			}

		} catch (Exception ex) {
			// Code to handle error.
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
