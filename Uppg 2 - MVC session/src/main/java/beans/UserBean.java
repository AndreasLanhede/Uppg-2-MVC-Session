package beans;

import users.Supersafeuserslog;

public class UserBean {
	private String name, password, favoriteSnack, favoriteMonth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFavoriteSnack() {
		return favoriteSnack;
	}

	public void setFavoriteSnack(String favoriteFruit) {
		this.favoriteSnack = favoriteFruit;
	}
	
	public String getFavoriteMonth() {
		return favoriteMonth;
	}

	public void setFavoriteMonth(String favoriteMonth) {
		this.favoriteMonth = favoriteMonth;
	}

	public boolean validate() {
		// kollar om användarnamn och lösenord matchar användare i "databasen". 
		// ändrade till [i] från [0] i koden. så funkar din inloggning också Marcus :).
		for (int i = 0; i < Supersafeuserslog.getUsers().length; i++) {

			// check the if the info is correct
			if (name.equals(Supersafeuserslog.getUsers()[i]) && password.equals(Supersafeuserslog.getPassword()[i])) {
				return true;
				
			}
		}
		return false;
	}
	public void resetUserBean() {
		this.password = null;
		this.name = null;
	}
}