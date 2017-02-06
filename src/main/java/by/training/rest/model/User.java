package by.training.rest.model;

public class User {

    private String username;
    private String password;
    private String enabled;

    public User(){}

    public User(String username, String password,String enabled) {
        this.username = username;
        this.password = password;
        this.enabled=enabled;
    }



    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
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
}
