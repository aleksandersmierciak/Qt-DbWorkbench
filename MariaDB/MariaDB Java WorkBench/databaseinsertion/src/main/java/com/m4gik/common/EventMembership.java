package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for EventMembership table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class EventMembership implements Insertion {

    private enum AttendanceStatus {
        Attending, Declined, NotReplied, NotSpecified, Unsure
    }

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(EventMembership.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_EVENTMEMBERSHIP_ID = "SELECT MAX(EventMembershipId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`EventMembership`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`EventMembership` (`EventMembershipId`, `UserId`, `EventId`, `AttendanceStatus`) "
            + " VALUES (?,?,?,?)";

    private Integer eventId;

    private Integer eventMembershipId;

    private Integer maxInserts;

    private Integer userId;

    /**
     * The constructor for {@link EventMembership} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public EventMembership(Integer maxInserts) throws SQLException {
        eventMembershipId = MariaDBConnection
                .findFreeId(MAX_EVENTMEMBERSHIP_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for EventMembership table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getEventMembershipId());
            preparedStatement.setInt(2, getUserId());
            preparedStatement.setInt(3, getEventId());
            preparedStatement.setString(4, new RandomEnum<AttendanceStatus>(
                    AttendanceStatus.class).random().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the eventId
     * @throws SQLException
     */
    public Integer getEventId() throws SQLException {
        eventId = randBetween(1,
                MariaDBConnection.findFreeId(Event.MAX_EVENT_ID) - 1);
        return eventId;
    }

    /**
     * @return the eventMembership
     */
    public Integer getEventMembershipId() {
        return eventMembershipId++;
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
     * This method insert random data into database structure.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#insertRandomData()
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into EventMembership is running");

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
