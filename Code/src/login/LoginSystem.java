package login;

import java.util.ArrayList;


public class LoginSystem {
    private ArrayList<User> users;
    private static User currentUser; // Store the current logged-in user

    public LoginSystem() {
        users = new ArrayList<>();
        users.add(new User("admin", "password")); // Sample user
    }

    // Check if username is already taken
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Register a new user
    public User registerUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }
        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        User newUser = new User(username, password);
        users.add(newUser);
        return newUser;
    }

    // Validate login credentials
    public User validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                currentUser = user; // Set the logged-in user
                return user;
            }
        }
        return null; // Invalid login
    }

    // Logout the current user
    public void logout() {
        currentUser = null; // Clear the session
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
