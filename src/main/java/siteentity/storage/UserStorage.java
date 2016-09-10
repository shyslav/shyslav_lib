package siteentity.storage;


import siteentity.entity.Role;
import siteentity.entity.User;
import database.configuration.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shyslav on 9/10/16.
 */
public class UserStorage {
    private String ipAddress;
    private int amountLogin;
    private ArrayList<Role> roles;
    private User user;

    public UserStorage(String ipAddress, int amountLogin) {
        this.ipAddress = ipAddress;
        this.amountLogin = amountLogin;
        this.roles = new ArrayList<>();
        generateRole();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void increase() {
        amountLogin++;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getAmountLogin() {
        return amountLogin;
    }

    public void setAmountLogin(int amountLogin) {
        this.amountLogin = amountLogin;
    }

    public Role getRoleByID(int id){
        for (Role rol : roles){
            if(rol.getId() == id){
                return rol;
            }
        }
        return null;
    }

    private void generateRole() {
        try {
            DatabaseConnection db = new DatabaseConnection();
            PreparedStatement preparedStatement =
                    db.getConnection().prepareStatement("SELECT * FROM role");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                roles.add(new Role(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println("generate error");
        }
    }
}
