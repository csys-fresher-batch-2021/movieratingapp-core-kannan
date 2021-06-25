package in.kannan.service;

import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.dao.UserDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.dto.MovieRatingDTO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.User;
import in.kannan.model.UserRating;
import in.kannan.util.Logger;
import in.kannan.validator.MovieValidator;
import in.kannan.validator.RatingValidator;

public class RatingService {
	private RatingService() {
		// private constructor to hide the existing class
	}

	/**
	 * This method is used to add the users rating.It validates the input details,
	 * check whether the user the rated for the particular movie before ,finds the
	 * role of the user then add their rating.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ServiceException
	 */

	public static void addUserRating(Integer userId, Integer movieId, Integer rating) throws ServiceException {
		try {
			RatingValidator.validateRating(userId, movieId, rating);
			UserRating userRating = UserRatingDAO.findUserIdByUserIdAndMovieId(userId, movieId);
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
			throw new ServiceException("Rating Should be between 1 and 5");

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
			UserRatingDAO.removeByUserIdAndMovieId(userID, movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Unable to Update");
		}
	}

	/**
	 * This method counts the number of users rated for the particular movie.
	 * Initially it trims the movie name and validates it.If the movie name not
	 * present it throws the exception as movie not found.
	 * 
	 * @param movieName
	 * @return number of users rated for the particular movie.
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public static Integer countUsersRated(String movieName) throws ValidationException, ServiceException {
		String newMovieName = movieName.trim();
		MovieValidator.validateMovieName(newMovieName);
		Integer count = null;
		try {
			Integer movieId = MovieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie Not found");
			}
			count = UserRatingDAO.countRatingByMovieId(movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to count the user ratings for this movie");
		}
		return count;
	}

	/**
	 * This method counts number of user rated for the particular movie and
	 * particular rating.
	 * 
	 * @param rating
	 * @param movieName
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public static Integer countRatingByRatingAndMovieName(Integer rating, String movieName)
			throws ValidationException, ServiceException {
		RatingValidator.validateRating(rating, movieName);
		Integer count = null;
		String newMovieName = movieName.trim();
		try {
			Integer movieId = MovieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie Not found");
			}
			count = UserRatingDAO.countRatingByRatingAndMovieId(rating, movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to count the particular rating for particular movie ");
		}
		return count;

	}

	/**
	 * This method returns the list as number of user rated for the particular movie
	 * for particular rating.
	 * 
	 * @param movieName
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public static List<MovieRatingDTO> getRatingCountByMovieName(String movieName)
			throws ServiceException, ValidationException {
		MovieValidator.validateMovieName(movieName);
		String newMovieName = movieName.trim();
		try {
			Integer movieId = MovieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Sorry Movie Not Registered");
			}

			return UserRatingDAO.countRatingByMovieIdOrderByRatingDesc(movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry Unable to count the data");
		}

	}

}
