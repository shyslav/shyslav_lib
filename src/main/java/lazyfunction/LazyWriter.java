package lazyfunction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import licenseframe.InitialLicence;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by shyslav on 9/25/16.
 */
public class LazyWriter {
    private static final Logger log = Logger.getLogger(LazyWriter.class.getName());

    /**
     * write licensed list to file
     *
     * @param users licensed array list
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void licenceWriteObject(InitialLicence users) throws IOException, URISyntaxException {
        File file = new File(LazyComputerInfo.getCurrentDir() + "/licenses.dat");
        if (file.createNewFile()) {
            log.info("Create new file licenses.dat");
        } else {
            log.info("File already exist. Reload file from db.");
        }
        if (users == null) {
            return;
        }
        DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
        output.writeBytes(dataToJson(users));
        output.close();
    }

    /**
     * Get licence string from file
     *
     * @return licence string
     * @throws IOException
     */
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
