package in.kannan.model;

import java.time.LocalDate;

public class Movie {
	private Integer id;
	private String name;
	private LocalDate releaseDate;
	private boolean status;
	private LocalDate endDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer movieId) {
		this.id = movieId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String movieName) {
		this.name = movieName;
	}

	public Movie(Integer movieId, String movieName, LocalDate movieReleaseDate) {
		super();
		this.id = movieId;
		this.name = movieName;
		this.releaseDate = movieReleaseDate;
	}

	/**
	 * @return the releaseDate
	 */
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(LocalDate movieReleaseDate) {
		this.releaseDate = movieReleaseDate;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean active) {
		this.status = active;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate movieEndDate) {
		this.endDate = movieEndDate;
	}

	public Movie(Integer movieId, String movieName, LocalDate movieReleaseDate, boolean active, LocalDate movieEndDate) {
		super();
		this.id = movieId;
		this.name = movieName;
		this.releaseDate = movieReleaseDate;
		this.status = active;
		this.endDate = movieEndDate;
	}

	public Movie(Integer movieId, String movieName, LocalDate movieReleaseDate, boolean active) {
		super();
		this.id = movieId;
		this.name = movieName;
		this.releaseDate = movieReleaseDate;
		this.status = active;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", releaseDate=" + releaseDate + ", status=" + status + ", endDate="
				+ endDate + "]";
	}

}
