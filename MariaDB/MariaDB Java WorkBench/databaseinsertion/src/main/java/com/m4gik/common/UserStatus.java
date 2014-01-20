package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generating data for UserStatus table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class UserStatus implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(UserStatus.class
            .getName());

    /**
     * Query to be executed on database
     */
    private static final String MAX_USER_ID = "SELECT MAX(UserStatusId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`UserStatus`";

    private Integer maxInserts;

    private String query = "INSERT INTO " + MariaDBConnection.DATABASE_NAME
            + ".`UserStatus` (`UserStatusId`, `Status`, `UpdateTime`) "
            + " VALUES (?,?,?)";

    private Random random = new Random(0);

    private String status;

    private Timestamp updateTime;

    private Integer userId;

    private Integer userStatusId;

    /**
     * The constructor for {@link UserStatus}.
     * 
     * @throws SQLException
     */
    public UserStatus(Integer maxInserts) throws SQLException {
        this.userStatusId = MariaDBConnection.findFreeId(MAX_USER_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for UserStatus table.
     * 
     * @param conn
     *            Parameter to connect.
     * @return Prepared Statement with data to execute.
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, getUserStatusId());
            preparedStatement.setString(2, getStatus());
            preparedStatement.setTimestamp(3, getUpdateTime());
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
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        status = Container.status[getRandom(Container.status.length)];
        return status;
    }

    /**
     * @return the updateTiem
     */
    public Timestamp getUpdateTime() {
        Calendar cal = Calendar.getInstance();
        updateTime = new Timestamp(cal.getTimeInMillis());
        return updateTime;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @return the userStatusId
     */
    public Integer getUserStatusId() {
        return userStatusId++;
    }

    /**
     * This method insert random data into database structure.
     */
    public void insertRandomData() {
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
        logger.info("End of process");
    }

    /**
     * @param maxInserts
     *            the maxInserts to set
     */
    public void setMaxInserts(Integer maxInserts) {
        this.maxInserts = maxInserts;
    }

}
