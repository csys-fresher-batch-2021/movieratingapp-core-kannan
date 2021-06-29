package in.kannan.exception;

public class ValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3766857623073621643L;

	/**
	 * used to throw the exception
	 * 
	 * @param message the message to be thrown
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * used to throw the exception by mentioning the type
	 * 
	 * @param e       cached exception type
	 * @param message the message to be thrown
	 */
	public ValidationException(Throwable e, String message) {
		super(message, e);
	}

}
