package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for PostList table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class PostList implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(PostList.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_POSTLIST_ID = "SELECT MAX(PostListId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`PostList`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`PostList` (`PostListId`, `UserId`, `PostId`) "
            + " VALUES (?,?,?)";

    private Integer maxInserts;

    private Integer postId;

    private Integer postListId;

    private Integer userId;

    public PostList(Integer maxInserts) throws SQLException {
        postListId = MariaDBConnection.findFreeId(MAX_POSTLIST_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for PostList table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getPostListId());
            preparedStatement.setInt(2, getUserId());
            preparedStatement.setInt(3, getPostId());
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
     * @return the postId
     * @throws SQLException
     */
    public Integer getPostId() throws SQLException {
        postId = randBetween(1,
                MariaDBConnection.findFreeId(Post.MAX_POST_ID) - 1);
        return postId;
    }

    /**
     * @return the postListId
     */
    public Integer getPostListId() {
        return postListId++;
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
        logger.info("Process for insert data into PostList is running");

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
