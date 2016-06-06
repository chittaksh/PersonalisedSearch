package helper;

/* 
 * 
 * 
 */

import java.sql.*;


public class SQLHelper
{

	public static void getSQLClass() throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// Code to Create Connection
	public static Connection getConnection(connectionObject connObj)
			throws Exception
	{
		try
		{
			return DriverManager.getConnection(connObj.getServerLink(),
					connObj.getUsername(), connObj.getPassword());
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}

	// Code to Close Connection
	public static void closeConnection(Connection con) throws Exception
	{
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (Exception e)
			{
				throw e;
			}
		}
	}
}