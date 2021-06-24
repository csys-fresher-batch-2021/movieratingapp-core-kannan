package in.kannan.dto;

import in.kannan.model.Movie;

public class CountRating {
	private Movie movie;
	private Integer count;

	public CountRating(Movie movie, Integer count) {
		super();
		this.movie = movie;
		this.count = count;
	}

	@Override
	public String toString() {
		return "CountRating [movie=" + movie + ", count=" + count + "]";
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

}
