package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for FriendRelation table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class FriendRelation implements Insertion {

    private enum AreFriends {
        N, Y
    }

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(FriendRelation.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_FRIENDRELATION_ID = "SELECT MAX(FriendRelationId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`FriendRelation`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`FriendRelation` (`FriendRelationId`, `AreFriends`, `UserId1`, `UserId2`) "
            + " VALUES (?,?,?,?)";

    private Integer friendRelationId;

    private Integer maxInserts;

    private Integer userId1;

    private Integer userId2;

    public FriendRelation(Integer maxInserts) throws SQLException {
        friendRelationId = MariaDBConnection.findFreeId(MAX_FRIENDRELATION_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for FriendRelation table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getFriendRelationId());
            preparedStatement.setString(2, new RandomEnum<AreFriends>(
                    AreFriends.class).random().toString());
            preparedStatement.setInt(3, getUserId1());
            preparedStatement.setInt(4, getUserId2());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the friendRelationId
     */
    public Integer getFriendRelationId() {
        return friendRelationId++;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the userId1
     * @throws SQLException
     */
    public Integer getUserId1() throws SQLException {
        userId1 = randBetween(1,
                MariaDBConnection.findFreeId(Profile.MAX_USER_ID) - 1);
        return userId1;
    }

    /**
     * @return the userId2
     * @throws SQLException
     */
    public Integer getUserId2() throws SQLException {
        userId2 = userId1;
        while (userId2 == userId1) {
            userId2 = randBetween(1,
                    MariaDBConnection.findFreeId(Profile.MAX_USER_ID) - 1);
        }

        return userId2;
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
        logger.info("Process for insert data into FriendRelation is running");

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
