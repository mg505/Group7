package login;

import java.util.ArrayList;

public class LoginSystem {

    private ArrayList<User> users;

    public LoginSystem() {
        // Initially adding a test user
        users = new ArrayList<>();
        users.add(new User("admin", "password"));  // Predefined user
    }

    // Register a new user
    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }

    // Validate login credentials
    public boolean validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;  // Login successful
            }
        }
        return false;  // Invalid login
    }
}
