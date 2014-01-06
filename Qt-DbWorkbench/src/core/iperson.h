#ifndef IPERSON_H
#define IPERSON_H

#include <QDateTime>
#include <QImage>
#include <QList>
#include <QString>

#include "iequatable.h"
#include "ipost.h"
#include "gender.h"
#include "activitylookedfor.h"
#include "relationshiptype.h"

class IPost;

class IPerson : virtual public IEquatable<IPerson>
{
public:
    IPerson() = default;
    virtual ~IPerson() {}

    IPerson(const IPerson &) = delete;
    IPerson &operator =(const IPerson &) = delete;

    virtual QString firstName() const = 0;
    virtual void setFirstName(QString ) = 0;
    virtual QString lastName() const = 0;
    virtual void setLastName(QString ) = 0;
    virtual Gender gender() const = 0;
    virtual void setGender(Gender ) = 0;
    virtual QDateTime birthday() const = 0;
    virtual void setBirthday(QDateTime ) = 0;

    virtual QList<IPost *> posts() const = 0;
    virtual void setPosts(QList<IPost *> ) = 0;

    virtual QDateTime lastUpdated() const = 0;
    virtual void setLastUpdated(QDateTime ) = 0;

    virtual QString status() const = 0;
    virtual void setStatus(QString ) = 0;
    virtual QString aboutMe() const = 0;
    virtual void setAboutMe(QString ) = 0;
    virtual QString activities() const = 0;
    virtual void setActivities(QString ) = 0;
    virtual QString interests() const = 0;
    virtual void setInterests(QString ) = 0;
    virtual QString politicalViews() const = 0;
    virtual void setPoliticalViews(QString ) = 0;
    virtual QString religion() const = 0;
    virtual void setReligion(QString ) = 0;

    virtual QString favoriteBooks() const = 0;
    virtual void setFavoriteBooks(QString ) = 0;
    virtual QString favoriteMovies() const = 0;
    virtual void setFavoriteMovies(QString ) = 0;
    virtual QString favoriteMusic() const = 0;
    virtual void setFavoriteMusic(QString ) = 0;
    virtual QString favoriteQuotes() const = 0;
    virtual void setFavoriteQuotes(QString ) = 0;
    virtual QString favoriteTvShows() const = 0;
    virtual void setFavoriteTvShows(QString ) = 0;

    virtual QImage picture() const = 0;
    virtual void setPicture(QImage ) = 0;
    virtual ActivityLookedFor lookingFor() const = 0;
    virtual void setLookingFor(ActivityLookedFor ) = 0;
    virtual IPerson *inRelationshipWith() const = 0;
    virtual void setInRelationshipWith(IPerson *) = 0;
    virtual RelationshipType relationshipType() const = 0;
    virtual void setRelationshipType(RelationshipType ) = 0;
    virtual QString location() const = 0;
    virtual void setLocation(QString ) = 0;
};

#endif // IPERSON_H
