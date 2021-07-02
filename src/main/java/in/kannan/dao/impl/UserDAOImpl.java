package in.kannan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import in.kannan.constants.UserRoleEnum;
import in.kannan.dao.UserDAO;
import in.kannan.exception.DBException;
import in.kannan.model.User;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;
import in.kannan.util.MessageConstant;

public class UserDAOImpl implements UserDAO {

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String ROLE = "role";
	public static final String ID = "user_id";

	/**
	 * This method returns the detail for the given email and password.
	 * 
	 * @param email    input the email
	 * @param password input the password
	 * @return the user list
	 * @throws DBException
	 */
	@Override
	public User findByEmailAndPassword(String email, String password) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		connection = ConnectionUtil.getConnection();
		try {
			String sql = "select user_id,name,email,role from users where email = ? and password = ?";

			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {

				Integer id = rs.getInt(ID);
				String name = rs.getString(NAME);
				String role = rs.getString(ROLE);
				UserRoleEnum userRole = UserRoleEnum.valueOf(role);
				String userEmail = rs.getString(EMAIL);
				user = new User(id, name, userEmail, userRole);
			}
		}

		catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return user;

	}

	/**
	 * This method returns the object which holds the role of the users.
	 * 
	 * @param userId
	 * @return
	 * @throws DBException
	 */
	@Override
	public User findRole(Integer userId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select role from users where user_id = ?";

			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);

			rs = pst.executeQuery();

			if (rs.next()) {

				String roleStr = rs.getString(ROLE);
				UserRoleEnum role = UserRoleEnum.valueOf(roleStr);
				user = new User(role);
			}
		}

		catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return user;

	}

	/**
	 * This method saves the particular user data .
	 * 
	 * @param userName
	 * @param email
	 * @param password
	 * @param role
	 * @throws DBException
	 */
	@Override
	public void save(User user) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into users (name,email,password,role) values (?,?,?,?)";
			pst = connection.prepareStatement(sql);
			String userName = user.getName();
			String email = user.getEmail();
			String password = user.getPassword();
			String role = user.getRole().toString();
			pst.setString(1, userName);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.setString(4, role);
			pst.execute();
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_SAVE_USER);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method checks the presence of email for particular user.
	 * 
	 * @param email
	 * @return
	 * @throws DBException
	 */
	@Override
	public User findByEmail(String email) throws DBException {
		User user = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select email,password from users where email =? ";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				String mailId = rs.getString(EMAIL);
				String password = rs.getString(PASSWORD);
				user = new User(mailId, password);

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return user;
	}

	/**
	 * This method updates the blocked column of a given user id to false
	 * 
	 * @param userId
	 * @throws DBException
	 */
	@Override
	public void update(Integer userId, LocalDateTime modifiedDateTime) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update users set blocked = true, blocked_date_time =? where user_id = ?";
			pst = connection.prepareStatement(sql);

			Timestamp dateTime = Timestamp.valueOf(modifiedDateTime);
			pst.setTimestamp(1, dateTime);
			pst.setInt(2, userId);
			int rows = pst.executeUpdate();
			if (rows == 1) {
				Logger.message(MessageConstant.UPDATEMESSAGE);
			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_UPDATE_USER);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method checks the given id with blocked column to get the data to login
	 * 
	 * @param email
	 * @return
	 * @throws DBException
	 */
	@Override
	public User findByEmailAndBlocked(String email) throws DBException {
		User user = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select email,password from users where email =? and blocked = true ";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				String mailId = rs.getString(EMAIL);
				String password = rs.getString(PASSWORD);
				user = new User(mailId, password);

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return user;
	}

}
