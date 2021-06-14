package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class MovieValidator {
	private MovieValidator() {

	}

	public static boolean isValidMovieName(String name) {
		boolean isValid = false;

		if (name != null) {
			isValid = true;

			if (name.trim().equals("")) {
				isValid = false;
			}

			String exceptions = "!@#$%^&*()_+},=-`~{:1234567890?/><";

			for (int i = 0; i < name.trim().length(); i++) {
				char c = name.trim().charAt(i);
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

	public static void validateMovieName(String name) throws ValidationException {

		if (!isValidMovieName(name)) {
			throw new ValidationException("Invalid Movie Name ");
		}
	}

}
