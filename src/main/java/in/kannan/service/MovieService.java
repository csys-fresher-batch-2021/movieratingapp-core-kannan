package in.kannan.service;

import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;

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
	 * @throws ServiceException DAOException is caught and thrown as
	 *                          ServiceException
	 */
	public static List<Movie> getMovies() throws ServiceException {
		try {
			List<Movie> list = MovieDAO.findAll();
			List<Movie> movieDetail = list;
			return movieDetail;
		} catch (DBException e) {

			e.printStackTrace();
			throw new ServiceException(e, "Unable to Display Movie");
		}
	}

}
