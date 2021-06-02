package in.kannan.exception;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6988309934978050586L;

	/**
	 * DAOException constructor to throw exception in DAO class
	 * 
	 * @param message to be thrown
	 */

	public DAOException(String message) {
		super(message);
	}

	/**
	 * exception to be thrown in DAO class
	 * 
	 * @param e       type of exception to be caught
	 * @param message to be thrown
	 */

	public DAOException(Exception e, String message) {
		super(message, e);

	}

}
