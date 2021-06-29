package in.kannan.dao;

import java.time.LocalDateTime;

import in.kannan.exception.DBException;
import in.kannan.model.User;

public interface UserDAO {
	/**
	 * This method returns the detail for the given email and password.
	 * 
	 * @param email    input the email
	 * @param password input the password
	 * @return the user list
	 * @throws DBException
	 */
	public User findByEmailAndPassword(String email, String password) throws DBException;

	/**
	 * This method returns the object which holds the role of the users.
	 * 
	 * @param userId
	 * @return
	 * @throws DBException
	 */

	public User findRole(Integer userId) throws DBException;

	/**
	 * This method saves the particular user data .
	 * 
	 * @param userName
	 * @param email
	 * @param password
	 * @param role
	 * @throws DBException
	 */

	public void save(User user) throws DBException;

	/**
	 * This method checks the presence of email for particular user.
	 * 
	 * @param email
	 * @return
	 * @throws DBException
	 */

	public User findByEmail(String email) throws DBException;

	/**
	 * This method updates the blocked column of a given user id to false
	 * 
	 * @param userId
	 * @throws DBException
	 */

	public void update(Integer userId, LocalDateTime modifiedDateTime) throws DBException;

	/**
	 * This method checks the given id with blocked column to get the data to login
	 * 
	 * @param email
	 * @return
	 * @throws DBException
	 */

	public User findByEmailAndBlocked(String email) throws DBException;

}