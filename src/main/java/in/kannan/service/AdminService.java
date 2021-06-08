package in.kannan.service;

import in.kannan.dao.AdminDAO;
import in.kannan.exception.AdminLoginException;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.Users;
import in.kannan.validator.AdminValidator;

public class AdminService {
	private AdminService() {

	}

	/**
	 * calls the DAO class after validation of input details
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 */
	public static Users checkAdminLogin(String email, String password) throws ServiceException {
		Users use = null;
		try {
			if (AdminValidator.isValidEmail(email) && AdminValidator.isValidPassword(password)) {
				use = AdminDAO.findByGmailAndPassword(email, password);
			} else {
				throw new ServiceException("Validation Failure");
			}
		} catch (DBException | AdminLoginException e) {

			throw new ServiceException(e, "Invalid Details");
		}
		return use;
	}

}
