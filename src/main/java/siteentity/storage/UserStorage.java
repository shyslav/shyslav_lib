package siteentity.storage;


import database.configuration.DatabaseConnection;
import lazyfunction.LazyDate;
import siteentity.entity.User;
import webframework.impls.UserVariables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by shyslav on 9/10/16.
 */
public class UserStorage {
    private String ipAddress;
    private HashMap<Integer, String> amountLogin;
    private User user;

    public UserStorage(String ipAddress) {
        this.ipAddress = ipAddress;
        this.amountLogin = new HashMap<>();
        amountLoginFromDB();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void increase() {
        amountLogin.put(LazyDate.getUnixDate(), ipAddress);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getAmountLogin() {
        amountLoginUpdate();
        return amountLogin.size();
    }

    /**
     * Update map when time lost
     */
    private void amountLoginUpdate() {
        amountLogin.entrySet().removeIf(
                entry ->
                        LazyDate.getMinutesFromMillis(entry.getKey()) > UserVariables.WRONG_ATTEMPTS_TIME_SAVE);
    }

    /**
     * GET not correct login from db
     * @return
     */
    private void amountLoginFromDB() {
        String query = "select * from login_data where ip = ? and status = ? "
                + " ORDER BY id desc LIMIT " + UserVariables.AMOUN_WRONK_PASSWORD_ATTEMPTS;
        DatabaseConnection db = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ipAddress);
            preparedStatement.setString(2, "error");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (LazyDate.getMinutesFromMillis(rs.getInt("login_time")) < UserVariables.WRONG_ATTEMPTS_TIME_SAVE) {
                    amountLogin.put(rs.getInt("login_time"), ipAddress);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
