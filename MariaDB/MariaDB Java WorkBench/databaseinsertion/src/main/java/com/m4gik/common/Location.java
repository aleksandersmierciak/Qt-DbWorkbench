package com.m4gik.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * 
 * This class is responsible for generate data for Location table.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Location implements Insertion {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(Location.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_LOCATION_ID = "SELECT MAX(LocationId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`Location`";

    public static final String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`Location` (`LocationId`, `City`, `Country`, `State`, `Street`, `ZipCode`) "
            + " VALUES (?,?,?,?,?,?)";

    private String city;

    private String country;

    private Integer locationId;

    private Integer maxInserts;

    private Random random = new Random(0);

    private String state;

    private String street;

    private String zipCode;

    /**
     * The constructor for {@link Location} class.
     * 
     * @param maxInserts
     * @throws SQLException
     */
    public Location(Integer maxInserts) throws SQLException {
        this.locationId = MariaDBConnection.findFreeId(MAX_LOCATION_ID);
        setMaxInserts(maxInserts);
    }

    /**
     * This method create insert for Location table.
     * 
     * @param conn
     *            Parameter to connect.
     * @return Prepared Statement with data to execute.
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, getLocationId());
            preparedStatement.setString(2, getCity());
            preparedStatement.setString(3, getCountry());
            preparedStatement.setString(4, getState());
            preparedStatement.setString(5, getStreet());
            preparedStatement.setString(6, getZipCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the city
     */
    public String getCity() {
        city = Container.cities[getRandom(Container.cities.length)];
        return city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        Locale locale = new Locale("",
                Container.countries[getRandom(Container.countries.length)]);
        country = locale.getDisplayCountry(locale);
        return country;
    }

    /**
     * @return the locationId
     */
    public Integer getLocationId() {
        return locationId++;
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
     * @return the state
     */
    public String getState() {
        state = "";
        return state;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        street = Container.streets[getRandom(Container.streets.length)];
        return street;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        String stringBuilder = randBetween(10, 58).toString();
        stringBuilder += "-" + randBetween(100, 999).toString();
        zipCode = stringBuilder;
        return zipCode;
    }

    /**
     * This method insert random data into database structure.
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into Location is running");

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
