package com.m4gik.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * This class is responsible for connecting with database, fetching the data and
 * passing values.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class MariaDBConnection {

    /**
     * The filed holds connection (session) with a specific database. SQL
     * statements are executed and results are returned within the context of a
     * connection.
     */
    private static Connection conn = null;

    /**
     * This filed represents the name for database.
     */
    public static final String DATABASE_NAME = "`mszczygiel_ZTBD`";

    /**
     * To keep one instance of MariaDBConnection class.
     */
    private static volatile MariaDBConnection instance = null;

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger
            .getLogger(MariaDBConnection.class.getName());
    /**
     * The object used for executing a static SQL statement and returning the
     * results it produces.
     */
    private static Statement statement = null;

    /**
     * This method ends connection with database.
     * 
     * @throws SQLException
     */
    public static void endConnection() throws SQLException {
        if (getStatement() != null) {
            getStatement().close();
        }

        if (conn != null) {
            conn.close();
        }
    }

    /**
     * This method execute query and displays results.
     * 
     * @param query
     *            The query to display.
     * @throws SQLException
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);

        // TODO : Display all
        // ArrayList<String> stringsToDisplay = new ArrayList<String>();

        return result;
    }

    /**
     * This method execute prepared statement.
     * 
     * @param preparedStmt
     *            The prepared statement to execute.
     * @throws SQLException
     */
    public static void executeStatement(PreparedStatement preparedStmt)
            throws SQLException {
        preparedStmt.execute();
    }

    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Double-checked locking Singleton. Private Constructor.
     * 
     * @return the instance
     * @throws SQLException
     */
    public static MariaDBConnection getInstance(String connectionString)
            throws SQLException {
        if (instance == null) {
            synchronized (MariaDBConnection.class) {
                // Double check
                if (instance == null) {
                    instance = new MariaDBConnection(connectionString);
                }
            }
        }
        return instance;
    }

    /**
     * @return the statement
     */
    public static Statement getStatement() {
        return statement;
    }

    /**
     * This method initialize connection with database.
     * 
     * @param connectionString
     *            The connection string to init connection.
     * @throws SQLException
     */
    public static void initConnection(String connectionString)
            throws SQLException {
        if (MariaDBConnection.conn == null) {
            MariaDBConnection.conn = DriverManager
                    .getConnection(connectionString);
        }

        if (getStatement() == null) {
            setStatement(conn.createStatement());
        }
    }

    /**
     * @param conn
     *            the conn to set
     */
    public static void setConn(Connection conn) {
        MariaDBConnection.conn = conn;
    }

    /**
     * @param statement
     *            the statement to set
     */
    private static void setStatement(Statement statement) {
        MariaDBConnection.statement = statement;
    }

    /**
     * Private constructor to avoid the automatic creation of a default public
     * constructor.
     */
    @SuppressWarnings("unused")
    private MariaDBConnection() {
    }

    /**
     * The constructor, initialize connection for datebase connection.
     * 
     * @param connectionString
     *            The connection string to init connection.
     * @throws SQLException
     */
    public MariaDBConnection(String connectionString) throws SQLException {
        MariaDBConnection.conn = DriverManager.getConnection(connectionString);
        MariaDBConnection.setStatement(conn.createStatement());
    }

}
