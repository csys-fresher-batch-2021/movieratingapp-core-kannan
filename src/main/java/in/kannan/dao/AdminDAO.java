package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.kannan.exception.AdminLoginException;
import in.kannan.exception.DBException;
import in.kannan.model.Users;
import in.kannan.util.ConnectionUtil;

public class AdminDAO {
	private AdminDAO() {
		// private constructor to hide the implicit class

	}

	/**
	 * finds the correct admin to login
	 * 
	 * @param email
	 * @param password
	 * @throws DBException
	 * @throws AdminLoginException
	 */
	public static Users findByGmailAndPassword(String email, String password) throws DBException, AdminLoginException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Users use = null;
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

				if (role.equals("Admin")) {

					use = new Users(id, name, email1, role);

				} else {
					throw new AdminLoginException("Only Admin could login");
				}

			}
			if (use == null) {

				throw new AdminLoginException("Null Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't fetch the information");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return use;

	}

}
