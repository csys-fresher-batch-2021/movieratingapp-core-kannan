package in.kannan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;

public class ConnectionUtil {
	/**
	 * private constructor to hide public class
	 */
	private ConnectionUtil() {

	}

	private static final String DRIVER_CLASS_NAME = System.getenv("spring.datasource.driver-class-name");
	private static final String DB_URL = System.getenv("spring.datasource.url");
	private static final String DB_USERNAME = System.getenv("spring.datasource.username");
	private static final String DB_PASSWORD = System.getenv("spring.datasource.password");

	/**
	 * gets connection to database localhost
	 * 
	 * @return connection
	 * @throws ConnectionException ClassNotFoundException and SQLException is caught
	 *                             and thrown as ConnectionException
	 */

	public static Connection getConnection() throws ConnectionException {
		Connection connection;
		try {
			Class.forName(DRIVER_CLASS_NAME);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException(e, "Unable to Connect");
		}

		return connection;
	}

	/**
	 * close connection for ResultSet ,PreparedStatement and Connection
	 * 
	 * @param rs  ResultSet variable
	 * @param ps  PreparedStatement variable
	 * @param con Connection variable
	 * @throws DBException handles SQLException and throws as DAOException
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection con) throws DBException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {

				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

}
