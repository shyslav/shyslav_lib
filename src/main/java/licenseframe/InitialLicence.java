package licenseframe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lazyfunction.LazyComputerInfo;
import lazyfunction.LazyWriter;
import licenseframe.users.LicensedUsers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shyslav on 9/25/16.
 */
public class InitialLicence extends ArrayList<LicensedUsers> {
    public InitialLicence() {
        try {
            add(readUsers(LazyComputerInfo.getCurrentDir() + "/licenses.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action to check user licenses
     *
     * @return true if licensed
     */
    public boolean checkLicense() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        for (LicensedUsers user : this) {
            if (user.getUserName().equals(getCurrentUser().getUserName())
                    && user.getComputerName().equals(getCurrentUser().getComputerName())) {
                Date date = null;
                try {
                    date = formatter.parse(user.getDateLicenceEnd());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date.before(new Date())) {
                    return true;
                }
            }
        }
        return false;
    }

    private LicensedUsers getCurrentUser() {
        return new LicensedUsers(LazyComputerInfo.getComputerName(),
                LazyComputerInfo.getUserName(),
                LazyComputerInfo.getOSName(),
                LazyComputerInfo.getDriverInfo().get(0).getTotalSpace());
    }

    private static LicensedUsers readUsers(String filepath) throws IOException {
        Gson gson = new GsonBuilder().create();
        String licenses = LazyWriter.licenceGetObject();
        LicensedUsers data = gson.fromJson(licenses, LicensedUsers.class);
        return data;
    }
}
