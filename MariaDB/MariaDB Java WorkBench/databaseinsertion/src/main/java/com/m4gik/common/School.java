package com.m4gik.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for School table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class School implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(School.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_SCHOOL_ID = "SELECT MAX(SchoolId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`School`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`School` (`SchoolId`, `Concentrations`, `GraduationYear`, `Name`) "
            + " VALUES (?,?,?,?)";

    private String concentrations;

    private String graduationYear;

    private Integer maxInserts;

    private String name;

    private Random random = new Random(0);

    private Integer schoolId;

    /**
     * The constructor for {@link School} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public School(Integer maxInserts) throws SQLException {
        schoolId = MariaDBConnection.findFreeId(MAX_SCHOOL_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for School table.
     * 
     * This method overrides an existing method.
     * 
     * @see com.m4gik.common.Insertion#createInsert(java.sql.Connection)
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getSchoolId());
            preparedStatement.setString(2, getConcentrations());
            preparedStatement.setString(3, getGraduationYear());
            preparedStatement.setString(4, getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the concentrations
     */
    public String getConcentrations() {
        concentrations = Container.concentrations[getRandom(Container.concentrations.length)];
        return concentrations;
    }

    /**
     * @return the graduationYear
     */
    public String getGraduationYear() {
        GregorianCalendar gc = new GregorianCalendar();
        Integer year = randBetween(1930, 2000);
        gc.set(Calendar.YEAR, year);

        Integer dayOfYear = randBetween(1,
                gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        graduationYear = new Date(gc.getTime().getTime()).toString();
        return graduationYear;
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
        name = "School of "
                + Container.lastNames[getRandom(Container.lastNames.length)];
        return name;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the schoolId
     */
    public Integer getSchoolId() {
        return schoolId++;
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
        logger.info("Process for insert data into School is running");

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
