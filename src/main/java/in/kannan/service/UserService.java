package in.kannan.service;

import in.kannan.dao.UserDAO;
import in.kannan.validator.UserValidator;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.User;
import in.kannan.util.Logger;

public class UserService {
	private UserService() {
		// private constructor to hide the implicit class
	}

	/**
	 * This method is used for log in by the user .It checks the validity of email
	 * and password, checks for correct email and password and also checks the user
	 * role as only ADMIN could login.
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static User adminLogin(String email, String password) throws ServiceException, ValidationException {
		User user = null;
		try {

			user = UserDAO.findByEmailAndPassword(email, password);

			if (user == null) {
				throw new ServiceException("Invalid Login credentialls");
			} else if (!user.getRole().equals("ADMIN")) {
				throw new ServiceException("Only ADMIN could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, "Unable to fetch the user details");
		}

		return user;
	}

	/**
	 * This method is used for log in by the user .It checks the validity of email
	 * and password, checks for correct email and password and also checks the user
	 * role as only USERS could login.
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static User userLogin(String email, String password) throws ServiceException, ValidationException {
		User user = null;
		try {

			user = UserDAO.findByEmailAndPassword(email, password);

			if (user == null) {
				throw new ServiceException("Invalid Login credentials");
			} else if (!user.getRole().equals("USER")) {
				throw new ServiceException("Only USER could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, "Unable to fetch the user details");
		}

		return user;
	}

	public static void userRegistration(String userName, String email, String password, String role)
			throws ValidationException, ServiceException {
		try {
			UserValidator.validateUserDetails(userName, email, password, role);
			UserDAO.save(userName, email, password, role);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry Unable to register");
		}

	}

}
