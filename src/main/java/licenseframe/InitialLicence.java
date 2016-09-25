package licenseframe;

import lazyfunction.LazyComputerInfo;
import licenseframe.users.LicensedUsers;

import java.util.Date;

/**
 * Created by shyslav on 9/25/16.
 */
public class InitialLicence {
    public LicensedUsers initialize(){
        LicensedUsers licensedUsers =
                new LicensedUsers(LazyComputerInfo.getComputerName(),
                        LazyComputerInfo.getUserName(),
                        LazyComputerInfo.getOSName(),
                        LazyComputerInfo.getDriverInfo().get(0).getTotalSpace(),
                        new Date(2016,9,25),
                        new Date(2016,12,30));
        return licensedUsers;
    }
}
