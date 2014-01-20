package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 
 * Interface for be abstract for creating insertion operation into database.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public interface Insertion {

    /**
     * This method creates single based insert into proper table.
     * 
     * @param conn
     *            Parameter to connect.
     * @return Prepared Statement with data to execute.
     */
    public PreparedStatement createInsert(Connection conn);

    /**
     * This method insert random data into database structure.
     */
    public void insertRandomData();
}
