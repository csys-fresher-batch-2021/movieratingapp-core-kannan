package in.kannan.validator;

import in.kannan.constants.UserRoleEnum;
import in.kannan.exception.ValidationException;

public class UserValidatorTest {
	public static void main(String[] args) throws ValidationException {
		// String role = "USER";
		// UserRoleEnum userRole = UserRoleEnum.valueOf(role);
		UserRoleEnum[] user = UserRoleEnum.values();
		for (UserRoleEnum b : user) {
			System.out.println(b + ".. " + b.ordinal());
		}
		// System.out.println(UserRoleEnum.ADMIN);
		// System.out.println(userRole);
		// UserValidator.validateUserDetails("Kannan", "kannan@gmail.com", "omAD3@#sf",
		// userRole);

	}

}
