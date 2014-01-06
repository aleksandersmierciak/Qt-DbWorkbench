#include "person.h"

Person::Person()
{
}

QString Person::firstName() const
{
    return this->m_firstName;
}

void Person::setFirstName(QString firstName)
{
    this->m_firstName = firstName;
}

QString Person::lastName() const
{
    return this->m_lastName;
}

void Person::setLastName(QString lastName)
{
    this->m_lastName = lastName;
}

Gender Person::gender() const
{
    return this->m_gender;
}

void Person::setGender(Gender gender)
{
    this->m_gender = gender;
}

QDateTime Person::birthday() const
{
    return this->m_birthday;
}

void Person::setBirthday(QDateTime birthday)
{
    this->m_birthday = birthday;
}

QList<IPost *> Person::posts() const
{
    return this->m_posts;
}

void Person::setPosts(QList<IPost *> posts)
{
    this->m_posts = posts;
}

QDateTime Person::lastUpdated() const
{
    return this->m_lastUpdated;
}

void Person::setLastUpdated(QDateTime lastUpdated)
{
    this->m_lastUpdated = lastUpdated;
}

QString Person::status() const
{
    return this->m_status;
}

void Person::setStatus(QString status)
{
    this->m_status = status;
}

QString Person::aboutMe() const
{
    return this->m_aboutMe;
}

void Person::setAboutMe(QString aboutMe)
{
    this->m_aboutMe = aboutMe;
}

QString Person::activities() const
{
    return this->m_activities;
}

void Person::setActivities(QString activities)
{
    this->m_activities = activities;
}

QString Person::interests() const
{
    return this->m_interests;
}

void Person::setInterests(QString interests)
{
    this->m_interests = interests;
}

QString Person::politicalViews() const
{
    return this->m_politicalViews;
}

void Person::setPoliticalViews(QString politicalViews)
{
    this->m_politicalViews = politicalViews;
}

QString Person::religion() const
{
    return this->m_religion;
}

void Person::setReligion(QString religion)
{
    this->m_religion = religion;
}

QString Person::favoriteBooks() const
{
    return this->m_favoriteBooks;
}

void Person::setFavoriteBooks(QString favoriteBooks)
{
    this->m_favoriteBooks = favoriteBooks;
}

QString Person::favoriteMovies() const
{
    return this->m_favoriteMovies;
}

void Person::setFavoriteMovies(QString favoriteMovies)
{
    this->m_favoriteMovies = favoriteMovies;
}

QString Person::favoriteMusic() const
{
    return this->m_favoriteMusic;
}

void Person::setFavoriteMusic(QString favoriteMusic)
{
    this->m_favoriteMusic = favoriteMusic;
}

QString Person::favoriteQuotes() const
{
    return this->m_favoriteQuotes;
}

void Person::setFavoriteQuotes(QString favoriteQuotes)
{
    this->m_favoriteQuotes = favoriteQuotes;
}

QString Person::favoriteTvShows() const
{
    return this->m_favoriteTvShows;
}

void Person::setFavoriteTvShows(QString favoriteTvShows)
{
    this->m_favoriteTvShows = favoriteTvShows;
}

QImage Person::picture() const
{
    return this->m_picture;
}

void Person::setPicture(QImage picture)
{
    this->m_picture = picture;
}

ActivityLookedFor Person::lookingFor() const
{
    return this->m_lookingFor;
}

void Person::setLookingFor(ActivityLookedFor lookingFor)
{
    this->m_lookingFor = lookingFor;
}

IPerson *Person::inRelationshipWith() const
{
    return this->m_inRelationshipWith;
}

void Person::setInRelationshipWith(IPerson *inRelationshipWith)
{
    this->m_inRelationshipWith = inRelationshipWith;
}

RelationshipType Person::relationshipType() const
{
    return this->m_relationshipType;
}

void Person::setRelationshipType(RelationshipType relationshipType)
{
    this->m_relationshipType = relationshipType;
}

QString Person::location() const
{
    return this->m_location;
}

void Person::setLocation(QString location)
{
    this->m_location = location;
}

bool Person::comparisonImpl(const IPerson &other) const
{
    return m_aboutMe == other.aboutMe()
        && m_activities == other.activities()
        && m_birthday == other.birthday()
        && m_favoriteBooks == other.favoriteBooks()
        && m_favoriteMovies == other.favoriteMovies()
        && m_favoriteMusic == other.favoriteMusic()
        && m_favoriteQuotes == other.favoriteQuotes()
        && m_favoriteTvShows == other.favoriteTvShows()
        && m_firstName == other.firstName()
        && m_gender == other.gender()
        && m_inRelationshipWith == other.inRelationshipWith()
        && m_interests == other.interests()
        && m_lastName == other.lastName()
        && m_lastUpdated == other.lastUpdated()
        && m_location == other.location()
        && m_lookingFor == other.lookingFor()
        && m_picture == other.picture()
        && m_politicalViews == other.politicalViews()
        && m_relationshipType == other.relationshipType()
        && m_religion == other.religion()
        && m_status == other.status();
}
