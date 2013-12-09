-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.5-10.0.5-MariaDB-1~wheezy-log - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             8.1.0.4615
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for ZTBD
CREATE DATABASE IF NOT EXISTS `ZTBD` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `ZTBD`;


-- Dumping structure for table ZTBD.Affiliation
CREATE TABLE IF NOT EXISTS `Affiliation` (
  `AffiliationId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` longtext COLLATE utf8_polish_ci,
  `NID` longtext COLLATE utf8_polish_ci,
  `Status` longtext COLLATE utf8_polish_ci,
  `Year` year(4) DEFAULT NULL,
  `AffiliationType` enum('Collage','HighSchool','Work','Geography') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`AffiliationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Affiliation: ~0 rows (approximately)
/*!40000 ALTER TABLE `Affiliation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Affiliation` ENABLE KEYS */;


-- Dumping structure for table ZTBD.AffiliationList
CREATE TABLE IF NOT EXISTS `AffiliationList` (
  `AffiliationListId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `AffiliationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`AffiliationListId`),
  KEY `FK_AffiliationList_FacebookProfile` (`UserId`),
  KEY `FK_AffiliationList_Affiliation` (`AffiliationId`),
  CONSTRAINT `FK_AffiliationList_FacebookProfile` FOREIGN KEY (`UserId`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_AffiliationList_Affiliation` FOREIGN KEY (`AffiliationId`) REFERENCES `Affiliation` (`AffiliationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.AffiliationList: ~0 rows (approximately)
/*!40000 ALTER TABLE `AffiliationList` DISABLE KEYS */;
/*!40000 ALTER TABLE `AffiliationList` ENABLE KEYS */;


-- Dumping structure for table ZTBD.EventMembership
CREATE TABLE IF NOT EXISTS `EventMembership` (
  `EventMembershipId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `EventId` int(11) DEFAULT NULL,
  `RSVP` enum('Attending','Declined','NotReplied','NotSpecified','Unsure') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`EventMembershipId`),
  KEY `FK_EventMembership_FacebookProfile` (`UserId`),
  KEY `FK_EventMembership_FacebookEvent` (`EventId`),
  CONSTRAINT `FK_EventMembership_FacebookProfile` FOREIGN KEY (`UserId`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_EventMembership_FacebookEvent` FOREIGN KEY (`EventId`) REFERENCES `FacebookEvent` (`EventId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.EventMembership: ~0 rows (approximately)
/*!40000 ALTER TABLE `EventMembership` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventMembership` ENABLE KEYS */;


-- Dumping structure for table ZTBD.FacebookEvent
CREATE TABLE IF NOT EXISTS `FacebookEvent` (
  `EventId` int(11) NOT NULL AUTO_INCREMENT,
  `Creator` int(11) DEFAULT NULL,
  `Description` longtext COLLATE utf8_polish_ci,
  `EndTime` datetime DEFAULT NULL,
  `EventSubtype` longtext COLLATE utf8_polish_ci,
  `EventType` longtext COLLATE utf8_polish_ci,
  `Host` longtext COLLATE utf8_polish_ci,
  `LargePictrue` mediumblob,
  `Location` int(11) DEFAULT NULL,
  `MediumPicture` mediumblob,
  `Name` longtext COLLATE utf8_polish_ci,
  `NetworkId` int(11) DEFAULT NULL,
  `SmallPicture` blob,
  `StartTime` datetime DEFAULT NULL,
  `TagLine` longtext COLLATE utf8_polish_ci,
  `UpdatedTime` datetime DEFAULT NULL,
  `RSVP` enum('Attending','Declined','NotReplied','NotSpecified','Unsure') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`EventId`),
  KEY `FK_FacebookEvent_FacebookProfile` (`Creator`),
  KEY `FK_FacebookEvent_Location` (`Location`),
  CONSTRAINT `FK_FacebookEvent_FacebookProfile` FOREIGN KEY (`Creator`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FacebookEvent_Location` FOREIGN KEY (`Location`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.FacebookEvent: ~0 rows (approximately)
/*!40000 ALTER TABLE `FacebookEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `FacebookEvent` ENABLE KEYS */;


-- Dumping structure for table ZTBD.FacebookGroup
CREATE TABLE IF NOT EXISTS `FacebookGroup` (
  `FacebookGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `Creator` longtext COLLATE utf8_polish_ci,
  `Description` longtext COLLATE utf8_polish_ci,
  `GroupID` longtext COLLATE utf8_polish_ci,
  `GroupSubType` longtext COLLATE utf8_polish_ci,
  `GroupType` longtext COLLATE utf8_polish_ci,
  `LargePicture` mediumblob,
  `MediumPicture` mediumblob,
  `Name` longtext COLLATE utf8_polish_ci,
  `NetworkId` int(11) DEFAULT NULL,
  `Office` longtext COLLATE utf8_polish_ci,
  `RecentNews` longtext COLLATE utf8_polish_ci,
  `SmallPicture` blob,
  `UpdateTime` datetime DEFAULT NULL,
  `Website` longtext COLLATE utf8_polish_ci,
  `Venue` int(11) DEFAULT NULL,
  PRIMARY KEY (`FacebookGroupId`),
  KEY `FK_FacebookGroup_Location` (`Venue`),
  CONSTRAINT `FK_FacebookGroup_Location` FOREIGN KEY (`Venue`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.FacebookGroup: ~0 rows (approximately)
/*!40000 ALTER TABLE `FacebookGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `FacebookGroup` ENABLE KEYS */;


-- Dumping structure for table ZTBD.FacebookProfile
CREATE TABLE IF NOT EXISTS `FacebookProfile` (
  `AboutMe` longtext COLLATE utf8_polish_ci,
  `Activities` longtext COLLATE utf8_polish_ci,
  `AffilationCount` int(11) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `FavoriteBooks` longtext COLLATE utf8_polish_ci,
  `FavoriteMovies` longtext COLLATE utf8_polish_ci,
  `FavoriteMusic` longtext COLLATE utf8_polish_ci,
  `FavoriteQuotes` longtext COLLATE utf8_polish_ci,
  `FavoriteTVShows` longtext COLLATE utf8_polish_ci,
  `Firstname` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Lastname` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Interests` longtext COLLATE utf8_polish_ci,
  `isApplicationUser` tinyint(4) DEFAULT NULL,
  `NotesCount` int(11) DEFAULT NULL,
  `PictureBigURL` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `PictureSmallURL` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `PictureURL` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `PoliticalViews` longtext COLLATE utf8_polish_ci,
  `Religion` longtext COLLATE utf8_polish_ci,
  `SchoolCount` int(11) DEFAULT NULL,
  `SignificantOtherId` int(11) DEFAULT NULL,
  `UpdateTime` time DEFAULT NULL,
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `WallCount` int(11) DEFAULT NULL,
  `WebAddFriendLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WebNotesByUserLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WebPicturesByUserLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `webPicturesOfUserLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WebPokeLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WebPostOnUserWallLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WebSendMessageLink` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `WorkPlaceCount` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `ProfileGender` enum('Unspecified','Male','Female') COLLATE utf8_polish_ci DEFAULT NULL,
  `LookingForGenders` enum('Unspecified','Male','Female') COLLATE utf8_polish_ci DEFAULT NULL,
  `ProfileLookingFor` enum('Dating','Friendship','RandomPlay','RelationShip','Unspecified','WhatEverICanGet') COLLATE utf8_polish_ci DEFAULT NULL,
  `ProfileRelationShip` enum('Engaged','InAnOpenRelationShip','InARelationhip','IsSingle','ItsComplicated','Married','Unspecified') COLLATE utf8_polish_ci DEFAULT NULL,
  `CurrenetLocation` int(11) DEFAULT NULL,
  `HomeTownLocation` int(11) DEFAULT NULL,
  `CurrentStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_FacebookProfile_Location` (`CurrenetLocation`),
  KEY `FK_FacebookProfile_Location_2` (`HomeTownLocation`),
  KEY `FK_FacebookProfile_UserStatus` (`CurrentStatus`),
  CONSTRAINT `FK_FacebookProfile_Location` FOREIGN KEY (`CurrenetLocation`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FacebookProfile_Location_2` FOREIGN KEY (`HomeTownLocation`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FacebookProfile_UserStatus` FOREIGN KEY (`CurrentStatus`) REFERENCES `UserStatus` (`UserStatusId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.FacebookProfile: ~0 rows (approximately)
/*!40000 ALTER TABLE `FacebookProfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `FacebookProfile` ENABLE KEYS */;


-- Dumping structure for table ZTBD.FriendRelation
CREATE TABLE IF NOT EXISTS `FriendRelation` (
  `FriendRelationId` int(11) NOT NULL AUTO_INCREMENT,
  `AreFriends` enum('Y','N') COLLATE utf8_polish_ci DEFAULT NULL,
  `UserId1` int(11) DEFAULT '0',
  `UserId2` int(11) DEFAULT '0',
  PRIMARY KEY (`FriendRelationId`),
  KEY `FK__FacebookProfile` (`UserId1`),
  KEY `FK__FacebookProfile_2` (`UserId2`),
  CONSTRAINT `FK__FacebookProfile` FOREIGN KEY (`UserId1`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__FacebookProfile_2` FOREIGN KEY (`UserId2`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.FriendRelation: ~0 rows (approximately)
/*!40000 ALTER TABLE `FriendRelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `FriendRelation` ENABLE KEYS */;


-- Dumping structure for table ZTBD.Location
CREATE TABLE IF NOT EXISTS `Location` (
  `LocationId` int(11) NOT NULL AUTO_INCREMENT,
  `City` longtext COLLATE utf8_polish_ci,
  `Country` longtext COLLATE utf8_polish_ci,
  `State` longtext COLLATE utf8_polish_ci,
  `Street` longtext COLLATE utf8_polish_ci,
  `ZipCode` longtext COLLATE utf8_polish_ci,
  PRIMARY KEY (`LocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Location: ~0 rows (approximately)
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;


-- Dumping structure for table ZTBD.Photo
CREATE TABLE IF NOT EXISTS `Photo` (
  `AlbumID` int(11) DEFAULT NULL,
  `Caption` longtext COLLATE utf8_polish_ci,
  `Created` datetime DEFAULT NULL,
  `LargeSource` mediumblob,
  `Link` longtext COLLATE utf8_polish_ci,
  `MediumSource` mediumblob,
  `Owner` longtext COLLATE utf8_polish_ci,
  `PhotoId` int(11) NOT NULL AUTO_INCREMENT,
  `SmallSource` blob,
  PRIMARY KEY (`PhotoId`),
  KEY `FK_Photo_PhotoAlbum` (`AlbumID`),
  CONSTRAINT `FK_Photo_PhotoAlbum` FOREIGN KEY (`AlbumID`) REFERENCES `PhotoAlbum` (`AlbumId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Photo: ~0 rows (approximately)
/*!40000 ALTER TABLE `Photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Photo` ENABLE KEYS */;


-- Dumping structure for table ZTBD.PhotoAlbum
CREATE TABLE IF NOT EXISTS `PhotoAlbum` (
  `AlbumId` int(11) NOT NULL AUTO_INCREMENT,
  `CoverPhotoId` int(11) DEFAULT NULL,
  `Created` datetime DEFAULT NULL,
  `Description` longtext COLLATE utf8_polish_ci,
  `Location` longtext COLLATE utf8_polish_ci,
  `Modified` datetime DEFAULT NULL,
  `Name` longtext COLLATE utf8_polish_ci,
  `Size` int(11) DEFAULT NULL,
  PRIMARY KEY (`AlbumId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.PhotoAlbum: ~0 rows (approximately)
/*!40000 ALTER TABLE `PhotoAlbum` DISABLE KEYS */;
/*!40000 ALTER TABLE `PhotoAlbum` ENABLE KEYS */;


-- Dumping structure for table ZTBD.PhotoTag
CREATE TABLE IF NOT EXISTS `PhotoTag` (
  `PhotoTagId` int(11) NOT NULL AUTO_INCREMENT,
  `PhotoId` int(11) DEFAULT '0',
  `Position` point DEFAULT NULL,
  `User` int(11) DEFAULT '0',
  PRIMARY KEY (`PhotoTagId`),
  KEY `PhotoId` (`PhotoId`),
  KEY `User` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.PhotoTag: ~0 rows (approximately)
/*!40000 ALTER TABLE `PhotoTag` DISABLE KEYS */;
/*!40000 ALTER TABLE `PhotoTag` ENABLE KEYS */;


-- Dumping structure for table ZTBD.School
CREATE TABLE IF NOT EXISTS `School` (
  `SchoolId` int(11) NOT NULL AUTO_INCREMENT,
  `Concentrations` longtext COLLATE utf8_polish_ci,
  `GraduationYear` longtext COLLATE utf8_polish_ci,
  `Name` longtext COLLATE utf8_polish_ci,
  PRIMARY KEY (`SchoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.School: ~0 rows (approximately)
/*!40000 ALTER TABLE `School` DISABLE KEYS */;
/*!40000 ALTER TABLE `School` ENABLE KEYS */;


-- Dumping structure for table ZTBD.SchoolList
CREATE TABLE IF NOT EXISTS `SchoolList` (
  `SchoolListId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `SchoolId` int(11) DEFAULT NULL,
  PRIMARY KEY (`SchoolListId`),
  KEY `FK_SchoolList_FacebookProfile` (`UserId`),
  KEY `FK_SchoolList_School` (`SchoolId`),
  CONSTRAINT `FK_SchoolList_FacebookProfile` FOREIGN KEY (`UserId`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SchoolList_School` FOREIGN KEY (`SchoolId`) REFERENCES `School` (`SchoolId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.SchoolList: ~0 rows (approximately)
/*!40000 ALTER TABLE `SchoolList` DISABLE KEYS */;
/*!40000 ALTER TABLE `SchoolList` ENABLE KEYS */;


-- Dumping structure for table ZTBD.UserStatus
CREATE TABLE IF NOT EXISTS `UserStatus` (
  `UserStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `Status` longtext COLLATE utf8_polish_ci,
  `UpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`UserStatusId`),
  KEY `FK_UserStatus_FacebookProfile` (`UserId`),
  CONSTRAINT `FK_UserStatus_FacebookProfile` FOREIGN KEY (`UserId`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.UserStatus: ~0 rows (approximately)
/*!40000 ALTER TABLE `UserStatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserStatus` ENABLE KEYS */;


-- Dumping structure for table ZTBD.WorkPlace
CREATE TABLE IF NOT EXISTS `WorkPlace` (
  `WorkPlaceId` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyName` longtext COLLATE utf8_polish_ci,
  `Description` longtext COLLATE utf8_polish_ci,
  `EndDate` date DEFAULT NULL,
  `Positon` longtext COLLATE utf8_polish_ci,
  `StartDate` date DEFAULT NULL,
  `WorkLocation` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`WorkPlaceId`),
  KEY `FK_WorkPlace_Location` (`WorkLocation`),
  CONSTRAINT `FK_WorkPlace_Location` FOREIGN KEY (`WorkLocation`) REFERENCES `Location` (`LocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.WorkPlace: ~0 rows (approximately)
/*!40000 ALTER TABLE `WorkPlace` DISABLE KEYS */;
/*!40000 ALTER TABLE `WorkPlace` ENABLE KEYS */;


-- Dumping structure for table ZTBD.WorkPlaceList
CREATE TABLE IF NOT EXISTS `WorkPlaceList` (
  `WorkPlaceListId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `WorkPlaceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`WorkPlaceListId`),
  KEY `FK_WorkPlaceList_FacebookProfile` (`UserId`),
  KEY `FK_WorkPlaceList_WorkPlace` (`WorkPlaceId`),
  CONSTRAINT `FK_WorkPlaceList_FacebookProfile` FOREIGN KEY (`UserId`) REFERENCES `FacebookProfile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_WorkPlaceList_WorkPlace` FOREIGN KEY (`WorkPlaceId`) REFERENCES `WorkPlace` (`WorkPlaceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.WorkPlaceList: ~0 rows (approximately)
/*!40000 ALTER TABLE `WorkPlaceList` DISABLE KEYS */;
/*!40000 ALTER TABLE `WorkPlaceList` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
