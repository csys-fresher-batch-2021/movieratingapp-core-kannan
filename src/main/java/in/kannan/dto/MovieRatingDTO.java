package in.kannan.dto;

public class MovieRatingDTO {
	private Double rating;

	public MovieRatingDTO(Double rating, Integer count) {
		super();
		this.rating = rating;
		this.count = count;
	}

	@Override
	public String toString() {
		return "UserCount [rating=" + rating + ", count=" + count + "]";
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

	private Integer count;

}
