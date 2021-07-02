package in.kannan.service;

import java.util.List;

import in.kannan.constants.UserRoleEnum;
import in.kannan.dao.DAOFactory;
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

	private static RatingService ratingService = null;

	public static RatingService getInstance() {
		if (ratingService == null) {
			synchronized (RatingService.class) {
				if (ratingService == null) {
					ratingService = new RatingService();
				}

			}
		}
		return ratingService;
	}

	private static MovieDAO movieDAO = DAOFactory.getMovieDAOInstance();
	private static UserDAO userDAO = DAOFactory.getUserDAOInstance();
	private static UserRatingDAO userRatingDAO = DAOFactory.getUserRatingDAOInstance();

	/**
	 * This method is used to add the users rating.It validates the input details,
	 * check whether the user the rated for the particular movie before ,finds the
	 * role of the user then add their rating.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public void addUserRating(Integer userId, Integer movieId, Integer rating)
			throws ServiceException, ValidationException {
		try {
			RatingValidator.validateRating(userId, movieId, rating);
			UserRating userRating = userRatingDAO.findUserIdByUserIdAndMovieId(userId, movieId);
			if (userRating != null) {
				throw new ServiceException("You already Rated");
			}
			User user = userDAO.findRole(userId);
			if (user.getRole() != UserRoleEnum.USER) {
				throw new ServiceException("Only users could rate the app");
			}

			userRatingDAO.save(userId, movieId, rating);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());

		}

	}

	/**
	 * This method just deletes the movieId and userID data
	 * 
	 * @param userID
	 * @param movieId
	 * @throws ServiceException
	 */
	public void undoRating(Integer userID, Integer movieId) throws ServiceException {
		try {
			userRatingDAO.removeByUserIdAndMovieId(userID, movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
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

	public Integer countUsersRated(String movieName) throws ValidationException, ServiceException {
		String newMovieName = movieName.trim();
		MovieValidator.validateMovieName(newMovieName);
		Integer count = null;
		try {
			Integer movieId = movieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie Not found");
			}
			count = userRatingDAO.countRatingByMovieId(movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
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

	public Integer countRatingByRatingAndMovieName(Integer rating, String movieName)
			throws ValidationException, ServiceException {
		RatingValidator.validateRating(rating, movieName);
		Integer count = null;
		String newMovieName = movieName.trim();
		try {
			Integer movieId = movieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie Not found");
			}
			count = userRatingDAO.countRatingByRatingAndMovieId(rating, movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
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

	public List<MovieRatingDTO> getRatingCountByMovieName(String movieName)
			throws ServiceException, ValidationException {
		MovieValidator.validateMovieName(movieName);
		String newMovieName = movieName.trim();
		try {
			Integer movieId = movieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Sorry Movie Not Registered");
			}

			return userRatingDAO.countRatingByMovieIdOrderByRatingDesc(movieId);
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, e.getMessage());
		}

	}

}
