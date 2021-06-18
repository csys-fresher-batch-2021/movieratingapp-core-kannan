package in.kannan.service;

import java.util.List;

import in.kannan.dao.MovieRatingDAO;
import in.kannan.dao.UserDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.MovieRating;
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
			updateRating(movieId);

		} catch (ValidationException | DBException e) {
			Logger.trace(e);
			throw new ServiceException("Failed to add your rating");

		}

	}

	/**
	 * This method fetch the average rating in one field and update them in another
	 * field.
	 * 
	 * @param id
	 * @throws ServiceException
	 */

	public static void updateRating(Integer id) throws ServiceException {
		try {
			List<MovieRating> averageRatingList = UserRatingDAO.findAverageRating();
			for (MovieRating movieRating : averageRatingList) {
				if (movieRating.getMovieId().equals(id)) {
					Double rating = movieRating.getRating();
					Integer movieId = movieRating.getMovieId();
					MovieRatingDAO.update(rating, movieId);
				}

			}
		} catch (DBException e) {

			Logger.trace(e);
			throw new ServiceException("Failed to update the rating");
		}

	}
}
