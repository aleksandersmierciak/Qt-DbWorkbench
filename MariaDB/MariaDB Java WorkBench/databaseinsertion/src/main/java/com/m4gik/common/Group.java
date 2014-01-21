package com.m4gik.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for Group table.
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

    /**
     * Query to be executed on database
     */
    public static final String MAX_GROUP_ID = "SELECT MAX(GroupId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`Group`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`Group` (`GroupId`, `Creator`, `Description`, `GroupType`, `Picture`, `Name`, `UpdateTime`, `Website`, `Location`) "
            + " VALUES (?,?,?,?,?,?,?,?,?)";

    private Integer creator;

    private String description;

    private Integer groupId;

    private String groupType;

    private Integer location;

    private Integer maxInserts;

    private String name;

    private FileInputStream picture;

    private Random random = new Random(0);

    private Timestamp updateTime;

    private String website;

    /**
     * The constructor for {@link Group} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public Group(Integer maxInserts) throws SQLException {
        this.groupId = MariaDBConnection.findFreeId(MAX_GROUP_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for UserStatus table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getGroupId());
            preparedStatement.setInt(2, getCreator());
            preparedStatement.setString(3, getDescription());
            preparedStatement.setString(4, getGroupType());
            preparedStatement.setBlob(5, getPicture());
            preparedStatement.setString(6, getName());
            preparedStatement.setTimestamp(7, getUpdateTime());
            preparedStatement.setString(8, getWebsite());
            preparedStatement.setInt(9, getLocation());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the creator
     * @throws SQLException
     */
    public Integer getCreator() throws SQLException {
        creator = randBetween(1,
                MariaDBConnection.findFreeId(Profile.MAX_USER_ID) - 1);
        return creator;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        description = Container.abouts[getRandom(Container.abouts.length)];
        return description;
    }

    /**
     * @return the groupId
     */
    public Integer getGroupId() {
        return groupId++;
    }

    /**
     * @return the groupType
     */
    public String getGroupType() {
        groupType = Container.groupTypes[getRandom(Container.groupTypes.length)];
        return groupType;
    }

    /**
     * @return the location
     * @throws SQLException
     */
    public Integer getLocation() throws SQLException {
        String query = "SELECT COUNT(LocationId) FROM "
                + MariaDBConnection.DATABASE_NAME + ".`Location`";
        ResultSet result = MariaDBConnection.executeQuery(query);
        result.next();
        location = randBetween(1, result.getInt(1));
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
        name = Container.lastNames[getRandom(Container.lastNames.length)]
                .toUpperCase()
                + "_"
                + Container.politicanViews[getRandom(Container.politicanViews.length)];
        return name;
    }

    /**
     * @return the picture
     * @throws FileNotFoundException
     */
    public FileInputStream getPicture() throws FileNotFoundException {
        File file = new File("resources/image.jpg");
        picture = new FileInputStream(file);
        return picture;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the updateTime
     */
    public Timestamp getUpdateTime() {
        Calendar cal = Calendar.getInstance();
        updateTime = new Timestamp(cal.getTimeInMillis());
        return updateTime;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytesOfMessage = null;
        try {
            bytesOfMessage = groupId.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        website = "http://socialpage.com/"
                + messageDigest.digest(bytesOfMessage).toString();

        return website;
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
        logger.info("Process for insert data into Group is running");

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
