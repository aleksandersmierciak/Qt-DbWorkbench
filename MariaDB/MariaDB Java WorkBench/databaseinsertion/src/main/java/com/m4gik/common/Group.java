package com.m4gik.common;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * his class is responsible for generate data for Group table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Group implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger
            .getLogger(Group.class.getName());

    private Integer creator;

    private String description;

    private Integer groupId;

    private String groupType;

    private Integer location;

    private Integer maxInserts;

    private String name;

    private Blob picture;

    private Timestamp updateTime;

    /**
     * This method create insert for UserStatus table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @return the groupType
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @return the location
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the picture
     */
    public Blob getPicture() {
        return picture;
    }

    // ByteArrayInputStream bais = new ByteArrayInputStream(bindata);

    /**
     * @return the updateTime
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * This method insert random data into database structure.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#insertRandomData()
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into UserStatus is running");

        for (int i = 0; i < getMaxInserts(); i++) {
            try {
                MariaDBConnection
                        .executeStatement(createInsert(MariaDBConnection
                                .getConn()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end_time = System.nanoTime();
        logger.info("End of process (time operation: "
                + ((end_time - start_time) / 1e6) + " milliseconds)");
    }

    /**
     * @param maxInserts
     *            the maxInserts to set
     */
    public void setMaxInserts(Integer maxInserts) {
        this.maxInserts = maxInserts;
    }

}
