package com.m4gik.util;

import java.sql.Time;
import java.util.Random;

/**
 * This class is represents table inside database and is responsible for
 * generating SQL querys, and inserts data to database.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class FacebookProfile {

    private enum Gender {
        Female, Male, Unspecified
    }

    private enum ProfileLookingFor {
        Dating, Friendship, RandomPlay, RelationShip, Unspecified, WhatEverICanGet
    }

    private enum ProfileRelationShip {
        Engaged, InAnOpenRelationShip, InARelationhip, IsSingle, ItsComplicated, Married, Unspecified
    }

    private static String exampleQuery = "SELECT `AboutMe`, `Activities`, `UserId` FROM `ZTBD`.`Profile`";

    public static String makeExampleQuery() {
        return exampleQuery;
    }

    private String aboutMe;

    private String activities;

    private Integer affilationCount;

    private Integer currenetLocation;

    private Integer currentStatus;

    private String favoriteBooks;

    private String favoriteMusic;

    private String favoriteQuotes;

    private String favoriteTVShows;

    private String firstName;

    private Integer homeTownLocation;

    private String interests;

    private Short isApplicationUser;

    private String lastName;

    private Integer notesCount;

    private String pictureBigURL;

    private String pictureSmallURL;

    private String pictureURL;

    private String politicalViews;

    private String query = "INSERT INTO `ZTBD`.`FacebookProfile` (`AboutMe`, `Activities`, `AffilationCount`, `Birthday`, `FavoriteBooks`, `FavoriteMovies`, `FavoriteMusic`, `FavoriteQuotes`,`FavoriteTVShows`, `Firstname`, `Lastname`, `Interests`,`isApplicationUser`, `NotesCount`, `PictureBigURL`, `PictureSmallURL`,`PictureURL`, `PoliticalViews`, `Religion`, `SchoolCount`, `SignificantOtherId`, `UpdateTime`, `WallCount`,`WebAddFriendLink`, `WebNotesByUserLink`, `WebPicturesByUserLink`,`webPicturesOfUserLink`, `WebPokeLink`, `WebPostOnUserWallLink`,`WebSendMessageLink`, `WorkPlaceCount`, `ProfileGender`,`LookingForGenders`, `ProfileLookingFor`, `ProfileRelationShip`,`CurrenetLocation`, `HomeTownLocation`, `CurrentStatus`)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Random random = new Random(0);

    private String religion;

    private Integer schoolCount;

    private Integer signifcantOtherId;

    private Time updateTime;

    private Integer UserId; // PRIMARY KEY

    private Integer wallCount;

    private String webAddFriendLink;

    private String webNotesByUserLink;

    private String webPictureOfUserLink;

    private String webPokeLink;

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
     * @return the affilationCount
     */
    public Integer getAffilationCount() {
        return affilationCount;
    }

    /**
     * @return the currenetLocation
     */
    public Integer getCurrenetLocation() {
        return currenetLocation;
    }

    /**
     * @return the currentStatus
     */
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @return the favoriteBooks
     */
    public String getFavoriteBooks() {
        return favoriteBooks;
    }

    /**
     * @return the favoriteMusic
     */
    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    /**
     * @return the favoriteQuotes
     */
    public String getFavoriteQuotes() {
        return favoriteQuotes;
    }

    /**
     * @return the favoriteTVShows
     */
    public String getFavoriteTVShows() {
        return favoriteTVShows;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        firstName = Container.firstNames[getRandom(Container.firstNames.length)];
        return firstName;
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
     * @return the isApplicationUser
     */
    public Short getIsApplicationUser() {
        return isApplicationUser;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        lastName = Container.lastNames[getRandom(Container.lastNames.length)];
        return lastName;
    }

    /**
     * @return the notesCount
     */
    public Integer getNotesCount() {
        return notesCount;
    }

    /**
     * @return the pictureBigURL
     */
    public String getPictureBigURL() {
        return pictureBigURL;
    }

    /**
     * @return the pictureSmallURL
     */
    public String getPictureSmallURL() {
        return pictureSmallURL;
    }

    /**
     * @return the pictureURL
     */
    public String getPictureURL() {
        return pictureURL;
    }

    /**
     * @return the politicalViews
     */
    public String getPoliticalViews() {
        return politicalViews;
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
     * @return the schoolCount
     */
    public Integer getSchoolCount() {
        return schoolCount;
    }

    /**
     * @return the signifcantOtherId
     */
    public Integer getSignifcantOtherId() {
        return signifcantOtherId;
    }

    /**
     * @return the updateTime
     */
    public Time getUpdateTime() {
        return updateTime;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return UserId;
    }

    /**
     * @return the wallCount
     */
    public Integer getWallCount() {
        return wallCount;
    }

    /**
     * @return the webAddFriendLink
     */
    public String getWebAddFriendLink() {
        return webAddFriendLink;
    }

    /**
     * @return the webNotesByUserLink
     */
    public String getWebNotesByUserLink() {
        return webNotesByUserLink;
    }

    /**
     * @return the webPictureOfUserLink
     */
    public String getWebPictureOfUserLink() {
        return webPictureOfUserLink;
    }

    /**
     * @return the webPokeLink
     */
    public String getWebPokeLink() {
        return webPokeLink;
    }

}
