package in.kannan.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.kannan.exception.ValidationException;

public class UserValidator {
	private UserValidator() {
		// private constructor to hide the implicit class
	}

	public static boolean isValidUserName(String userName) {
		boolean isValid = false;

		if (userName != null) {
			isValid = true;

			if (userName.trim().equals("") || userName.length() > 30) {
				isValid = false;
			}

			String exceptions = "!@#$%^&*()_+},=-`~{:1234567890?/><";

			for (int i = 0; i < userName.trim().length(); i++) {
				char c = userName.trim().charAt(i);
				for (int j = 0; j < exceptions.length(); j++) {
					char d = exceptions.charAt(j);
					if (c == d) {
						isValid = false;
					}
				}
			}

		}
		return isValid;

	}

	/**
	 * Validates the email using regular expression pattern.
	 * 
	 * @param mail
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
	 * validates the password using regular expression pattern.
	 * 
	 * @param password
	 * @return boolean value
	 */

	public static boolean isValidPassword(String password) {
		boolean isValid = false;
		/**
		 * it must contain at least one number,lower case Variable,upper case
		 * variable,special characters and length should be greater than 8 and less than
		 * 20.
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

	public static boolean isValidRole(String role) {
		boolean isValid = false;
		if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("USER")) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Validates the user details if incorrect it throws the exception.
	 * 
	 * @param email
	 * @param password
	 * @throws ValidationException
	 */

	public static void validateUserDetails(String userName, String email, String password, String role)
			throws ValidationException {
		if (!isValidUserName(userName)) {
			throw new ValidationException("Invalid User Name");
		}

		else if (!isValidEmail(email)) {
			throw new ValidationException("Invalid email format ");
		} else if (!isValidPassword(password)) {
			throw new ValidationException("Invalid password format ");

		} else if (!isValidRole(role)) {
			throw new ValidationException("Invalid role");
		}

	}
}
