package in.kannan.service;

import java.time.LocalDateTime;

import in.kannan.dao.DAOFactory;
import in.kannan.dao.UserDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.User;
import in.kannan.util.Logger;
import in.kannan.util.MessageConstant;
import in.kannan.validator.RatingValidator;
import in.kannan.validator.UserValidator;

public class UserService {
	private UserService() {
		// private constructor to hide the implicit class
	}

	private static UserDAO userDAO = DAOFactory.getUserDAOInstance();
	private static UserRatingDAO userRatingDAO = DAOFactory.getUserRatingDAOInstance();

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

			user = userDAO.findByEmailAndPassword(email, password);

			if (user == null) {
				throw new ServiceException("Invalid Login credentialls");
			} else if (!user.getRole().equals("ADMIN")) {
				throw new ServiceException("Only ADMIN could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, e.getMessage());
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

			user = userDAO.findByEmailAndPassword(email, password);
			User fakeUser = userDAO.findByEmailAndBlocked(email);
			if (fakeUser != null) {
				throw new ServiceException(MessageConstant.BLOCKMESSAGE);
			}

			if (user == null) {
				throw new ServiceException("Invalid Login credentials");
			} else if (!user.getRole().equals("USER")) {
				throw new ServiceException("Only USER could login");
			}

		} catch (DBException e) {
			Logger.trace(e);

			throw new ServiceException(e, e.getMessage());
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

			User userEmail = userDAO.findByEmail(email);
			if (userEmail != null) {
				throw new ServiceException(MessageConstant.ALREADYREGISTERED);
			}
			UserValidator.validateUserDetails(userName, email, password, role);
			user = new User();
			user.setName(userName);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);

			userDAO.save(user);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
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
			LocalDateTime blockedDateTime = LocalDateTime.now();
			userDAO.update(userId, blockedDateTime);
			userRatingDAO.update(userId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
		}
	}

}
