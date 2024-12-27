package login;

import java.util.ArrayList;

public class LoginSystem {

    private ArrayList<User> users;

    public LoginSystem() {
        users = new ArrayList<>();
        // Predefine a test user for login
        users.add(new User("admin", "password"));  // Predefined user (username: admin, password: password)
    }

    // Register a new user and return the User object
    public User registerUser(String username, String password) {
        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                throw new IllegalArgumentException("Username already exists!");
            }
        }
        // Create and add the new user
        User newUser = new User(username, password);
        users.add(newUser);
        return newUser;
    }

    // Validate login credentials and return the User object if valid
    public User validateLogin(String username, String password) {
        // Iterate through the users list to find a match
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;  // Return the User object on successful login
            }
        }
        return null;  // Return null if login fails
    }

    // Optional: Getter for users (if needed for other operations)
    public ArrayList<User> getUsers() {
        return users;
    }
}
