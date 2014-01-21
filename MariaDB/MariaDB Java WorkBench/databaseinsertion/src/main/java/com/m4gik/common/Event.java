package com.m4gik.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for Event table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Event implements Insertion {

    private enum AttendanceStatus {
        Attending, Declined, NotReplied, NotSpecified, Unsure
    }

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger
            .getLogger(Event.class.getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_EVENT_ID = "SELECT MAX(EventId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`Event`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`Event` (`EventId`, `Creator`, `Description`, `EndTime`,"
            + " `EventSubtype`, `EventType`,"
            + "`Host`, `Location`, `Picture`, `Name`, `NetworkId`,"
            + " `StartTime`,`TagLine` ,`UpdatedTime`, `AttendanceStatus`) "
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private Integer creator;

    private String description;

    private Timestamp endTime;

    private Integer eventId;

    private String eventSubType;

    private String eventType;

    private File file = new File("resources/image.jpg");

    private String host;

    private Integer location;

    private Integer maxInserts;

    private String name;

    private Integer networkId;

    private FileInputStream picture;

    private Random random = new Random(0);

    private Timestamp startTime;

    private String tagLine;

    private Timestamp updateTime;

    public Event(Integer maxInserts) throws SQLException {
        eventId = MariaDBConnection.findFreeId(MAX_EVENT_ID);
        setMaxInserts(maxInserts);
        try {
            picture = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method create insert for Event table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getEventId());
            preparedStatement.setInt(2, getCreator());
            preparedStatement.setString(3, getDescription());
            preparedStatement.setTimestamp(4, getEndTime());
            preparedStatement.setString(5, getEventSubType());
            preparedStatement.setString(6, getEventType());
            preparedStatement.setString(7, getHost());
            preparedStatement.setInt(8, getLocation());
            preparedStatement.setBlob(9, getPicture());
            preparedStatement.setString(10, getName());
            preparedStatement.setInt(11, getNetworkId());
            preparedStatement.setTimestamp(12, getStartTime());
            preparedStatement.setString(13, getTagLine());
            preparedStatement.setTimestamp(14, getUpdateTime());
            preparedStatement.setString(15, new RandomEnum<AttendanceStatus>(
                    AttendanceStatus.class).random().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
     * @return the endTime
     */
    public Timestamp getEndTime() {
        GregorianCalendar gc = new GregorianCalendar();
        Integer year = randBetween(2015, 2016);
        gc.set(Calendar.YEAR, year);

        Integer dayOfYear = randBetween(1,
                gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        endTime = new Timestamp(gc.getTime().getTime());

        return endTime;
    }

    /**
     * @return the eventId
     */
    public Integer getEventId() {
        return eventId++;
    }

    /**
     * @return the eventSubType
     */
    public String getEventSubType() {
        eventSubType = Container.activites[getRandom(Container.activites.length)]
                + " "
                + Container.religions[getRandom(Container.religions.length)];
        return eventSubType;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        eventType = Container.groupTypes[getRandom(Container.groupTypes.length)];
        return eventType;
    }

    /**
     * @return the host
     */
    public String getHost() {
        host = Container.lastNames[getRandom(Container.lastNames.length)];
        return host;
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
        name = Container.activites[getRandom(Container.activites.length)]
                + " "
                + Container.lastNames[getRandom(Container.lastNames.length)]
                        .toUpperCase()
                + "_"
                + Container.politicanViews[getRandom(Container.politicanViews.length)];
        return name;
    }

    /**
     * @return the networkId
     */
    public Integer getNetworkId() {
        networkId = randBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
        return networkId;
    }

    /**
     * @return the picture
     * @throws FileNotFoundException
     */
    public FileInputStream getPicture() throws FileNotFoundException {
        return picture;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        GregorianCalendar gc = new GregorianCalendar();
        Integer year = randBetween(2013, 2014);
        gc.set(Calendar.YEAR, year);

        Integer dayOfYear = randBetween(1,
                gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        startTime = new Timestamp(gc.getTime().getTime());

        return startTime;
    }

    /**
     * @return the tagLine
     */
    public String getTagLine() {
        tagLine = name.substring(0, 4).toUpperCase();
        return tagLine;
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
     * This method insert random data into database structure.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#insertRandomData()
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into Event is running");

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
