package in.kannan.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.kannan.exception.ValidationException;

public class UserValidator {
	private UserValidator() {
		// private constructor to hide the implicit class
	}

	/**
	 * validates the email
	 * 
	 * @param mail input the email
	 * @return boolean value
	 */

	public static boolean isValidEmail(String mail) {
		boolean isValid = false;
		String pattern = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(mail);
		boolean check = m.matches();

		if (check) {
			isValid = true;
		}
		return isValid;

	}

	/**
	 * validates the password
	 * 
	 * @param password input the password
	 * @return boolean value
	 */

	public static boolean isValidPassword(String password) {
		boolean isValid = false;
		/**
		 * it must contain atleast one of the followings are number,lowercase
		 * variabe,uppercase variable,specialcharacters and length shoul be greater than
		 * 8 and less than 20
		 */
		String pattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(password);
		boolean check = m.matches();

		if (check) {
			isValid = true;
		}

		return isValid;

	}

	/**
	 * validates the user details
	 * 
	 * @param email    input the email
	 * @param password input the password
	 * @throws ValidationException
	 */

	public static void validateLoginCredentials(String email, String password) throws ValidationException {

		if (!isValidEmail(email)) {
			throw new ValidationException("Invalid email format ");
		} else if (!isValidPassword(password)) {
			throw new ValidationException("Invalid password format ");

		}

	}
}
