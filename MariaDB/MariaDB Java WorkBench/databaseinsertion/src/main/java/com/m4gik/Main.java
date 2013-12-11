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
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        MariaDBConnection
                .getInstance("jdbc:mysql://127.0.0.1:3306/ZTBD?user=root&password=root");
        System.out.println("Working");
        MariaDBConnection.executeQuery(FacebookProfile.makeExampleQuery());
    }
}
