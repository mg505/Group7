package login;

import java.util.ArrayList;

public class Registration {
	private ArrayList<User> registeredUsers = new ArrayList<>();

    // Method to register a user
    public boolean register(String username, String password) {
        // Check if the username already exists
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }

        // Register the user
        User newUser = new User(username, password);
        registeredUsers.add(newUser);
        return true;
    }

    // Optional: Method to list registered users
    public ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

}
