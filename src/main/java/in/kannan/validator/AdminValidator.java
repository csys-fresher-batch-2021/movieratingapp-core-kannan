package in.kannan.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author kann2651
 *
 */
public class AdminValidator {
	private AdminValidator() {
		// private constructor to hide the implicit class
	}

	/**
	 * checks the correct format of email true if correct else false
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
	 * checks the correct format of password true if correct else false
	 * 
	 * @param password
	 * @return true if correct else false
	 */

	public static boolean isValidPassword(String password) {
		boolean isValid = false;
		/**
		 * it must contain atleast one of the followings number,lowercasevariabe,
		 * uppercasevariable,specialcharacters and length should be greater than 8 and
		 * less than 20
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

}
