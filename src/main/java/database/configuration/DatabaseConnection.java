package database.configuration;

import crypto.LazyCrypto;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private static final Logger log = Logger.getLogger(DatabaseConnection.class.getName());

    private Connection con;
    public Statement st;
    public ResultSet rs;

    /**
     * Open mysql connection and create statement
     */
    public void openConnection() {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database/database.properties")) {
            props.load(getDecodeInputStream(in));
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try {
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            log.error("open connection error" + ex);
        }
    }

    /**
     * Get connection without statement
     *
     * @return connection
     */
    public Connection getConnection() {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database/database.properties")) {
            props.load(getDecodeInputStream(in));
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try {
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            log.error("open connection error" + ex);
        }
        return con;
    }

    /**
     * Ged decode input stream from coded input stream
     *
     * @param in coded input stream
     * @return decode input stream
     * @throws IOException
     */
    public InputStream getDecodeInputStream(InputStream in) throws IOException {
        String inputStreamText = getStringFromInputStream(in);
        byte[] bytes = new BASE64Decoder().decodeBuffer(inputStreamText);
        String decryptText = LazyCrypto.decryptText(bytes);
        return new ByteArrayInputStream(decryptText.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Action to close connection
     */
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            log.error("close database connection error" + ex);
        }
    }

    /**
     * Convert input stream to string
     *
     * @param is input stream
     * @return input string
     */
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}