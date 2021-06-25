package in.kannan.dao;

import in.kannan.exception.DBException;

public class MovieDAOTest {
	public static void main(String[] args) throws DBException {
		// MovieRatingDAO.remove(10);
		// MovieDAO.findAllWithRating();
		// List<Movie> movieList = MovieDAO.findByMovieId();
		// System.out.println(movieList);
//		for (Movie movie : movieList) {
//			System.out.println(movie);
//		}
		System.out.println(MovieDAO.findAll());
		// System.out.println(MovieDAO.findMovieAndRatingByRating(10));
		// System.out.println(MovieDAO.findMovieByAverageRating(6));
		// System.out.println(MovieDAO.findAllOrderByAverageRatingDesc());
		// System.out.println(MovieDAO.findByMovieId("Karnan"));
	}

}
