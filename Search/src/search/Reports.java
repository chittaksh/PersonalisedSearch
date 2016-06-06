package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataAccess;
import model.KeyRecord;
import model.UrlRecord;
import model.UserDetail;

/**
 * Servlet implementation class Reports
 */
@WebServlet("/search/Reports")
public class Reports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reports() {
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
		try {
			HttpSession session = request.getSession();

			UserDetail user = (UserDetail) session.getAttribute("User");
			List<UrlRecord> records = DataAccess.getReportsByUser(user);
			List<UrlRecord> filteredRecords = new ArrayList<UrlRecord>();
			List<KeyRecord> keyRecords = DataAccess.getKeyRecords(user);

			for (UrlRecord re : records) {
				boolean exists = false;
				for (UrlRecord reco : filteredRecords) {
					if (re.getUrl().getAuthority().equals(reco.getUrl().getAuthority())) {
						exists = true;
						reco.setCount(reco.getCount() + re.getCount());
					}
				}
				if (!exists) {
					filteredRecords.add(re);
				} else {

				}
			}

			request.setAttribute("records", filteredRecords);
			request.setAttribute("keyRecords", keyRecords);
			request.setAttribute("userList", DataAccess.getAllUsers());

			request.getRequestDispatcher("Reports.jsp").include(request, response);
		} catch (Exception ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("Reports.jsp").include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			HttpSession session = request.getSession();
			
			int userId = Integer.parseInt((String) request.getParameter("selUser"));

			UserDetail user;
			List<UrlRecord> records;
			List<UrlRecord> filteredRecords = new ArrayList<UrlRecord>();
			List<KeyRecord> keyRecords;
				
			if(userId==0){
				user = (UserDetail) session.getAttribute("User");
				records= DataAccess.getReportsByUser(user);
				keyRecords = DataAccess.getKeyRecords(user);
			}else{
				user = (UserDetail) DataAccess.getUsrById(userId);
				records= DataAccess.getReportsByUser(user);
				keyRecords = DataAccess.getKeyRecords(user);
			}

			for (UrlRecord re : records) {
				boolean exists = false;
				for (UrlRecord reco : filteredRecords) {
					if (re.getUrl().getAuthority().equals(reco.getUrl().getAuthority())) {
						exists = true;
						reco.setCount(reco.getCount() + re.getCount());
					}
				}
				if (!exists) {
					filteredRecords.add(re);
				} else {
					
				}
			}

			request.setAttribute("records", filteredRecords);
			request.setAttribute("keyRecords", keyRecords);
			request.setAttribute("userList", DataAccess.getAllUsers());
			request.setAttribute("userId", userId);

			request.getRequestDispatcher("Reports.jsp").include(request, response);
		} catch (Exception ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("Reports.jsp").include(request, response);
		}
	}

}
