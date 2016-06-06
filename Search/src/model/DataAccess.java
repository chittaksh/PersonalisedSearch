package model;

import java.net.URL;
import java.sql.*;
import java.util.*;
import helper.SQLHelper;
import helper.connectionObject;

public class DataAccess {

	// Connection Object
	static connectionObject connObj = new connectionObject(
			// "jdbc:mysql://localhost/search", "root", "root");
			"jdbc:mysql://localhost/search", "root", "cartoon");
	static Connection c = null;

	public static UserDetail tryLogin(String userEmail, String password) throws Exception {
		UserDetail userD = null;

		try {
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");

			while (rs.next()) {
				if (rs.getString("EMAIL").equals(userEmail) && rs.getString("PASS").equals(password)) {
					// Username and Password Verified.
					userD = new UserDetail(rs.getInt("ID"), Roles.getRole(rs.getInt("ROLE")), rs.getString("FIRSTNAME"),
							rs.getString("LASTNAME"), rs.getString("PASS"), rs.getString("EMAIL"),
							rs.getString("CONTACT"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getInt("ZIP"));
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}
		return userD;
	}

	public static int setUser(String firstName, String lastName, String eMail, String password, String contact,
			String address, String city, int zip) throws Exception {
		int a = 0;

		try {
			c = SQLHelper.getConnection(connObj);

			// Check if User Exist
			boolean UserExist = false;
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT EMAIL FROM USERS");

			while (rs.next()) {
				if (rs.getString("EMAIL").equals(eMail)) {
					UserExist = true;
					break;
				}
			}

			if (!UserExist) {
				// Insert User
				String insertTableSQL = "INSERT INTO USERS(ROLE, FIRSTNAME, LASTNAME, EMAIL, PASS, CONTACT, ADDRESS, CITY, ZIP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

				PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, 2);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, eMail);
				preparedStatement.setString(5, password);
				preparedStatement.setString(6, contact);
				preparedStatement.setString(7, address);
				preparedStatement.setString(8, city);
				preparedStatement.setInt(9, zip);

				a = preparedStatement.executeUpdate();
			} else {
				Exception e = new Exception("User Already Exist.");
				throw e;
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static List<UserDetail> getAllUsers() throws Exception {
		List<UserDetail> lstUserDetail = new ArrayList<UserDetail>();

		try {
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE ROLE=2;");

			while (rs.next()) {
				UserDetail userD = new UserDetail();
				// Username and Password Verified.
				userD = new UserDetail(rs.getInt("ID"), Roles.getRole(rs.getInt("ROLE")), rs.getString("FIRSTNAME"),
						rs.getString("LASTNAME"), rs.getString("PASS"), rs.getString("EMAIL"), rs.getString("CONTACT"),
						rs.getString("ADDRESS"), rs.getString("CITY"), rs.getInt("ZIP"));

				lstUserDetail.add(userD);
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return lstUserDetail;
	}

	public static UserDetail getUsrById(int id) throws Exception{
		UserDetail user = new UserDetail();
		
		try {
			c = SQLHelper.getConnection(connObj);

			String strStatement = "SELECT * FROM USERS WHERE ID=?;";
			
			PreparedStatement stmt = c.prepareStatement(strStatement);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Username and Password Verified.
				user = new UserDetail(rs.getInt("ID"), Roles.getRole(rs.getInt("ROLE")), rs.getString("FIRSTNAME"),
						rs.getString("LASTNAME"), rs.getString("PASS"), rs.getString("EMAIL"), rs.getString("CONTACT"),
						rs.getString("ADDRESS"), rs.getString("CITY"), rs.getInt("ZIP"));
				break;
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}
		
		return user;
	}
	
	public static int setUser(int id, String firstName, String lastName, String eMail, String password, String contact,
			String address, String city, int zip) throws Exception {
		int a = 0;

		try {
			c = SQLHelper.getConnection(connObj);

			// Insert User
			String insertTableSQL = "UPDATE USERS SET FIRSTNAME=?, LASTNAME=?, EMAIL=?, PASS=?, CONTACT=?, ADDRESS=?, CITY=?, ZIP=? WHERE ID=?";

			PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, eMail);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, contact);
			preparedStatement.setString(6, address);
			preparedStatement.setString(7, city);
			preparedStatement.setInt(8, zip);
			preparedStatement.setInt(9, id);

			a = preparedStatement.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static int setResult(UserDetail user, Result result) throws Exception {
		int a = 0;

		try {
			c = SQLHelper.getConnection(connObj);

			// Check if URL Exist
			boolean RecordExist = false;
			String chkWebSite = "SELECT * FROM LOCALRESULTS L WHERE L.URL = ?;";
			PreparedStatement stmt = c.prepareStatement(chkWebSite);
			stmt.setString(1, result.getUrl().toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				RecordExist = true;
				result.setId(rs.getInt("ID"));
			}

			if (!RecordExist) {
				// Code to store new record URL.
				String insertTableSQL = "INSERT INTO LOCALRESULTS(TITLE, METAKEYS, URL, DESCRIPTION, SERVICE) VALUES (?, ?, ?, ?, ?);";

				PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, result.getTitle());
				preparedStatement.setString(2, result.getMetakeys());
				preparedStatement.setString(3, result.getUrl().toString());
				preparedStatement.setString(4, result.getDesc());
				preparedStatement.setString(5, result.getService());

				a = preparedStatement.executeUpdate();

				String strID = "SELECT MAX(ID) AS ID FROM LOCALRESULTS;";
				PreparedStatement SP1 = c.prepareStatement(strID);
				ResultSet rs1 = SP1.executeQuery();

				while (rs1.next()) {
					result.setId(rs1.getInt("ID"));
				}

				String insertTableCount = "INSERT INTO URLUSERCOUNTER(USERID, URLID, COUNTER) VALUES(?, ?, ?);";

				PreparedStatement PS2 = c.prepareStatement(insertTableCount);
				PS2.setInt(1, user.getId());
				PS2.setInt(2, result.getId());
				PS2.setInt(3, result.getVisitCount() + 1);

				result.setVisitCount(result.getVisitCount() + 1);

				PS2.executeUpdate();
			} else {
				// Here if URL Does Exists. check if the user has visited
				// Earlier.
				boolean recordExists = false;

				String strStatement = "SELECT ID, USERID, URLID FROM URLUSERCOUNTER WHERE USERID=? && URLID=?;";

				PreparedStatement stmt1 = c.prepareStatement(strStatement);
				stmt1.setInt(1, user.getId());
				stmt1.setInt(2, result.getId());
				ResultSet rs2 = stmt1.executeQuery();

				while (rs2.next()) {
					recordExists = true;
				}

				if (recordExists) {
					// If the url exsists and the user has visited earlier.
					String updateTableCount = "UPDATE URLUSERCOUNTER SET COUNTER=? WHERE USERID=? && URLID=?";

					PreparedStatement PS2 = c.prepareStatement(updateTableCount);
					PS2.setInt(1, result.getVisitCount() + 1);
					PS2.setInt(2, user.getId());
					PS2.setInt(3, result.getId());

					result.setVisitCount(result.getVisitCount() + 1);

					PS2.executeUpdate();
				} else {
					// If the url exsists and the user has not visited earlier.
					String insertTableCount = "INSERT INTO URLUSERCOUNTER(USERID, URLID, COUNTER) VALUES(?, ?, ?);";

					PreparedStatement PS2 = c.prepareStatement(insertTableCount);
					PS2.setInt(1, user.getId());
					PS2.setInt(2, result.getId());
					PS2.setInt(3, result.getVisitCount() + 1);

					result.setVisitCount(result.getVisitCount() + 1);

					PS2.executeUpdate();
				}
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}
		return a;
	}

	public static List<Result> getResults(UserDetail user, String key) throws Exception {
		List<Result> lstResult = new ArrayList<Result>();

		try {
			c = SQLHelper.getConnection(connObj);

			String strQuery = "SELECT * FROM LOCALRESULTS L LEFT OUTER JOIN URLUSERCOUNTER U ON L.ID = U.URLID WHERE U.USERID = ? && L.METAKEYS LIKE ? ORDER BY COUNTER DESC;";

			PreparedStatement preparedStatement = c.prepareStatement(strQuery);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, key.replace(" ", "%"));

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Result result = new Result();
				result.setId(rs.getInt("URLID"));
				result.setOld(true);
				result.setUrl(new URL(rs.getString("URL")));
				result.setService(rs.getString("SERVICE"));
				result.setDesc(rs.getString("DESCRIPTION"));
				result.setMetakeys(rs.getString("METAKEYS"));
				result.setTitle(rs.getString("TITLE"));
				result.setVisitCount(rs.getInt("COUNTER"));
				lstResult.add(result);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);

		}

		return lstResult;
	}

	public static int setCounter(UserDetail user, String key) throws Exception {
		int a = 0;
		int id = 0;

		try {
			c = SQLHelper.getConnection(connObj);

			// Check if URL Exist
			boolean RecordExist = false;

			String chkKeyWord = "SELECT * FROM KEYWORDSLIST K WHERE K.KEYWORDS = ?;";
			PreparedStatement stmt = c.prepareStatement(chkKeyWord);
			stmt.setString(1, key.toLowerCase());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				RecordExist = true;
				id = rs.getInt("ID");
			}

			if (RecordExist) {

				boolean userSearched = false;

				String chkUserSearch = "SELECT ID FROM KEYWORDSCOUNTER KD WHERE KD.KEYID= ? && USERID=?";

				PreparedStatement Prep1 = c.prepareStatement(chkUserSearch);
				Prep1.setInt(1, id);
				Prep1.setInt(2, user.getId());
				ResultSet rs4 = Prep1.executeQuery();

				while (rs4.next()) {
					userSearched = true;
				}

				if (userSearched) {

					String updateKeyword = "UPDATE KEYWORDSCOUNTER SET COUNTER=COUNTER+1 WHERE USERID=? && KEYID=?;";

					PreparedStatement PS1 = c.prepareStatement(updateKeyword);
					PS1.setInt(1, user.getId());
					PS1.setInt(2, id);

					a = PS1.executeUpdate();
				} else {
					String updateKeyword = "INSERT INTO KEYWORDSCOUNTER(COUNTER, USERID, KEYID) VALUES (1, ?, ?);";
					PreparedStatement PS1 = c.prepareStatement(updateKeyword);
					PS1.setInt(1, user.getId());
					PS1.setInt(2, id);
					a = PS1.executeUpdate();
				}

			} else {

				String insertQuery = "INSERT INTO KEYWORDSLIST(KEYWORDS) VALUES(?);";

				PreparedStatement PS2 = c.prepareStatement(insertQuery);
				PS2.setString(1, key.toLowerCase());

				a = PS2.executeUpdate();

				String getKeyId = "SELECT MAX(ID) AS ID FROM KEYWORDSLIST;";
				PreparedStatement prep = c.prepareStatement(getKeyId);
				ResultSet rs3 = prep.executeQuery();

				while (rs3.next()) {
					id = rs3.getInt("ID");
				}

				String updateKeyword = "INSERT INTO KEYWORDSCOUNTER(COUNTER, USERID, KEYID) VALUES (1, ?, ?);";
				PreparedStatement PS1 = c.prepareStatement(updateKeyword);
				PS1.setInt(1, user.getId());
				PS1.setInt(2, id);
				a += PS1.executeUpdate();
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static List<UrlRecord> getReports() throws Exception {

		List<UrlRecord> records = new ArrayList<UrlRecord>();

		try {
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT DISTINCT ID, URL, (SELECT SUM(COUNTER) FROM URLUSERCOUNTER U WHERE L.ID = U.URLID) AS COUNTER FROM LOCALRESULTS L");
			while (rs.next()) {
				UrlRecord rec = new UrlRecord(rs.getInt("ID"), new URL(rs.getString("URL")), rs.getInt("COUNTER"));
				records.add(rec);
			}
			;

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}
		return records;
	}

	public static List<UrlRecord> getReportsByUser(UserDetail user) throws Exception {

		List<UrlRecord> records = new ArrayList<UrlRecord>();

		try {
			c = SQLHelper.getConnection(connObj);

			String strQuery = "SELECT DISTINCT ID, URL, (SELECT COALESCE(SUM(COUNTER), 0) FROM URLUSERCOUNTER U WHERE L.ID = U.URLID AND U.USERID = ?) AS COUNTER FROM LOCALRESULTS L";

			PreparedStatement stmt = c.prepareStatement(strQuery);
			stmt.setInt(1, user.getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("COUNTER") != 0) {
					UrlRecord rec = new UrlRecord(rs.getInt("ID"), new URL(rs.getString("URL")), rs.getInt("COUNTER"));
					records.add(rec);
				}
			}
			;

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return records;
	}

	public static List<KeyRecord> getKeyRecords() throws Exception {
		List<KeyRecord> lstRecords = new ArrayList<KeyRecord>();
		try {
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT DISTINCT ID, KEYWORDS, (SELECT SUM(COUNTER) FROM KEYWORDSCOUNTER U WHERE L.ID = U.KEYID) AS COUNTER FROM KEYWORDSLIST L");
			while (rs.next()) {
				KeyRecord rec = new KeyRecord(rs.getInt("ID"), rs.getString("KEYWORDS"), rs.getInt("COUNTER"));
				lstRecords.add(rec);
			}
			;

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return lstRecords;
	}

	public static List<KeyRecord> getKeyRecords(UserDetail user) throws Exception {
		List<KeyRecord> lstRecords = new ArrayList<KeyRecord>();
		try {
			c = SQLHelper.getConnection(connObj);

			String strQuery = "SELECT DISTINCT ID, KEYWORDS, (SELECT SUM(COUNTER) FROM KEYWORDSCOUNTER U WHERE L.ID = U.KEYID AND U.USERID = ?) AS COUNTER FROM KEYWORDSLIST L";

			PreparedStatement stmt = c.prepareStatement(strQuery);
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				KeyRecord rec = new KeyRecord(rs.getInt("ID"), rs.getString("KEYWORDS"), rs.getInt("COUNTER"));
				lstRecords.add(rec);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return lstRecords;
	}

	public static List<KeyRecord> getKeyRecordsForReport() throws Exception {
		List<KeyRecord> lstRecords = new ArrayList<KeyRecord>();
		try {
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT DISTINCT ID, KEYWORDS, (SELECT SUM(COUNTER) FROM KEYWORDSCOUNTER U WHERE L.ID = U.KEYID) AS COUNTER FROM KEYWORDSLIST L ORDER BY L.ID DESC LIMIT 10");
			while (rs.next()) {
				KeyRecord rec = new KeyRecord(rs.getInt("ID"), rs.getString("KEYWORDS"), rs.getInt("COUNTER"));
				lstRecords.add(rec);
			}
			;

		} catch (Exception e) {
			throw e;
		} finally {
			SQLHelper.closeConnection(c);
		}

		return lstRecords;
	}
}
