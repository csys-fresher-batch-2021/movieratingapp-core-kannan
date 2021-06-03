package in.kannan.service;

import java.util.List;

import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;




public class MovieServiceTest {
	public MovieServiceTest() {
		super();
	}
	/**
	 * displays movie details 
	 * @param list contain movie detail
	 */
	public static void displayMovieDetail(List<Movie> list) {
		for (Movie movie : list) {
			System.out.println(movie);

		}
	}
	/**
	 * main method to start execution
	 * @param args
	 * @throws ServiceException
	 */

	public static void main(String[] args) throws ServiceException {
		List<Movie> list = MovieService.getMovies();
		displayMovieDetail(list);

	}

}
