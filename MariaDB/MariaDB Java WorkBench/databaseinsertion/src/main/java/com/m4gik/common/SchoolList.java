package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for SchoolList table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class SchoolList implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(SchoolList.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_SCHOOLLIST_ID = "SELECT MAX(SchoolListId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`SchoolList`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`SchoolList` (`SchoolListId`, `UserId`, `SchoolId`) "
            + " VALUES (?,?,?)";

    private Integer maxInserts;

    private Integer schoolId;

    private Integer schoolListId;

    private Integer userId;

    /**
     * The constructor for {@link SchoolList} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public SchoolList(Integer maxInserts) throws SQLException {
        schoolListId = MariaDBConnection.findFreeId(MAX_SCHOOLLIST_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for SchoolList table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getSchoolListId());
            preparedStatement.setInt(2, getUserId());
            preparedStatement.setInt(3, getSchoolId());
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
     * @return the schoolId
     * @throws SQLException
     */
    public Integer getSchoolId() throws SQLException {
        schoolId = randBetween(1,
                MariaDBConnection.findFreeId(School.MAX_SCHOOL_ID) - 1);
        return schoolId;
    }

    /**
     * @return the schoolListId
     */
    public Integer getSchoolListId() {
        return schoolListId++;
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
     * This method insert random data into database structure.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#insertRandomData()
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into SchoolList is running");

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
