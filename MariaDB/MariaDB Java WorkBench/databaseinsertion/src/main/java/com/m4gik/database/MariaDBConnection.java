package com.m4gik.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is responsible for connecting with database, fetching the data and
 * passing values.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class MariaDBConnection {

    /**
     * 
     */
    private Connection conn = null;

    /**
     * 
     */
    private Statement statement = null;

    /**
     * 
     * @param connectionString
     * @throws SQLException
     */
    public MariaDBConnection(String connectionString) throws SQLException {
        this.conn = DriverManager.getConnection(connectionString);
        this.setStatement(conn.createStatement());
    }

    /**
     * @return the statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * @param statement
     *            the statement to set
     */
    private void setStatement(Statement statement) {
        this.statement = statement;
    }

}
