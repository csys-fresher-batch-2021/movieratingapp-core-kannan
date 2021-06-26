package in.kannan.service;

import in.kannan.dao.UserDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.User;
import in.kannan.util.Logger;
import in.kannan.validator.RatingValidator;
import in.kannan.validator.UserValidator;

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
	 */
	public static User adminLogin(String email, String password) throws ServiceException {
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

			throw new ServiceException(e.getMessage());
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
	 */
	public static User userLogin(String email, String password) throws ServiceException {
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

			throw new ServiceException(e.getMessage());
		}

		return user;
	}

	/**
	 * This method registers the particular user in the data
	 * 
	 * @param userName
	 * @param email
	 * @param password
	 * @param role
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public static void userRegistration(String userName, String email, String password, String role)
			throws ValidationException, ServiceException {
		User user = null;
		try {
			UserValidator.validateUserDetails(userName, email, password, role);
			user = new User();
			user.setName(userName);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);

			UserDAO.save(user);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * This method is used to block the fake user.
	 * 
	 * @param userId
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void blockUser(Integer userId) throws ValidationException, ServiceException {
		RatingValidator.validateId(userId);
		try {
			UserDAO.updateBlockedByUserId(userId);
			UserRatingDAO.updateActiveByUserId(userId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e.getMessage());
		}
	}

}
