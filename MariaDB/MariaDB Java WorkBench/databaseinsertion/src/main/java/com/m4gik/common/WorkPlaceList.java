package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for WorkPlace table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class WorkPlaceList implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(WorkPlaceList.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_WORKPLACELIST_ID = "SELECT MAX(WorkPlaceListId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`WorkPlaceList`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`WorkPlaceList` (`WorkPlaceListId`, `UserId`, `WorkPlaceId`) "
            + " VALUES (?,?,?)";

    private Integer maxInserts;

    private Integer userId;

    private Integer workPlaceId;

    private Integer workPlaceListId;

    public WorkPlaceList(Integer maxInserts) throws SQLException {
        workPlaceListId = MariaDBConnection.findFreeId(MAX_WORKPLACELIST_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for WorkPlaceList table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getWorkPlaceListId());
            preparedStatement.setInt(2, getUserId());
            preparedStatement.setInt(3, getWorkPlaceId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the userId
     * @throws SQLException
     */
    public Integer getUserId() throws SQLException {
        userId = randBetween(1,
                MariaDBConnection.findFreeId(Profile.MAX_USER_ID) - 1);
        return userId;
    }

    /**
     * @return the workPlaceId
     * @throws SQLException
     */
    public Integer getWorkPlaceId() throws SQLException {
        workPlaceId = randBetween(1,
                MariaDBConnection.findFreeId(WorkPlace.MAX_WORKPLACE_ID) - 1);
        return workPlaceId;
    }

    /**
     * @return the workPlaceList
     */
    public Integer getWorkPlaceListId() {
        return workPlaceListId++;
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
        logger.info("Process for insert data into WorkPlaceList is running");

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
     * This method creates data between given range.
     * 
     * @param start
     * @param end
     * @return Value between given range.
     */
    public Integer randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    /**
     * @param maxInserts
     *            the maxInserts to set
     */
    public void setMaxInserts(Integer maxInserts) {
        this.maxInserts = maxInserts;
    }

}
