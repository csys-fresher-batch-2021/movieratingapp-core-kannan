package in.kannan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.exception.ValidationException;
import in.kannan.model.Movie;
import in.kannan.model.MovieRating;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;

public class MovieDAO {
	/**
	 * private constructor to hide the existing class
	 */
	private static final String MOVIE_ID = "movie_id";
	private static final String MOVIE_NAME = "movie_name";
	private static final String RELEASE_DATE = "release_date";
	private static final String STATUS = "status";

	private MovieDAO() {

	}

	/**
	 * This method returns the movie details as list order by their Id.
	 * 
	 * @return movie details as list
	 * @throws DBException
	 */

	public static List<Movie> findAll() throws DBException {
		List<Movie> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select movie_id,movie_name,release_date,status from movies order by movie_id";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);

				Movie movie = new Movie(id, name, getStartDate, active);
				list.add(movie);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(e, "Unable to fetch the movie detail");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return list;

	}

	/**
	 * This method checks the presence of movie in the database.
	 * 
	 * @param movieName
	 * @return
	 * @throws ValidationException
	 */

	public static Movie exist(String movieName) throws ValidationException {
		Movie movie = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select movie_name from movies where lower(movie_name) = lower(?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, movieName);
			rs = pst.executeQuery();
			if (rs.next()) {
				String name = rs.getString(MOVIE_NAME);
				movie = new Movie(name);

			}
		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new ValidationException("Unable to fetch the data");
		} finally {
			ConnectionUtil.close(rs, pst, null);
		}
		return movie;
	}

	/**
	 * This method inserts the movie detail into the database.
	 * 
	 * @param movieName
	 * @param releaseDate
	 * @param status
	 * @param id
	 * @throws DBException
	 */

	public static void save(String movieName, LocalDate releaseDate, boolean status) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into movies (movie_name,release_date,status) values (?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, movieName);
			Date date = Date.valueOf(releaseDate);
			pst.setDate(2, date);
			pst.setBoolean(3, status);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to save the data  ");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
	}

	/**
	 * This method returns the movie id for the particular movie name.
	 * 
	 * @param movieName
	 * @return
	 * @throws DBException
	 */

	public static Integer findByMovieId(String movieName) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer movieId = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select movie_id from movies where lower(movie_name) = lower(?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, movieName);
			rs = pst.executeQuery();
			if (rs.next()) {
				movieId = rs.getInt(MOVIE_ID);

			}

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to fetch movie id for the given movie  ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}
		return movieId;

	}

	/**
	 * This method removes particular movie details from database.
	 * 
	 * @param movieName
	 * @throws DBException
	 */

	public static void remove(String movieName) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from movies where lower(movie_name) =lower(?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, movieName);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to delete the movie ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * This method displays the entire details of the movie along with ratings
	 * 
	 * @return list which holds all the details
	 * @throws DBException
	 */

	public static List<MovieRating> findAllWithRating() throws DBException {
		List<MovieRating> movieRating = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select m.movie_id,m.movie_name,m.release_date,m.status ,a.average_rating from movies m \r\n"
					+ "inner join (select movie_id ,avg(rating) as average_rating from rating_by_user group by movie_id ) \r\n"
					+ "as a on m.movie_id = a.movie_id order by a.average_rating desc;\r\n";

			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);
				double rate = rs.getDouble("average_rating");
				Movie movie = new Movie(id, name, getStartDate, active);

				MovieRating rating = new MovieRating(movie, rate);
				movieRating.add(rating);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(e, "Unable to fetch movie details ");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return movieRating;

	}

	/**
	 * This method returns the movie detail along rating for the input of movie Id
	 * 
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	public static MovieRating findAllWithRatingByMovieId(Integer movieId) throws DBException {
		MovieRating rating = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select m.movie_id,m.movie_name,m.release_date,m.status ,a.average_rating from movies m \r\n"
					+ "inner join (select movie_id ,avg(rating) as average_rating from rating_by_user where \r\n"
					+ "movie_id =? group by movie_id ) as a on m.movie_id = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.setInt(2, movieId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);
				double rate = rs.getDouble("average_rating");
				Movie movie = new Movie(id, name, getStartDate, active);

				rating = new MovieRating(movie, rate);

			}
		} catch (SQLException | NullPointerException e) {

			Logger.trace(e);
			throw new DBException(e, "Unable to fetch movie details ");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return rating;

	}

}
