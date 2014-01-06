package com.m4gik;

import java.sql.SQLException;

import com.m4gik.database.MariaDBConnection;
import com.m4gik.util.FacebookProfile;

/**
 * The Main class, executing generated inserts for MariaDB database.
 * 
 */
public class Main {

    /**
     * Main method.
     * 
     * @param args
     *            jdbc:mysql://kulisz.eu:3306/mszczygiel_ZTBD?user=xxx&
     *            password=xxx
     * @throws SQLException
     */
    public static void main(final String... args) throws SQLException {
        MariaDBConnection.getInstance(args[0]);
        System.out.println("Working");
        MariaDBConnection.executeQuery(FacebookProfile.makeExampleQuery());
    }
}
