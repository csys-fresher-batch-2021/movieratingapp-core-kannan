package in.kannan.exception;

public class DBException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6988309934978050586L;

	/**
	 * DAOException constructor to throw exception in DAO class
	 * 
	 * @param message the message to be thrown
	 */

	public DBException(String message) {
		super(message);
	}

	/**
	 * exception to be thrown in DAO class
	 * 
	 * @param e       cached exception type
	 * @param message the message to be thrown
	 */

	public DBException(Exception e, String message) {
		super(message, e);

	}

}
