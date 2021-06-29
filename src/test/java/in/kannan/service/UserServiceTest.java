package in.kannan.service;

import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;

public class UserServiceTest {
	public static void main(String[] args) throws ServiceException, ValidationException {
		// System.out.println(UserService.adminLogin("kannanramesh@gmail.com",
		// "fre3232SFE2#"));

		// Checks the admin Login
		// System.out.println(UserService.adminLogin("kannanraemes@hgmail.com",
		// "fre3232SFE2#"));
		// System.out.println(UserService.adminLogin("sankaran12@yahoo.com",
		// "LO122nei!@n"));
		// System.out.println(UserService.adminLogin("naraya@live.com",
		// "jefien344@##AAS"));

		// This method checks the user Login
		System.out.println(UserService.userLogin("siva999@gmail.com", "12^%vfdsAD"));

		// This method registers the particular user.
		UserService.userRegistration("Ashwin", "ashwin1234@gmail.com", "as12AS@#Dwe", "ADMIN");

		// UserService.userRegistration("Vinoth", "vino123@gmail.com", "ASaf12@#jK!2",
		// "ADMIN");

		UserService.blockUser(5);

	}

}
