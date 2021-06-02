package in.kannan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DAOException;

public class ConnectionUtil {
	/**
	 * private constructor to hide public class
	 */
	private ConnectionUtil() {

	}

	private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "LOV8Y2000aLOV8Y2000a";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/MovieRating";

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
			throw new ConnectionException(e, "Unble to Connect");
		}

		return connection;
	}

	/**
	 * close connection for ResultSet ,PreparedStatement and Connection
	 * 
	 * @param rs  ResultSet variable
	 * @param ps  PreparedStatement variable
	 * @param con Connection variable
	 * @throws DAOException handles SQLException and throws as DAOException
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection con) throws DAOException {
		try {
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e, "Unable to close DAO");
		}
	}

}
