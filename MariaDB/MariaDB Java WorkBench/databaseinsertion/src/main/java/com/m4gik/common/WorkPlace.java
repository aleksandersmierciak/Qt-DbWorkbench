package com.m4gik.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for WorkPlace table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class WorkPlace implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(WorkPlace.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_WORKPLACE_ID = "SELECT MAX(WorkPlaceId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`WorkPlace`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`WorkPlace` (`WorkPlaceId`, `CompanyName`, `Description`, `EndDate`, `Positon`, `StartDate`, `WorkLocation`) "
            + " VALUES (?,?,?,?,?,?,?)";

    private String comapnyName;

    private String description;

    private Date endDate;

    private Integer maxInserts;

    private String position;

    private Random random = new Random(0);

    private Date startDate;

    private Integer workLocation;

    private Integer workPlaceId;

    public WorkPlace(Integer maxInserts) throws SQLException {
        workPlaceId = MariaDBConnection.findFreeId(MAX_WORKPLACE_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for WorkPlace table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getWorkPlaceId());
            preparedStatement.setString(2, getComapnyName());
            preparedStatement.setString(3, getDescription());
            preparedStatement.setDate(4, getEndDate());
            preparedStatement.setString(5, getPosition());
            preparedStatement.setDate(6, getStartDate());
            preparedStatement.setInt(7, getWorkLocation());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the comapnyName
     */
    public String getComapnyName() {
        comapnyName = Container.activites[getRandom(Container.activites.length)]
                .toUpperCase()
                + " "
                + Container.lastNames[getRandom(Container.lastNames.length)]
                        .toUpperCase() + "X";
        return comapnyName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        description = comapnyName + " is "
                + Container.abouts[getRandom(Container.abouts.length)];
        return description;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        GregorianCalendar gc = new GregorianCalendar();
        Integer year = randBetween(1999, 2013);
        gc.set(Calendar.YEAR, year);

        Integer dayOfYear = randBetween(1,
                gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        endDate = new Date(gc.getTime().getTime());

        return endDate;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        position = Container.positions[getRandom(Container.positions.length)];
        return position;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        startDate = endDate;
        while (startDate.before(endDate)) {
            GregorianCalendar gc = new GregorianCalendar();
            Integer year = randBetween(1990, 2013);
            gc.set(Calendar.YEAR, year);

            Integer dayOfYear = randBetween(1,
                    gc.getActualMaximum(Calendar.DAY_OF_YEAR));
            gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

            startDate = new Date(gc.getTime().getTime());
        }
        return startDate;
    }

    /**
     * @return the workLocation
     * @throws SQLException
     */
    public Integer getWorkLocation() throws SQLException {
        String query = "SELECT COUNT(LocationId) FROM "
                + MariaDBConnection.DATABASE_NAME + ".`Location`";
        ResultSet result = MariaDBConnection.executeQuery(query);
        result.next();
        workLocation = randBetween(1, result.getInt(1));

        return workLocation;
    }

    /**
     * @return the workPlaceId
     */
    public Integer getWorkPlaceId() {
        return workPlaceId++;
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
        logger.info("Process for insert data into WorkPlace is running");

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
