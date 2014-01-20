package com.m4gik;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.common.UserStatus;
import com.m4gik.database.MariaDBConnection;

/**
 * The Main class, executing generated inserts for MariaDB database.
 * 
 */
public class Main {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * This method fills database with random insertion.
     * 
     * @param amount
     *            The amount of insertion.
     * @throws SQLException
     */
    private static void fillDatabase(Integer amount) throws SQLException {
        UserStatus userStatus = new UserStatus(amount / 10);
        userStatus.insertRandomData();
        // Profile profile = new Profile(amount);
        // profile.insertRandomData();
    }

    /**
     * This method initialize database structure.
     */
    private static void initializeDatabaseStructure() {
        // TODO Auto-generated method stub
    }

    /**
     * Main method.
     * 
     * @param args
     *            jdbc:mysql://kulisz.eu:3306/mszczygiel_ZTBD?user=xxx&
     *            password=xxx
     * @throws SQLException
     */
    public static void main(final String... args) throws SQLException {
        if (args.length > 1) {
            MariaDBConnection.getInstance(args[0]);
            initializeDatabaseStructure();
            fillDatabase(Integer.parseInt(args[1]));
            MariaDBConnection.endConnection();
        } else {
            logger.warning("Connection string is missing");
        }
    }
}
