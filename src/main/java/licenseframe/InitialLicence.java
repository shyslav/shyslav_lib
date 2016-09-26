package licenseframe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.configuration.DatabaseConnection;
import database.insert.DatabaseInsert;
import lazyfunction.LazyComputerInfo;
import lazyfunction.LazyMD5;
import lazyfunction.LazyWriter;
import licenseframe.users.LicensedUsers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shyslav on 9/25/16.
 */
public class InitialLicence extends ArrayList<LicensedUsers> {
    private static final InitialLicence licence = new InitialLicence();

    private InitialLicence() {
        getFromDBLicenses();
        try {
            LazyWriter.licenceWriteObject(this);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get from db licenses
     */
    private void getFromDBLicenses() {
        insertToDBLicenses();
        String query = "SELECT * FROM license";
        DatabaseConnection db = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                LicensedUsers users = readUsers(rs.getString("computerData"));
                this.add(new LicensedUsers(rs.getInt("id"),
                        users.getComputerName(),
                        users.getUserName(),
                        users.getSystem(),
                        users.getDiskTotalSpace(),
                        users.getDateLicenceStart(),
                        users.getDateLicenceEnd(),
                        rs.getString("computerDataMd")));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert to db licenses
     */
    private void insertToDBLicenses() {
        clearLicensesTable();
        DatabaseInsert.prepareInsert("license", new Object[]
                {"{\"computerName\":\"shyslav-Latitude-E7240/127.0.1.1\",\"userName\":\"shyslav\",\"system\":\"Linux\",\"diskTotalSpace\":46653673472,\"dateLicenceStart\":\"09/25/2016\",\"dateLicenceEnd\":\"12/30/2016\"}"
                        , "990cc19d85e024e92b42858580d2567f"}, new String[]
                {"computerData"
                        , "computerDataMd"}
        );
    }

    /**
     * Generate computer data to md5
     * @return md5 computer info with computer name + username + osname
     */
    public static String generateComputerDataMd5() {
        return LazyMD5.md5(LazyComputerInfo.getComputerName() + "..." + LazyComputerInfo.getUserName() + "..." + LazyComputerInfo.getOSName());
    }

    private void clearLicensesTable() {
        String query = "delete from license";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action to check user licenses
     *
     * @return true if licensed
     */
    public static boolean checkLicense() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        for (LicensedUsers user : getLicence()) {
            if (user.getUserName().equals(getCurrentUser().getUserName())
                    && user.getComputerName().equals(getCurrentUser().getComputerName())
                    && user.getMd5License().equals(generateComputerDataMd5())) {
                Date date = null;
                try {
                    date = formatter.parse(user.getDateLicenceEnd());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date != null && date.after(new Date())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get current user parameters
     *
     * @return current user parameters
     */
    private static LicensedUsers getCurrentUser() {
        return new LicensedUsers(LazyComputerInfo.getComputerName(),
                LazyComputerInfo.getUserName(),
                LazyComputerInfo.getOSName(),
                LazyComputerInfo.getDriverInfo().get(0).getTotalSpace());
    }

    /**
     * Read all licensed users
     *
     * @return licensed users
     * @throws IOException
     */
    private static LicensedUsers readUsers(String json) throws IOException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, LicensedUsers.class);
    }

    /**
     * Get licenses list
     *
     * @return licenses list
     */
    public static InitialLicence getLicence() {
        return licence;
    }
}
