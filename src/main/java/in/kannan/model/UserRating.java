package in.kannan.model;

public class UserRating {
	private Integer userId;
	private Integer movieId;
	private Integer rating;

	@Override
	public String toString() {
		return "UserRating [userId=" + userId + ", movieId=" + movieId + ", rating=" + rating + "]";
	}

	public UserRating(Integer userId) {
		super();
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	/**
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
