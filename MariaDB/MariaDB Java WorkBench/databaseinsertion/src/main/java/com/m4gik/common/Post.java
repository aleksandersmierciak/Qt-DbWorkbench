package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for Post table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Post implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(Post.class.getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_POST_ID = "SELECT MAX(PostId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`Post`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`Post` (`PostId`, `Header`, `Text`) " + " VALUES (?,?,?)";

    private String header;

    private Integer maxInserts;

    private Integer postId;

    private Random random = new Random(0);

    private String text;

    public Post(Integer maxInserts) throws SQLException {
        postId = MariaDBConnection.findFreeId(MAX_POST_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for Post table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getPostId());
            preparedStatement.setString(2, getHeader());
            preparedStatement.setString(3, getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        header = Container.books[getRandom(Container.books.length)];
        return header;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the postId
     */
    public Integer getPostId() {
        return postId++;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the text
     */
    public String getText() {
        text = Container.abouts[getRandom(Container.abouts.length)] + "\n"
                + Container.quotes[getRandom(Container.quotes.length)];
        return text;
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
        logger.info("Process for insert data into Post is running");

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
