package webframework.entity;


/**
 * Created by shyslav on 9/10/16.
 */
public class UserStorage {
    private String ipAddress;
    private int amounLogin;

    public UserStorage(String ipAddress, int amounLogin) {
        this.ipAddress = ipAddress;
        this.amounLogin = amounLogin;
    }
    public void increase(){
        amounLogin++;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getAmounLogin() {
        return amounLogin;
    }

    public void setAmounLogin(int amounLogin) {
        this.amounLogin = amounLogin;
    }
}
