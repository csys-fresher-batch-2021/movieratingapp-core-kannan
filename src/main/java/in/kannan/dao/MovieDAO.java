package in.kannan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.kannan.dto.MovieRatingCountDTO;
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
	private static final String AVERAGE_RATING = "average_rating";
	private static final String MESSAGE = "Unable to fetch movie details ";

	private MovieDAO() {

	}

	/**
	 * This method returns the movie details as list.
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
			String sql = "select movie_id,movie_name,release_date,status from movies";
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
			throw new DBException(e, MESSAGE);
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return list;

	}

	/**
	 * This method checks the presence of movie.
	 * 
	 * @param movieName
	 * @return
	 * @throws ValidationException
	 */

	public static Movie findMovieNameByExactMovieName(String movieName) throws ValidationException {
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
	 * This method save the movie detail .
	 * 
	 * @param movieName
	 * @param releaseDate
	 * @param status
	 * @param id
	 * @throws DBException
	 */

	public static void save(Movie movie) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into movies (movie_name,release_date,status) values (?,?,?)";
			pst = connection.prepareStatement(sql);
			String movieName = movie.getName();
			LocalDate releaseDate = movie.getReleaseDate();
			boolean status = movie.getStatus();
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

	public static Integer findMovieIdByMovieName(String movieName) throws DBException {
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
	 * This method removes particular movie details from database by using their Id.
	 * 
	 * @param movieName
	 * @throws DBException
	 */

	public static void remove(Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from movies where movie_id = ? ";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
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

	public static List<MovieRating> findAllOrderByAverageRatingDesc() throws DBException {
		List<MovieRating> movieRating = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();

			String sql = "select * from movie_ratings_order";

			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);
				double rate = rs.getDouble(AVERAGE_RATING);
				Movie movie = new Movie(id, name, getStartDate, active);

				MovieRating rating = new MovieRating(movie, rate);
				movieRating.add(rating);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(e, MESSAGE);
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

	public static MovieRating findAllByMovieId(Integer movieId) throws DBException {
		MovieRating rating = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select m.movie_id,m.movie_name,m.release_date,m.status,a.average_rating");
			sql.append(" from movies m inner join(select movie_id,round(avg(rating) ,2) as average_rating ");
			sql.append(" from user_ratings where movie_id =? group by movie_id ) as a on");
			sql.append(" m.movie_id=a.movie_id");
			String sq = sql.toString();
			pst = connection.prepareStatement(sq);
			pst.setInt(1, movieId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);
				double rate = rs.getDouble(AVERAGE_RATING);
				Movie movie = new Movie(id, name, getStartDate, active);

				rating = new MovieRating(movie, rate);

			}
		} catch (SQLException | NullPointerException e) {

			Logger.trace(e);
			throw new DBException(e, MESSAGE);
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return rating;

	}

	/**
	 * This method returns all the details of the movie including average rating.
	 * 
	 * @return
	 * @throws DBException
	 */

	public static List<MovieRating> findAllAndAverageRating() throws DBException {
		List<MovieRating> movieRating = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();

			String sql = "select * from movie_ratings";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(MOVIE_ID);
				String name = rs.getString(MOVIE_NAME);
				Date releaseDate = rs.getDate(RELEASE_DATE);
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean(STATUS);
				double rate = rs.getDouble(AVERAGE_RATING);
				Movie movie = new Movie(id, name, getStartDate, active);

				MovieRating rating = new MovieRating(movie, rate);
				movieRating.add(rating);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(e, MESSAGE);
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return movieRating;

	}

	/**
	 * This method returns the number of users rated along with the list of movies
	 * above the given rating value
	 * 
	 * @param rating
	 * @return list containing movie's rated
	 * @throws DBException
	 */

	public static List<MovieRatingCountDTO> findMovieAndRatingByRating(Integer rating) throws DBException {
		List<MovieRatingCountDTO> movieRating = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select m.movie_id,m.movie_name,m.release_date,m.status,r.count from movies m inner join");
			sql.append(
					" (select movie_id,count(rating) from user_ratings where rating>=? group by movie_id order by movie_id )");
			sql.append(" r on m.movie_id=r.movie_id;");
			String sq = sql.toString();
			pst = connection.prepareStatement(sq);
			pst.setInt(1, rating);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer movieId = rs.getInt(MOVIE_ID);
				String movieName = rs.getString(MOVIE_NAME);
				Date date = rs.getDate(RELEASE_DATE);
				LocalDate releaseDate = date.toLocalDate();
				boolean status = rs.getBoolean(STATUS);
				Integer count = rs.getInt("count");
				Movie movie = new Movie(movieId, movieName, releaseDate, status);

				MovieRatingCountDTO countRating = new MovieRatingCountDTO(movie, count);
				movieRating.add(countRating);
			}

		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException("Unable to count the rating");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return movieRating;

	}

	/**
	 * This method sorts according to average rating and returns the movie details
	 * along with the average rating to user
	 * 
	 * @param rating
	 * @return sorted list of movies
	 * @throws DBException
	 */

	public static List<MovieRating> findMovieByAverageRating(Integer averageRating) throws DBException {
		List<MovieRating> movieRating = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select m.movie_id,m.movie_name,m.release_date,m.status,a.average_rating from movies m inner join");
			sql.append("(select movie_id,round(avg(rating),2) as average_rating from user_ratings group by movie_id");
			sql.append(" having round(avg(rating),2) >=? order by movie_id) as a on  m.movie_id=a.movie_id");
			String sq = sql.toString();
			pst = connection.prepareStatement(sq);
			pst.setInt(1, averageRating);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer movieId = rs.getInt(MOVIE_ID);
				String movieName = rs.getString(MOVIE_NAME);
				Date date = rs.getDate(RELEASE_DATE);
				LocalDate releaseDate = date.toLocalDate();
				boolean status = rs.getBoolean(STATUS);
				double rating = rs.getDouble(AVERAGE_RATING);
				Movie movie = new Movie(movieId, movieName, releaseDate, status);
				MovieRating mRating = new MovieRating(movie, rating);
				movieRating.add(mRating);

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException("Unable to Sort");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return movieRating;
	}

}
