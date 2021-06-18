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
	 * role as only user could login.
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static User adminLogin(String email, String password) throws ServiceException, ValidationException {
		User user = null;
		try {

			UserValidator.validateLoginCredentials(email, password);

			user = UserDAO.findByEmailAndPassword(email, password);

			if (user == null) {
				throw new ServiceException("Invalid Login details");
			} else if (!user.getRole().equals("ADMIN")) {
				throw new ServiceException("Only ADMIN could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, "Unable to fetch the user details");
		}

		return user;
	}

}
