package in.kannan.model;

public class MovieRating {
	private Integer movieId;
	private Movie movie;

	@Override
	public String toString() {
		return "MovieRating [movie=" + movie + ", rating=" + rating + "]";
	}

	private Double rating;

	public MovieRating(Movie movie, Double rating) {
		super();
		this.rating = rating;
		this.movie = movie;
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
	 * @return the movieId
	 */
	public Integer getMovieId() {
		return movieId;
	}

	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public MovieRating(Integer movieId, Double rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

}
