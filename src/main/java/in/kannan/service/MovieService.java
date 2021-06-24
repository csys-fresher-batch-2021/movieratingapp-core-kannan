package in.kannan.service;

import java.time.LocalDate;
import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.dao.UserRatingDAO;
import in.kannan.dto.MovieRatingCountDTO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.Movie;
import in.kannan.model.MovieRating;
import in.kannan.util.Logger;
import in.kannan.validator.MovieValidator;
import in.kannan.validator.RatingValidator;

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
	public static List<Movie> getAllMovies() throws ServiceException {
		try {
			return MovieDAO.findAllOrderByMovieId();

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
			String newMovieName = movieName.trim();
			MovieValidator.validateMovie(newMovieName, releaseDate);
			LocalDate date = LocalDate.parse(releaseDate);
			Movie movie = MovieDAO.findMovieNameByExactMovieName(newMovieName);
			if (movie != null) {
				throw new ServiceException("Movie Already Registered");
			}
			Movie movieDetail = new Movie();
			movieDetail.setName(newMovieName);
			movieDetail.setReleaseDate(date);
			movieDetail.setStatus(status);

			MovieDAO.save(movieDetail);
			Integer movieId = MovieDAO.findMovieIdByMovieName(newMovieName);
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

			Integer movieId = MovieDAO.findMovieIdByMovieName(newMovieName);
			if (movieId == null) {
				throw new ServiceException("Movie is not registered");
			}

			UserRatingDAO.remove(movieId);
			MovieDAO.remove(movieId);

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

	public static List<MovieRating> getAllMovieWithRating() throws ServiceException {

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

	public static MovieRating getMovieWithRatingByMovieName(String movieName)
			throws ServiceException, ValidationException {

		try {
			MovieValidator.validateMovieName(movieName);
			Integer movieId = MovieDAO.findMovieIdByMovieName(movieName);
			if (movieId == null) {
				throw new ServiceException("Movie is Not Registered ");
			}

			return MovieDAO.findByMovieId(movieId);

		} catch (DBException e) {
			Logger.trace(e);
			throw new ServiceException("Sorry unable to find Movie Details");
		}

	}

	/**
	 * This method returns the number of users rated with the list of movies above
	 * the given rating value
	 * 
	 * @param rating
	 * @return
	 * @throws ServiceException
	 */

	public static List<MovieRatingCountDTO> getMovieAndRatingByRating(Integer rating) throws ServiceException {
		try {
			return MovieDAO.findMovieAndRatingByRating(rating);
		} catch (DBException e) {
			throw new ServiceException("Sorry unable to count the user's rating");
		}
	}

	/**
	 * This method returns the sorting list of movies according to average rating
	 * for the given rating
	 * 
	 * @param averageRating
	 * @return sorted movies
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public static List<MovieRating> getMovieByAverageRating(Integer averageRating)
			throws ValidationException, ServiceException {
		try {
			RatingValidator.validateRating(averageRating);
			return MovieDAO.findMovieByAverageRating(averageRating);
		} catch (DBException e) {
			throw new ServiceException("Unable to Sort the movie");
		}

	}

}
