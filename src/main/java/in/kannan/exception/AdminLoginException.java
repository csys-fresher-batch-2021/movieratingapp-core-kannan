package in.kannan.exception;

public class AdminLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3444005602403642166L;
	/**
	 * throws this exception with message
	 * @param message
	 */

	public AdminLoginException(String message) {
		super(message);
	}

	public AdminLoginException(Exception e, String message) {
		super(message, e);
	}

}
