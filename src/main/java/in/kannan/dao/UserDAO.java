package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.kannan.exception.DBException;
import in.kannan.model.User;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;

public class UserDAO {
	private UserDAO() {
		// private constructor to hide the implicit class

	}

	/**
	 * returns the particular row
	 * 
	 * @param email    input the email
	 * @param password input the password
	 * @return the user list
	 * @throws DBException
	 */
	public static User findByEmailAndPassword(String email, String password) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		connection = ConnectionUtil.getConnection();
		try {
			String sql = "select id,name,email,role from users where email = ? and password = ?";

			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {

				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String role = rs.getString("role");
				String email1 = rs.getString("email");
				user = new User(id, name, email1, role);
			}
		}

		catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, "Unable to fetch the details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return user;

	}
}
