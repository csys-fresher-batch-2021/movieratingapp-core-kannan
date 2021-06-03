package in.kannan.service;

import java.util.List;

import in.kannan.exception.ServiceException;
import in.kannan.model.Movie;
import in.kannan.service.MovieService;



public class ServiceTest {
	public ServiceTest() {
		super();
	}
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
