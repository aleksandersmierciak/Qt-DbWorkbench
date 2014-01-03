package com.m4gik.util;

import java.sql.Date;
import java.sql.Timestamp;

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
            + " values (?,?)";

    private String religion;

    private Integer significantOtherId;

    private Timestamp updateTime;

    private Integer userId;

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

    /**
     * @param aboutMe
     *            the aboutMe to set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * @param activities
     *            the activities to set
     */
    public void setActivities(String activities) {
        this.activities = activities;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @param currentLocation
     *            the currentLocation to set
     */
    public void setCurrentLocation(Integer currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @param currentStatus
     *            the currentStatus to set
     */
    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @param favouriteBooks
     *            the favouriteBooks to set
     */
    public void setFavouriteBooks(String favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }

    /**
     * @param favouriteMovies
     *            the favouriteMovies to set
     */
    public void setFavouriteMovies(String favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    /**
     * @param favouriteMusic
     *            the favouriteMusic to set
     */
    public void setFavouriteMusic(String favouriteMusic) {
        this.favouriteMusic = favouriteMusic;
    }

    /**
     * @param favouriteQuotes
     *            the favouriteQuotes to set
     */
    public void setFavouriteQuotes(String favouriteQuotes) {
        this.favouriteQuotes = favouriteQuotes;
    }

    /**
     * @param favouriteTVShows
     *            the favouriteTVShows to set
     */
    public void setFavouriteTVShows(String favouriteTVShows) {
        this.favouriteTVShows = favouriteTVShows;
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @param homeTownLocation
     *            the homeTownLocation to set
     */
    public void setHomeTownLocation(Integer homeTownLocation) {
        this.homeTownLocation = homeTownLocation;
    }

    /**
     * @param interests
     *            the interests to set
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @param pictureURL
     *            the pictureURL to set
     */
    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    /**
     * @param politicanViews
     *            the politicanViews to set
     */
    public void setPoliticanViews(String politicanViews) {
        this.politicanViews = politicanViews;
    }

    /**
     * @param religion
     *            the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @param significantOtherId
     *            the significantOtherId to set
     */
    public void setSignificantOtherId(Integer significantOtherId) {
        this.significantOtherId = significantOtherId;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
