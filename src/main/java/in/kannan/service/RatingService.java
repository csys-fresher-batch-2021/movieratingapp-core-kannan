package in.kannan.service;

import in.kannan.dao.UserDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.User;
import in.kannan.model.UserRating;
import in.kannan.util.Logger;
import in.kannan.validator.RatingValidator;

public class RatingService {
	private RatingService() {
		// private constructor to hide the existing class
	}

	/**
	 * This method is used to add the users rating.It validates the input details,
	 * check whether the user the rated for the particular movie before ,finds the
	 * role of the user then add their rating and also update the rating in another
	 * field.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ServiceException
	 */

	public static void addUsersRating(Integer userId, Integer movieId, Integer rating) throws ServiceException {
		try {
			RatingValidator.validateRating(userId, movieId, rating);
			UserRating userRating = UserRatingDAO.exist(userId, movieId);
			if (userRating != null) {
				throw new ServiceException("You already Rated");
			}
			User user = UserDAO.findRole(userId);
			if (!user.getRole().equals("USER")) {
				throw new ServiceException("Only users could rate the app");
			}

			UserRatingDAO.save(userId, movieId, rating);

		} catch (ValidationException | DBException e) {
			Logger.trace(e);
			throw new ServiceException("Rating Should be between 0 and 10");

		}

	}

	/**
	 * This method just deletes the movieId and userID data
	 * 
	 * @param userID
	 * @param movieId
	 * @throws ServiceException
	 */
	public static void undoRating(Integer userID, Integer movieId) throws ServiceException {
		try {
			UserRatingDAO.remove(userID, movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Unable to Update");
		}
	}

}
