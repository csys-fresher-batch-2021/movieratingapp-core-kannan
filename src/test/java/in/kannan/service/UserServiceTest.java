package in.kannan.service;

import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;

public class UserServiceTest {
	public static void main(String[] args) throws ServiceException, ValidationException {
		// System.out.println(UserService.getInstance().adminLogin("kannanramesh@gmail.com",
		// "fre3232SFE2#"));

		// Checks the admin Login
		// System.out.println(UserService.getInstance().adminLogin("kannanraemes@hgmail.com",
		// "fre3232SFE2#"));
		// System.out.println(UserService.getInstance().adminLogin("subra245@gmail.com",
		// "@#34asDEw"));
		// System.out.println(UserService.getInstance().adminLogin("ram1234@gmail.com",
		// "asf35AS@#Dwe"));

		// This method checks the user Login
		// System.out.println(UserService.getInstance().userLogin("ram1234@gmail.com",
		// "asf35AS@#Dwe"));

		// This method registers the particular user.
		// String role = "ADMIN";
		// UserRoleEnum userRole = UserRoleEnum.valueOf(role);
		// UserService.getInstance().userRegistration("Ramkumar", "ram1234@gmail.com",
		// "asf35AS@#Dwe", userRole);

		// UserService.getInstance().userRegistration("Vinoth", "vino123@gmail.com",
		// "ASaf12@#jK!2", userRole);

		UserService.getInstance().blockUser(4);

	}

}
