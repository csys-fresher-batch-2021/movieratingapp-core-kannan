package in.kannan.util;

public class Logger {
	private Logger() {
		// private constructor to hide the implicit class
	}

	/**
	 * displays the corresponding toString method of object
	 * 
	 * @param obj object variable
	 */
	public static void log(Object obj) {
		System.out.println(obj);
	}

	/**
	 * trace the exception message
	 * 
	 * @param e type of exception occurred
	 */

	public static void trace(Exception e) {
		e.printStackTrace();
	}

	/**
	 * THis logger method used to print the given message
	 * 
	 * @param message
	 */
	public static void message(String message) {
		System.out.println(message);
	}

}
