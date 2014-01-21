package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for GroupMembership table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class GroupMembership implements Insertion {

    private enum GroupMembershipType {
        Admin, Member, NotReplied, Officer
    }

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(GroupMembership.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_GROUPMEMBERSHIP_ID = "SELECT MAX(GroupMembershipId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`GroupMembership`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`GroupMembership` (`GroupMembershipId`, `GroupId`, `UserId`, `GroupMembershipType`) "
            + " VALUES (?,?,?,?)";

    private Integer groupId;

    private Integer groupMembershipId;

    private Integer maxInserts;

    private Integer userId;

    /**
     * The constructor for {@link GroupMembership} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public GroupMembership(Integer maxInserts) throws SQLException {
        groupMembershipId = MariaDBConnection
                .findFreeId(MAX_GROUPMEMBERSHIP_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for GroupMembership table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getGroupMembershipId());
            preparedStatement.setInt(2, getGroupId());
            preparedStatement.setInt(3, getUserId());
            preparedStatement.setString(4, new RandomEnum<GroupMembershipType>(
                    GroupMembershipType.class).random().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the groupId
     * @throws SQLException
     */
    public Integer getGroupId() throws SQLException {
        groupId = randBetween(1,
                MariaDBConnection.findFreeId(Group.MAX_GROUP_ID) - 1);
        return groupId;
    }

    /**
     * @return the groupMembershipId
     */
    public Integer getGroupMembershipId() {
        return groupMembershipId++;
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
                MariaDBConnection.findFreeId(Profile.MAX_USER_ID));
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
        logger.info("Process for insert data into GroupMembership is running");

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
