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
        if(users==null){
            return;
        }
        DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
        output.writeBytes(dataToJson(users));
        output.close();
    }

    public static String licenceGetObject() throws IOException {
        DataInputStream input = new DataInputStream(new FileInputStream(LazyComputerInfo.getCurrentDir() + "/licenses.dat"));
        StringBuilder builder = new StringBuilder();
        while (input.available() > 0) {
            builder.append(input.readLine());
        }
        input.close();
        return builder.toString();
    }

    /**
     * Write object to json string
     *
     * @param object object to convert
     * @return json string
     */
    public static String dataToJson(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }
}
