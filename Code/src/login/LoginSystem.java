package login;

import java.util.ArrayList;

public class LoginSystem {
	// List of all users
    private ArrayList<User> users; 
 // Current logged-in user
    private static User currentUser; 

    public LoginSystem() {
        users = new ArrayList<>();
        //admin user
        users.add(new User("admin", "password")); 
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
                currentUser = user; // Set logged-in user
                return user;
            }
        }
        return null; 
    }

    // Logout current user
    public void logout() {
        currentUser = null;
    }
    
    // Return current user
    public static User getCurrentUser() {
        return currentUser; 
    }
    
    // Return all users
    public ArrayList<User> getAllUsers() {
        return users; 
    }
    
    // Remove user from list
    public void removeUser(User user) {
        users.remove(user); 
    }
}
