package in.kannan;

import java.util.List;

import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;
import in.kannan.service.MovieService;



public class TestClass {
	public static void displayMovieDetail(List<Movie> list) {
		for (Movie movie : list) {
			System.out.println(movie);

		}
	}

	public static void main(String[] args) throws ServiceException {
		List<Movie> list = MovieService.getMovies();
		displayMovieDetail(list);

	}

}
