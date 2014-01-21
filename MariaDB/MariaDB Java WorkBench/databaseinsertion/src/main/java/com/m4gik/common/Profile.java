package com.m4gik.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Logger;

import com.m4gik.database.MariaDBConnection;

/**
 * This class is represents table inside database and is responsible for
 * generating SQL query, and inserts data to database.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Profile implements Insertion {

    private enum Gender {
        Female, Male, Unspecified
    }

    private enum LookingForGenders {
        Female, Male, Unspecified
    }

    private enum ProfileLookingFor {
        Dating, Friendship, RandomPlay, RelationShip, Unspecified, WhatEverICanGet
    }

    private enum ProfileRelationShip {
        Engaged, InAnOpenRelationShip, InARelationhip, IsSingle, ItsComplicated, Married, Unspecified
    }

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(Profile.class
            .getName());

    /**
     * Query to be executed on database
     */
    public static final String MAX_USER_ID = "SELECT MAX(UserId) FROM "
            + MariaDBConnection.DATABASE_NAME + ".`Profile`";

    public final static String QUERY = "INSERT INTO "
            + MariaDBConnection.DATABASE_NAME
            + ".`Profile` (`AboutMe`, "
            + "`Activities`,`Birthday`,`FavoriteBooks`, `FavoriteMovies`,`FavoriteMusic`,"
            + "`FavoriteQuotes`,`FavoriteTVShows`, `Firstname`,`Lastname`,`Interests`,"
            + "`PictureURL`,`PoliticalViews`,`Religion`, `SignificantOtherId`,`UpdateTime`,`UserId`,"
            + "`ProfileGender`,`LookingForGenders`, `ProfileLookingFor`, `ProfileRelationship`, "
            + "`CurrentLocation`, `HomeTownLocation`, `CurrentStatus`) "
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String aboutMe;

    private String activities;

    private Date birthday;

    private Integer currentLocation;

    private Integer currentStatus;

    private String favouriteBooks;

    private String favouriteMovies;

    private String favouriteMusic;

    private String favouriteQuotes;

    private String favouriteTVShows;

    private String firstname;

    private Integer homeTownLocation;

    private String interests;

    private String lastname;

    private Integer maxInserts;

    private String pictureURL;

    private String politicanViews;

    private Random random = new Random(0);

    private String religion;

    private Integer significantOtherId;

    private Time updateTime;

    /**
     * Primary key.
     */
    private Integer userId;

    /**
     * The constructor for {@link Profile}.
     * 
     * @param maxInserts
     *            Parameter describe max amount of inserts, which will be put
     *            into database.
     * @throws SQLException
     */
    public Profile(Integer maxInserts) throws SQLException {
        this.userId = MariaDBConnection.findFreeId(MAX_USER_ID);
        logger.info("First free id: " + userId.toString());

        if (userId <= 1) {
            initFirstRow();
        }

        setMaxInserts(maxInserts);
    }

    /**
     * This method prepares sql statement to insert auto-generated data.
     * 
     * @param conn
     * @return The prepared statement.
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setString(1, getAboutMe());
            preparedStatement.setString(2, getActivities());
            preparedStatement.setDate(3, getBirthday());
            preparedStatement.setString(4, getFavouriteBooks());
            preparedStatement.setString(5, getFavouriteMovies());
            preparedStatement.setString(6, getFavouriteMusic());
            preparedStatement.setString(7, getFavouriteQuotes());
            preparedStatement.setString(8, getFavouriteTVShows());
            preparedStatement.setString(9, getFirstname());
            preparedStatement.setString(10, getLastname());
            preparedStatement.setString(11, getInterests());
            preparedStatement.setString(12, getPictureURL());
            preparedStatement.setString(13, getPoliticanViews());
            preparedStatement.setString(14, getReligion());
            preparedStatement.setInt(15, getSignificantOtherId());
            preparedStatement.setTime(16, getUpdateTime());
            preparedStatement.setInt(17, getUserId());
            preparedStatement.setString(18,
                    new RandomEnum<Gender>(Gender.class).random().toString());
            preparedStatement.setString(19, new RandomEnum<LookingForGenders>(
                    LookingForGenders.class).random().toString());
            preparedStatement.setString(20, new RandomEnum<ProfileLookingFor>(
                    ProfileLookingFor.class).random().toString());
            preparedStatement.setString(21,
                    new RandomEnum<ProfileRelationShip>(
                            ProfileRelationShip.class).random().toString());
            preparedStatement.setInt(22, getCurrentLocation());
            preparedStatement.setInt(23, getHomeTownLocation());
            preparedStatement.setInt(24, getCurrentStatus());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        aboutMe = Container.abouts[getRandom(Container.abouts.length)];
        return aboutMe;
    }

    /**
     * @return the activities
     */
    public String getActivities() {
        activities = Container.activites[getRandom(Container.activites.length)];
        return activities;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        GregorianCalendar gc = new GregorianCalendar();
        Integer year = randBetween(1930, 2000);
        gc.set(Calendar.YEAR, year);

        Integer dayOfYear = randBetween(1,
                gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        birthday = new Date(gc.getTime().getTime());

        return birthday;
    }

    /**
     * @return the currentLocation
     * @throws SQLException
     */
    public Integer getCurrentLocation() throws SQLException {
        String query = "SELECT COUNT(LocationId) FROM "
                + MariaDBConnection.DATABASE_NAME + ".`Location`";
        ResultSet result = MariaDBConnection.executeQuery(query);
        result.next();
        currentLocation = randBetween(1, result.getInt(1));
        return currentLocation;
    }

    /**
     * @return the currentStatus
     * @throws SQLException
     */
    public Integer getCurrentStatus() throws SQLException {
        String query = "SELECT COUNT(UserStatusId) FROM "
                + MariaDBConnection.DATABASE_NAME + ".`UserStatus`";
        ResultSet result = MariaDBConnection.executeQuery(query);
        result.next();
        currentStatus = randBetween(1, result.getInt(1));
        return currentStatus;
    }

    /**
     * @return the favouriteBooks
     */
    public String getFavouriteBooks() {
        favouriteBooks = Container.books[getRandom(Container.books.length)];
        return favouriteBooks;
    }

    /**
     * @return the favouriteMovies
     */
    public String getFavouriteMovies() {
        favouriteMovies = Container.movies[getRandom(Container.movies.length)];
        return favouriteMovies;
    }

    /**
     * @return the favouriteMusic
     */
    public String getFavouriteMusic() {
        favouriteMusic = Container.music[getRandom(Container.music.length)];
        return favouriteMusic;
    }

    /**
     * @return the favouriteQuotes
     */
    public String getFavouriteQuotes() {
        favouriteQuotes = Container.quotes[getRandom(Container.quotes.length)];
        return favouriteQuotes;
    }

    /**
     * @return the favouriteTVShows
     */
    public String getFavouriteTVShows() {
        favouriteTVShows = Container.movies[getRandom(Container.movies.length)];
        return favouriteTVShows;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        firstname = Container.firstNames[getRandom(Container.firstNames.length)];
        return firstname;
    }

    /**
     * @return the homeTownLocation
     * @throws SQLException
     */
    public Integer getHomeTownLocation() throws SQLException {
        String query = "SELECT COUNT(LocationId) FROM "
                + MariaDBConnection.DATABASE_NAME + ".`Location`";
        ResultSet result = MariaDBConnection.executeQuery(query);
        result.next();
        homeTownLocation = randBetween(1, result.getInt(1));
        return homeTownLocation;
    }

    /**
     * @return the interests
     */
    public String getInterests() {
        interests = Container.activites[getRandom(Container.activites.length)];
        return interests;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        lastname = Container.lastNames[getRandom(Container.lastNames.length)];
        return lastname;
    }

    /**
     * @return the maxInserts
     */
    public Integer getMaxInserts() {
        return maxInserts;
    }

    /**
     * @return the pictureURL
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String getPictureURL() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytesOfMessage = null;
        try {
            bytesOfMessage = userId.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        pictureURL = "http://socialpage.com/"
                + messageDigest.digest(bytesOfMessage).toString();
        return pictureURL;
    }

    /**
     * @return the politicanViews
     */
    public String getPoliticanViews() {
        politicanViews = Container.politicanViews[getRandom(Container.politicanViews.length)];
        return politicanViews;
    }

    /**
     * @return the random
     */
    public Integer getRandom(Integer value) {
        return random.nextInt(value);
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        religion = Container.religions[getRandom(Container.religions.length)];
        return religion;
    }

    /**
     * @return the significantOtherId
     * @throws SQLException
     */
    public Integer getSignificantOtherId() throws SQLException {
        if (userId >= 1) {
            significantOtherId = randBetween(1, userId - 1);
        } else {
            significantOtherId = 0;
        }

        return significantOtherId;
    }

    /**
     * @return the updateTime
     */
    public Time getUpdateTime() {
        java.util.Date date = new java.util.Date();
        updateTime = new Time(date.getTime());
        return updateTime;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId++;
    }

    /**
     * This method initialize first row.
     * 
     * @throws SQLException
     */
    private void initFirstRow() throws SQLException {
        String query = "INSERT INTO "
                + MariaDBConnection.DATABASE_NAME
                + ".`Profile` (`AboutMe` ,"
                + "`Activities` ,`Birthday` ,`FavoriteBooks` ,`FavoriteMovies` ,`FavoriteMusic` ,`FavoriteQuotes` ,"
                + "`FavoriteTVShows` ,`Firstname` ,`Lastname` ,`Interests` ,`PictureURL` ,`PoliticalViews` ,"
                + "`Religion` ,`SignificantOtherId` ,`UpdateTime` ,`UserId` ,`ProfileGender` ,`LookingForGenders` ,"
                + "`ProfileLookingFor` ,`ProfileRelationship` ,`CurrentLocation` ,`HomeTownLocation` ,`CurrentStatus`)"
                + " VALUES (NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL , '', '',"
                + " NULL , NULL , NULL , NULL , NULL , NULL , '0', NULL , NULL , NULL , NULL , NULL , NULL , NULL)";
        MariaDBConnection.executeUpdate(query);
    }

    /**
     * This method starts process for insert random data to database.
     */
    public void insertRandomData() {
        long start_time = System.nanoTime();
        logger.info("Process for insert data into Profile is running");

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

    /**
     * This method is only for test purpose.
     * 
     * @return something expected.
     */
    public Date testMethod() {
        return getBirthday();
    }

}
