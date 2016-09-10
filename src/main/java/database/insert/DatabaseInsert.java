package database.insert;

import database.configuration.DatabaseConnection;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class DatabaseInsert {
    private static final Logger log = Logger.getLogger(DatabaseInsert.class.getName());

    private static DatabaseConnection db = new DatabaseConnection();

    /**
     * Insert to database action
     *
     * @param tableName table name
     * @param values    insert values
     * @param rows      insert rows
     * @return result of query execute
     */
    public static String insert(String tableName, String[] values, String[] rows) {
        log.info("try to insert to database to" + tableName);
        db.openConnection();
        String command = "insert into " + tableName + "(" + String.join(",", rows) + ") values ('" + String.join("','", values) + "')";
        log.info(command);
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            log.error("insert failed" + ex);
            return "Группа не добавлена по причине:" + ex;
        } finally {
            db.closeConnection();
        }
        return "done";
    }

    /**
     * Insert to database with use prepare statement
     *
     * @param tableName table name
     * @param values    insert values
     * @param rows      insert rows
     * @return result of query execute
     */
    public static boolean prepareInsert(String tableName, Object[] values, String[] rows) {
        log.info("try to insert to database with Prepare Statement to" + tableName);
        Connection conn = db.getConnection();
        String command = "insert into " + tableName + "(" + String.join(",", rows) + ") values (" + generatePrepare(values.length) + ")";
        log.info(command);
        try {
            PreparedStatement ps = conn.prepareStatement(command);
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    ps.setInt(i + 1, (int) values[i]);
                } else if (values[i] instanceof InputStream) {
                    ps.setBinaryStream(i + 1, (InputStream) values[i]);
                } else {
                    ps.setString(i + 1, (String) values[i]);
                }
            }
            ps.execute();
        } catch (SQLException e) {
            log.error("error insert to database" + e);
            return false;
        } finally {
            db.closeConnection();
        }
        return true;
    }

    /**
     * Generate prepare string values query
     *
     * @param length - amount values in query
     * @return String question symbols
     */
    private static String generatePrepare(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "?";
            if (i < length - 1)
                result += ",";
        }
        return result;
    }
}
