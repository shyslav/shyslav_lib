package licenseframe.users;

import java.util.Date;

/**
 * Created by shyslav on 9/25/16.
 */
public class LicensedUsers {
    private final String computerName;
    private final String userName;
    private final String system;
    private final long diskTotalSpace;
    private final Date dateLicenceStart;
    private final Date dateLicenceEnd;

    public LicensedUsers(String computerName, String userName, String system, long diskTotalSpace, Date dateLicenceStart, Date dateLicenceEnd) {
        this.computerName = computerName;
        this.userName = userName;
        this.system = system;
        this.diskTotalSpace = diskTotalSpace;
        this.dateLicenceStart = dateLicenceStart;
        this.dateLicenceEnd = dateLicenceEnd;
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

    public Date getDateLicenceStart() {
        return dateLicenceStart;
    }

    public Date getDateLicenceEnd() {
        return dateLicenceEnd;
    }
}
