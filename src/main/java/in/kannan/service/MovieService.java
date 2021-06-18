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
	public static List<Movie> getMovies() throws ServiceException {
		try {
			return MovieDAO.findAll();

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

	public static void addMovie(String movieName, String releaseDate, boolean status)
			throws ServiceException, ValidationException {

		try {

			MovieValidator.validateMovie(movieName, releaseDate);
			LocalDate date = LocalDate.parse(releaseDate);
			Movie movie = MovieDAO.exist(movieName);
			if (movie != null) {
				throw new ServiceException("Movie Already Registered");
			}

			MovieDAO.save(movieName, date, status);

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
			MovieValidator.validateMovieName(movieName);
			Movie movie = MovieDAO.exist(movieName);
			if (movie == null) {
				throw new ServiceException("This movie is not registered");
			}
			Integer movieId = MovieDAO.findByMovieId(movieName);
			UserRatingDAO.remove(movieId);
			MovieDAO.remove(movieName);

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
			return MovieDAO.findAllWithRating();
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

	public static MovieRating getMovieDetail(String movieName) throws ServiceException, ValidationException {

		try {
			MovieValidator.validateMovieName(movieName);
			Integer movieId = MovieDAO.findByMovieId(movieName);

			return MovieDAO.findAllWithRatingByMovieId(movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to find Movie Details");
		}

	}

}
