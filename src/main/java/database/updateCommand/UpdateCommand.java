package database.updateCommand;

import database.configuration.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class UpdateCommand {
    private static final Logger log = Logger.getLogger(UpdateCommand.class.getName());

    private static DatabaseConnection db = new DatabaseConnection();

    public static String updateTable(String tableName, String[] args, String[] where) {
        log.info("try to update table " + tableName);
        String query = "update " + tableName + " set " + String.join(",", args) + " where " + String.join(" and ", where);
        log.info(query);
        System.out.println(query);
        db.openConnection();
        try {
            db.st.execute(query);
        } catch (SQLException ex) {
            log.error("error update database table" + ex);
            return "Что-то пошло не так" + ex;
        } finally {
            db.closeConnection();
        }
        return "ok";
    }
}
