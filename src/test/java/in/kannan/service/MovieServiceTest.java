package in.kannan.service;

import java.time.LocalDate;
import java.util.List;

import in.kannan.dao.MovieDAO;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;

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

	public static void main(String[] args) throws ServiceException, DBException {

	//	displayMovieDetail();
		String date = "2021-03-19";
		LocalDate raleaseDate = LocalDate .parse(date);
	//	MovieService.addMovie("Thaen", raleaseDate, true);
		MovieService.removeMovie("maaRa");

	}

}
