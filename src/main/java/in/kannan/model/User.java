package in.kannan.model;

import in.kannan.constants.UserRoleEnum;

public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private UserRoleEnum role;

	public User() {
		super();

	}

	public User(UserRoleEnum role) {
		super();
		this.role = role;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + "]";
	}

	public User(Integer id, String name, String email, UserRoleEnum role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public User(Integer id, String name, String email, String password, UserRoleEnum role) {
		this(id, name, email, role);
		this.password = password;

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public UserRoleEnum getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

}
