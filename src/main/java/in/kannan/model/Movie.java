package in.kannan.model;

import java.time.LocalDate;

public class Movie {
	private Integer id;
	private String name;
	private LocalDate startDate;
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
		id = movieId;
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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Movie(Integer movieId, String name, LocalDate startDate, boolean status, LocalDate endDate) {
		super();
		id = movieId;
		this.name = name;
		this.startDate = startDate;
		this.status = status;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", startDate=" + startDate + ", status=" + status + ", endDate="
				+ endDate + "]";
	}

}
