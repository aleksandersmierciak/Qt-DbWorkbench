package com.m4gik.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * This class is represents table inside database and is responsible for
 * generating SQL querys, and inserts data to database.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Profile {

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

    private String pictureURL;

    private String politicanViews;

    private String query = "INSERT INTO `ZTBD`.`FacebookProfile` (`AboutMe`, "
            + "`Activities`,`Birthday`,`FavoriteBooks`, `FavoriteMovies`,`FavoriteMusic`,"
            + "`FavoriteQuotes`,`FavoriteTVShows`, `Firstname`,`Lastname`,`Interests`,"
            + "`PictureURL`,`PoliticalViews`,`Religion`, `SignificantOtherId`,`UpdateTime`,`UserId`,"
            + "`ProfileGender`,`LookingForGenders`, `ProfileLookingFor`, `ProfileRelationship`, "
            + "`CurrentLocation`, `HomeTownLocation`, `CurrentStatus`) "
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private Random random = new Random(0);

    private String religion;

    private Integer significantOtherId;

    private Timestamp updateTime;

    /**
     * Primary key.
     */
    private Integer userId;

    /**
     * This method prepares sql statement to insert auto-generated data.
     * 
     * @param conn
     * @return The prepared statement.
     */
    public PreparedStatement createInsert(Connection conn) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @return the activities
     */
    public String getActivities() {
        return activities;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @return the currentLocation
     */
    public Integer getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @return the currentStatus
     */
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @return the favouriteBooks
     */
    public String getFavouriteBooks() {
        return favouriteBooks;
    }

    /**
     * @return the favouriteMovies
     */
    public String getFavouriteMovies() {
        return favouriteMovies;
    }

    /**
     * @return the favouriteMusic
     */
    public String getFavouriteMusic() {
        return favouriteMusic;
    }

    /**
     * @return the favouriteQuotes
     */
    public String getFavouriteQuotes() {
        return favouriteQuotes;
    }

    /**
     * @return the favouriteTVShows
     */
    public String getFavouriteTVShows() {
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
     */
    public Integer getHomeTownLocation() {
        return homeTownLocation;
    }

    /**
     * @return the interests
     */
    public String getInterests() {
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
     * @return the pictureURL
     */
    public String getPictureURL() {
        return pictureURL;
    }

    /**
     * @return the politicanViews
     */
    public String getPoliticanViews() {
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
        return religion;
    }

    /**
     * @return the significantOtherId
     */
    public Integer getSignificantOtherId() {
        return significantOtherId;
    }

    /**
     * @return the updateTime
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

}
