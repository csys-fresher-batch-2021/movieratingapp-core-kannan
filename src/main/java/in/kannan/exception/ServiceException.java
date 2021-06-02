package in.kannan.exception;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341317642603196756L;

	/**
	 * exception to be thrown in service class
	 * 
	 * @param message to be thrown
	 */

	public ServiceException(String message) {
		super(message);
	}

	/**
	 * exception to be thrown in service class
	 * 
	 * @param e       cached exception
	 * @param message to be thrown
	 */

	public ServiceException(Exception e, String message) {
		super(message, e);
	}

}
