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


-- Dumping structure for table ZTBD.Event
CREATE TABLE IF NOT EXISTS `Event` (
  `EventId` int(11) NOT NULL AUTO_INCREMENT,
  `Creator` int(11) DEFAULT NULL,
  `Description` longtext COLLATE utf8_polish_ci,
  `EndTime` datetime DEFAULT NULL,
  `EventSubtype` longtext COLLATE utf8_polish_ci,
  `EventType` longtext COLLATE utf8_polish_ci,
  `Host` longtext COLLATE utf8_polish_ci,
  `Location` int(11) DEFAULT NULL,
  `Picture` mediumblob,
  `Name` longtext COLLATE utf8_polish_ci,
  `NetworkId` int(11) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `TagLine` longtext COLLATE utf8_polish_ci,
  `UpdatedTime` datetime DEFAULT NULL,
  `AttendanceStatus` enum('Attending','Declined','NotReplied','NotSpecified','Unsure') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`EventId`),
  KEY `FK_FacebookEvent_FacebookProfile` (`Creator`),
  KEY `FK_FacebookEvent_Location` (`Location`),
  CONSTRAINT `FK_FacebookEvent_FacebookProfile` FOREIGN KEY (`Creator`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FacebookEvent_Location` FOREIGN KEY (`Location`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Event: ~0 rows (approximately)
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;


-- Dumping structure for table ZTBD.EventMembership
CREATE TABLE IF NOT EXISTS `EventMembership` (
  `EventMembershipId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `EventId` int(11) DEFAULT NULL,
  `AttendanceStatus` enum('Attending','Declined','NotReplied','NotSpecified','Unsure') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`EventMembershipId`),
  KEY `FK_EventMembership_Event` (`EventId`),
  KEY `FK_EventMembership_Profile` (`UserId`),
  CONSTRAINT `FK_EventMembership_Event` FOREIGN KEY (`EventId`) REFERENCES `Event` (`EventId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_EventMembership_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.EventMembership: ~0 rows (approximately)
/*!40000 ALTER TABLE `EventMembership` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventMembership` ENABLE KEYS */;


-- Dumping structure for table ZTBD.FriendRelation
CREATE TABLE IF NOT EXISTS `FriendRelation` (
  `FriendRelationId` int(11) NOT NULL AUTO_INCREMENT,
  `AreFriends` enum('Y','N') COLLATE utf8_polish_ci DEFAULT NULL,
  `UserId1` int(11) DEFAULT '0',
  `UserId2` int(11) DEFAULT '0',
  PRIMARY KEY (`FriendRelationId`),
  KEY `FK__Profile` (`UserId1`),
  KEY `FK__Profile_2` (`UserId2`),
  CONSTRAINT `FK__Profile` FOREIGN KEY (`UserId1`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__Profile_2` FOREIGN KEY (`UserId2`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.FriendRelation: ~0 rows (approximately)
/*!40000 ALTER TABLE `FriendRelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `FriendRelation` ENABLE KEYS */;


-- Dumping structure for table ZTBD.Group
CREATE TABLE IF NOT EXISTS `Group` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `Creator` int(11) DEFAULT NULL,
  `Description` longtext COLLATE utf8_polish_ci,
  `GroupType` longtext COLLATE utf8_polish_ci,
  `Picture` mediumblob,
  `Name` longtext COLLATE utf8_polish_ci,
  `UpdateTime` datetime DEFAULT NULL,
  `Website` longtext COLLATE utf8_polish_ci,
  `Location` int(11) DEFAULT NULL,
  PRIMARY KEY (`GroupId`),
  KEY `FK_Group_Profile` (`Creator`),
  KEY `FK_Group_Location` (`Location`),
  CONSTRAINT `FK_Group_Profile` FOREIGN KEY (`Creator`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Group_Location` FOREIGN KEY (`Location`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Group: ~0 rows (approximately)
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;


-- Dumping structure for table ZTBD.GroupMembership
CREATE TABLE IF NOT EXISTS `GroupMembership` (
  `GroupMembershipId` int(11) NOT NULL,
  `FacebookGroupId` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `GroupMembershipType` enum('NotReplied','Member','Officer','Admin') COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`GroupMembershipId`),
  KEY `FK_GroupMembership_Group` (`FacebookGroupId`),
  KEY `FK_GroupMembership_Profile` (`UserId`),
  CONSTRAINT `FK_GroupMembership_Group` FOREIGN KEY (`FacebookGroupId`) REFERENCES `Group` (`GroupId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GroupMembership_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.GroupMembership: ~0 rows (approximately)
/*!40000 ALTER TABLE `GroupMembership` DISABLE KEYS */;
/*!40000 ALTER TABLE `GroupMembership` ENABLE KEYS */;


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
  `Source` mediumblob,
  `Link` longtext COLLATE utf8_polish_ci,
  `Owner` int(11) DEFAULT NULL,
  `PhotoId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`PhotoId`),
  KEY `FK_Photo_PhotoAlbum` (`AlbumID`),
  KEY `FK_Photo_Profile` (`Owner`),
  CONSTRAINT `FK_Photo_Profile` FOREIGN KEY (`Owner`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
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
  `Size` point DEFAULT NULL,
  PRIMARY KEY (`AlbumId`),
  KEY `FK_PhotoAlbum_Photo` (`CoverPhotoId`),
  CONSTRAINT `FK_PhotoAlbum_Photo` FOREIGN KEY (`CoverPhotoId`) REFERENCES `Photo` (`PhotoId`) ON DELETE CASCADE ON UPDATE CASCADE
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
  KEY `FK_PhotoTag_Photo` (`PhotoId`),
  KEY `FK_PhotoTag_Profile` (`User`),
  CONSTRAINT `FK_PhotoTag_Photo` FOREIGN KEY (`PhotoId`) REFERENCES `Photo` (`PhotoId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PhotoTag_Profile` FOREIGN KEY (`User`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.PhotoTag: ~0 rows (approximately)
/*!40000 ALTER TABLE `PhotoTag` DISABLE KEYS */;
/*!40000 ALTER TABLE `PhotoTag` ENABLE KEYS */;


-- Dumping structure for table ZTBD.Post
CREATE TABLE IF NOT EXISTS `Post` (
  `PostId` int(11) NOT NULL AUTO_INCREMENT,
  `Header` longtext COLLATE utf8_polish_ci,
  `Text` longtext COLLATE utf8_polish_ci,
  PRIMARY KEY (`PostId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Post: ~0 rows (approximately)
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;


-- Dumping structure for table ZTBD.PostList
CREATE TABLE IF NOT EXISTS `PostList` (
  `PostListId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL DEFAULT '0',
  `PostId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PostListId`),
  KEY `FK_PostList_Profile` (`UserId`),
  KEY `FK_PostList_Post` (`PostId`),
  CONSTRAINT `FK_PostList_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PostList_Post` FOREIGN KEY (`PostId`) REFERENCES `Post` (`PostId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.PostList: ~0 rows (approximately)
/*!40000 ALTER TABLE `PostList` DISABLE KEYS */;
/*!40000 ALTER TABLE `PostList` ENABLE KEYS */;


-- Dumping structure for table ZTBD.Profile
CREATE TABLE IF NOT EXISTS `Profile` (
  `AboutMe` longtext COLLATE utf8_polish_ci,
  `Activities` longtext COLLATE utf8_polish_ci,
  `Birthday` date DEFAULT NULL,
  `FavoriteBooks` longtext COLLATE utf8_polish_ci,
  `FavoriteMovies` longtext COLLATE utf8_polish_ci,
  `FavoriteMusic` longtext COLLATE utf8_polish_ci,
  `FavoriteQuotes` longtext COLLATE utf8_polish_ci,
  `FavoriteTVShows` longtext COLLATE utf8_polish_ci,
  `Firstname` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Lastname` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Interests` longtext COLLATE utf8_polish_ci,
  `PictureURL` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `PoliticalViews` longtext COLLATE utf8_polish_ci,
  `Religion` longtext COLLATE utf8_polish_ci,
  `SignificantOtherId` int(11) DEFAULT NULL,
  `UpdateTime` time DEFAULT NULL,
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `ProfileGender` enum('Unspecified','Male','Female') COLLATE utf8_polish_ci DEFAULT NULL,
  `LookingForGenders` enum('Unspecified','Male','Female') COLLATE utf8_polish_ci DEFAULT NULL,
  `ProfileLookingFor` enum('Dating','Friendship','RandomPlay','RelationShip','Unspecified','WhatEverICanGet') COLLATE utf8_polish_ci DEFAULT NULL,
  `ProfileRelationship` enum('Engaged','InAnOpenRelationShip','InARelationhip','IsSingle','ItsComplicated','Married','Unspecified') COLLATE utf8_polish_ci DEFAULT NULL,
  `CurrentLocation` int(11) DEFAULT NULL,
  `HomeTownLocation` int(11) DEFAULT NULL,
  `CurrentStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_Profile_Location` (`CurrentLocation`),
  KEY `FK_Profile_Location_2` (`HomeTownLocation`),
  KEY `FK_Profile_UserStatus` (`CurrentStatus`),
  KEY `FK_Profile_Profile` (`SignificantOtherId`),
  CONSTRAINT `FK_Profile_Location` FOREIGN KEY (`CurrentLocation`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Profile_Location_2` FOREIGN KEY (`HomeTownLocation`) REFERENCES `Location` (`LocationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Profile_Profile` FOREIGN KEY (`SignificantOtherId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Profile_UserStatus` FOREIGN KEY (`CurrentStatus`) REFERENCES `UserStatus` (`UserStatusId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.Profile: ~0 rows (approximately)
/*!40000 ALTER TABLE `Profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `Profile` ENABLE KEYS */;


-- Dumping structure for table ZTBD.ReceiverPost
CREATE TABLE IF NOT EXISTS `ReceiverPost` (
  `ReceiverPostId` int(11) NOT NULL AUTO_INCREMENT,
  `PostId` int(11) NOT NULL DEFAULT '0',
  `Receiver` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ReceiverPostId`),
  KEY `FK_ReceiverPost_Post` (`PostId`),
  KEY `FK_ReceiverPost_Profile` (`Receiver`),
  CONSTRAINT `FK_ReceiverPost_Post` FOREIGN KEY (`PostId`) REFERENCES `Post` (`PostId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ReceiverPost_Profile` FOREIGN KEY (`Receiver`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.ReceiverPost: ~0 rows (approximately)
/*!40000 ALTER TABLE `ReceiverPost` DISABLE KEYS */;
/*!40000 ALTER TABLE `ReceiverPost` ENABLE KEYS */;


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
  KEY `FK_SchoolList_School` (`SchoolId`),
  KEY `FK_SchoolList_Profile` (`UserId`),
  CONSTRAINT `FK_SchoolList_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
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
  KEY `FK_UserStatus_Profile` (`UserId`),
  CONSTRAINT `FK_UserStatus_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
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
  KEY `FK_WorkPlaceList_WorkPlace` (`WorkPlaceId`),
  KEY `FK_WorkPlaceList_Profile` (`UserId`),
  CONSTRAINT `FK_WorkPlaceList_Profile` FOREIGN KEY (`UserId`) REFERENCES `Profile` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_WorkPlaceList_WorkPlace` FOREIGN KEY (`WorkPlaceId`) REFERENCES `WorkPlace` (`WorkPlaceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Dumping data for table ZTBD.WorkPlaceList: ~0 rows (approximately)
/*!40000 ALTER TABLE `WorkPlaceList` DISABLE KEYS */;
/*!40000 ALTER TABLE `WorkPlaceList` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
