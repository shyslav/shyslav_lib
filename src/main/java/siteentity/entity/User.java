package siteentity.entity;

/**
 * Created by shyslav on 9/10/16.
 */
public class User {
    private int id;
    private String login;
    private RoleType role;

    public User(int id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = RoleType.valueOf(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
