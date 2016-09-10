package database.configuration;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
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
    public void openConnection()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database/database.properties")) {
            props.load(in);
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try{
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
            st = con.createStatement();
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            log.error("open connection error"+ex);
        }
    }

    /**
     * Get connection without statement
     * @return connection
     */
    public Connection getConnection(){
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database/database.properties")) {
            props.load(in);
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try {
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            log.error("open connection error"+ex);
        }
        return con;
    }

    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException ex) {
            log.error("close database connection error"+ex);
        }
    }
}