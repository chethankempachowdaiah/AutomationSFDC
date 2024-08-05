package com.framework.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.framework.base.MyListener;

public class DatabaseConnect extends MyListener {

	public static ResultSet connect_DB(String db_Query)
			throws ClassNotFoundException, SQLException, IOException, InterruptedException {

		// Fetching the inputs to connect to database
		String strURL = "";
		String port = "";
		String service_Name = "";
		String strUserID = "";
		String strPassword = "";

		String dbURL = "jdbc:oracle:thin:" + strURL + ":" + port + ":" + service_Name;

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String DB_result = null;

		

			// Creating the connection to database and executing the given query
			Class.forName("");
			
			try {
			con = DriverManager.getConnection(dbURL, strUserID, strPassword);
			stmt = con.prepareStatement(db_Query);
			stmt.executeUpdate();
			rs = stmt.executeQuery(db_Query);

			// fetching the data from result set
			while (rs.next()) {
				DB_result = rs.getString(1);
				System.out.println("Fetched " + DB_result + " from Database");
				break;
			}

			// Closing the db connection
			closeDBConnection(con, stmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// Closing the db connection
			closeDBConnection(con, stmt, rs);
		}
		return rs;

	}

	public static void closeDBConnection(Connection con, Statement stmt, ResultSet rs) {

		// closing DB Connection
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
			}

		}
	}

}
