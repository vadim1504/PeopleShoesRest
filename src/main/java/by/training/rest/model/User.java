package by.training.rest.model;

public class User {

    private String username;
    private String password;
    private String enabled;

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    private UserRoles role;

    public User(){}

    public User(String username, String password,String enabled) {
        this.username = username;
        this.password = password;
        this.enabled=enabled;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String role) {
        this.enabled = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
