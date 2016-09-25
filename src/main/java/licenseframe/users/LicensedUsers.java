package licenseframe.users;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shyslav on 9/25/16.
 */
public class LicensedUsers {
    private String computerName;
    private String userName;
    private String system;
    private long diskTotalSpace;
    private String dateLicenceStart;
    private String dateLicenceEnd;

    public LicensedUsers() {
    }

    public LicensedUsers(String computerName, String userName, String system, long diskTotalSpace, String dateLicenceStart, String dateLicenceEnd) {
        this.computerName = computerName;
        this.userName = userName;
        this.system = system;
        this.diskTotalSpace = diskTotalSpace;
        this.dateLicenceStart = dateLicenceStart;
        this.dateLicenceEnd = dateLicenceEnd;
    }

    public LicensedUsers(String computerName, String userName, String osName, long totalSpace) {

    }

    public String getComputerName() {
        return computerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getSystem() {
        return system;
    }

    public long getDiskTotalSpace() {
        return diskTotalSpace;
    }


    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setDiskTotalSpace(long diskTotalSpace) {
        this.diskTotalSpace = diskTotalSpace;
    }

    public String getDateLicenceStart() {
        return dateLicenceStart;
    }

    public void setDateLicenceStart(String dateLicenceStart) {
        this.dateLicenceStart = dateLicenceStart;
    }

    public String getDateLicenceEnd() {
        return dateLicenceEnd;
    }

    public void setDateLicenceEnd(String dateLicenceEnd) {
        this.dateLicenceEnd = dateLicenceEnd;
    }
}
