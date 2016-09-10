package siteentity.storage;


import siteentity.entity.User;

/**
 * Created by shyslav on 9/10/16.
 */
public class UserStorage {
    private String ipAddress;
    private int amountLogin;
    private User user;

    public UserStorage(String ipAddress, int amountLogin) {
        this.ipAddress = ipAddress;
        this.amountLogin = amountLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
