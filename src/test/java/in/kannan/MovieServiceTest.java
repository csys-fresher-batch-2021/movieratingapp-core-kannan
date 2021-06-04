package in.kannan;

import java.util.List;

import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;
import in.kannan.service.MovieService;



public class MovieServiceTest {
	/**
	 * Test case : To display all movie detail.
	 * @throws ServiceException
	 */
	public static void displayMovieDetail() throws ServiceException {
		List<Movie> list = MovieService.getMovies();
		for (Movie movie : list) {
			System.out.println(movie);

		}
	}

	public static void main(String[] args) throws ServiceException {
		
		displayMovieDetail();
		

	}

}
