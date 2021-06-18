package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class UserValidatorTest {
	public static void main(String[] args) throws ValidationException {
		// UserValidator.validateLoginCredentials("kanna@nad.com", "omAD32@#sf");
		//UserValidator.validateLoginCredentials("kannanad.com", "omAD3@#2sf");
		UserValidator.validateLoginCredentials("kannan@gmail.com", "omAD3@#sf");
		
	}

}
