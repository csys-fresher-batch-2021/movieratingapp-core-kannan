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
	public void setId(Integer id) {
		this.id = id;
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

	public Movie(Integer id, String name, LocalDate releaseDate) {
		super();
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
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
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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

	public Movie(Integer id, String name, LocalDate releaseDate, boolean status,
			LocalDate endDate) {
		this(id,name,releaseDate,status);	// call 4 argument constructor	
		this.endDate = endDate;
	}

	public Movie(Integer id, String name, LocalDate releaseDate, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", releaseDate=" + releaseDate + ", status=" + status
				+ ", endDate=" + endDate + "]";
	}

}
