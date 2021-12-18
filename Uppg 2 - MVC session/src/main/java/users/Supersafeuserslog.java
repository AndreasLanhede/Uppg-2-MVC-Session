package users;

public class Supersafeuserslog {
	// Listor av användare och lösenord

	private static String users[] = { "Andreas", "Marcus" };

	private static String password[] = { "admin1", "admin2" };

	public static String[] getUsers() {
		return users;
	}

	public static String[] getPassword() {
		return password;
	}

}