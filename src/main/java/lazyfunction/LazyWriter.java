package lazyfunction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import licenseframe.users.LicensedUsers;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by shyslav on 9/25/16.
 */
public class LazyWriter {
    public static void licenceWriteObject(LicensedUsers users) throws IOException, URISyntaxException {
        File file = new File(LazyComputerInfo.getCurrentDir() + "/licenses.dat");
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
        output.writeBytes(LazyMD5.md5(DataToJson(users)));
        output.close();
    }

    /**
     * Write object to json string
     *
     * @param object object to convert
     * @return json string
     */
    public static String DataToJson(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }
}
