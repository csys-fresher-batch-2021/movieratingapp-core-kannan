package in.kannan.service;

import java.util.List;

import in.kannan.dao.AdminDAO;
import in.kannan.exception.AdminLoginException;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;
import in.kannan.model.Users;
import in.kannan.util.Logger;

public class MovieServiceTest {
	public MovieServiceTest() {
		super();
	}

	/**
	 * displays movie details
	 * 
	 * @param list contain movie detail
	 * @throws ServiceException
	 */
	public static void displayMovieDetail() throws ServiceException {
		List<Movie> list = MovieService.getMovies();
		for (Movie movie : list) {
			System.out.println(movie);

		}
	}

	/**
	 * main method to start execution
	 * 
	 * @param args
	 * @throws ServiceException
	 * @throws AdminLoginException
	 * @throws DBException
	 */

	public static void main(String[] args) throws ServiceException, DBException, AdminLoginException {

		// displayMovieDetail();
		Logger.log(AdminService.checkAdminLogin("naraya@live.com", "jefien344@##AAS"));

	}

}
