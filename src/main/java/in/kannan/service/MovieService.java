package in.kannan.service;

import java.time.LocalDate;
import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.dao.MovieRatingDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.Movie;
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
	 * get movie details from DAO
	 * 
	 * @return movie details list
	 * @throws
	 */
	public static List<Movie> getMovies() throws ServiceException {
		try {
			return MovieDAO.findAll();

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException(e, "Unable to fetch the movie details");
		}
	}

	/**
	 * This method is used to add the new movie into the database
	 * 
	 * @param movieName
	 * @param date
	 * @param status
	 * @throws ServiceException
	 */

	public static void addMovie(String movieName, LocalDate date, boolean status) throws ServiceException {
		try {
			MovieValidator.validateMovieName(movieName);
			Movie movie = MovieDAO.exist(movieName);
			if (movie != null) {
				throw new ServiceException("Movie Already Registered");
			}

			MovieDAO.save(movieName, date, status);
			Integer movieId = MovieDAO.findByMovieId(movieName);
			MovieRatingDAO.save(movieId);

		} catch (ValidationException | DBException e) {
			Logger.trace(e);
			throw new ServiceException("Failed to insert ");
		}
	}

	/**
	 * This method checks the presence of movie and deletes the given movie in
	 * database
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
			MovieRatingDAO.remove(movieId);
			MovieDAO.remove(movieName);

		} catch (ValidationException | DBException e) {
			Logger.trace(e);
			throw new ServiceException("Unable to delete the movie");
		}
	}

}
