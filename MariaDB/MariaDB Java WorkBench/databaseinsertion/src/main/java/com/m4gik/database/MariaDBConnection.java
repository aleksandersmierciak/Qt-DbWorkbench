package com.m4gik.database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

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

}
