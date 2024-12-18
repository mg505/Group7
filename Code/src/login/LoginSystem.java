package login;

import java.util.ArrayList;

public class LoginSystem {

    private ArrayList<User> users;

    public LoginSystem() {
        users = new ArrayList<>();
        // Predefine a test user for login
        users.add(new User("admin", "password"));  // Predefined user (username: admin, password: password)
    }

    // Register a new user
    public void registerUser(String username, String password) {
        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }
        // Add new user to the list
        users.add(new User(username, password));
        System.out.println("User registered successfully!");
    }

    // Validate login credentials
    public boolean validateLogin(String username, String password) {
        // Iterate through the users list to find a match
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;  // Login successful
            }
        }
        return false;  // Invalid login
    }

    // Optional: Getter for users (if needed for other operations)
    public ArrayList<User> getUsers() {
        return users;
    }
}
