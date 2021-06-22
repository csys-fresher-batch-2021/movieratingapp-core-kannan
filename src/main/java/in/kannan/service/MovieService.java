package in.kannan.service;

import java.time.LocalDate;
import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.Movie;
import in.kannan.model.MovieRating;
import in.kannan.util.Logger;
import in.kannan.validator.MovieValidator;

public class MovieService {
	/**
	 * private constructor to hide public class
	 */
	private MovieService() {
		super();
	}

	/**
	 * This method fetches all the details of the movie.
	 * 
	 * @return movie details list
	 * @throws
	 */
	public static List<Movie> getAllExceptRatings() throws ServiceException {
		try {
			return MovieDAO.findAllByOrderByMovieId();

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, "Sorry unable to fetch the movie details");
		}
	}

	/**
	 * This method is used to add the new movie.It validates the data then add it
	 * and it also add the data in another field.
	 * 
	 * @param movieName
	 * @param releaseDate
	 * @param status
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public static void save(String movieName, String releaseDate, boolean status)
			throws ServiceException, ValidationException {

		try {
			String newMovieName = movieName.trim();

			MovieValidator.validateMovie(newMovieName, releaseDate);
			LocalDate date = LocalDate.parse(releaseDate);
			Movie movie = MovieDAO.findMovieNameByExactMovieName(newMovieName);
			if (movie != null) {
				throw new ServiceException("Movie Already Registered");
			}

			MovieDAO.save(newMovieName, date, status);
			Integer movieId = MovieDAO.findMovieIdByExactMovieName(newMovieName);
			UserRatingDAO.addMovieId(movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to add movie ");
		}

	}

	/**
	 * This method checks the presence of movie and deletes the given movie
	 * 
	 * @param movieName
	 * @throws ServiceException
	 */
	public static void removeMovie(String movieName) throws ServiceException {
		try {
			String newMovieName = movieName.trim();
			MovieValidator.validateMovieName(newMovieName);

			Integer movieId = MovieDAO.findMovieIdByExactMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie is not registered");
			}

			UserRatingDAO.remove(movieId);
			MovieDAO.remove(newMovieName);

		} catch (ValidationException | DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to delete the movie");
		}
	}

	/**
	 * This method returns the movie detail with rating
	 * 
	 * @return
	 * @throws ServiceException
	 */

	public static List<MovieRating> getMoviesWithRating() throws ServiceException {

		try {
			return MovieDAO.findByMovieIdOrderByAverageRatingDesc();
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to fetch the detail");
		}

	}

	/**
	 * This method returns the movie detail with rating
	 * 
	 * @return
	 * @throws ServiceException
	 */

	public static List<MovieRating> getAll() throws ServiceException {

		try {
			return MovieDAO.findByMovieId();
		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to fetch the detail");
		}

	}

	/**
	 * This method returns the movie Details for the particular movie
	 * 
	 * @param movieName
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public static MovieRating findByMovieName(String movieName) throws ServiceException, ValidationException {

		try {
			MovieValidator.validateMovieName(movieName);
			Integer movieId = MovieDAO.findMovieIdByExactMovieName(movieName);
			if (movieId == null) {
				throw new ServiceException("Movie is Not Registered ");
			}

			return MovieDAO.findByMovieId(movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to find Movie Details");
		}

	}

}
