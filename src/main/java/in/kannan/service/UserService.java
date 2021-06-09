package in.kannan.service;

import in.kannan.dao.UserDAO;
import in.kannan.validator.UserValidator;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.User;
import in.kannan.util.Logger;

public class UserService {
	private UserService() {
		// private constructor to hide the implicit class
	}

	/**
	 * calls the DAO class and use the DAO output here
	 * 
	 * @param email    input the email
	 * @param password input the password
	 * @throws ServiceException
	 */
	public static User adminLogin(String email, String password) throws ServiceException {
		User user = null;
		try {

			UserValidator.validate(email, password);

			user = UserDAO.findByEmailAndPassword(email, password);

			if (user == null) {
				throw new ServiceException("Invalid Login Credentials");
			} else if (!user.getRole().equals("ADMIN")) {
				throw new ServiceException("Only ADMIN could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, "Unable to call the DAO");
		}

		return user;
	}

}
