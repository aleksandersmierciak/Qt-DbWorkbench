#ifndef PERSON_H
#define PERSON_H

#include "iperson.h"

class Person : public IPerson
{
public:
    Person();

    QString firstName() const override;
    void setFirstName(QString ) override;
    QString lastName() const override;
    void setLastName(QString ) override;
    Gender gender() const override;
    void setGender(Gender ) override;
    QDateTime birthday() const override;
    void setBirthday(QDateTime ) override;

    QList<IPost *> posts() const override;
    void setPosts(QList<IPost *> ) override;

    QDateTime lastUpdated() const override;
    void setLastUpdated(QDateTime ) override;

    QString status() const override;
    void setStatus(QString ) override;
    QString aboutMe() const override;
    void setAboutMe(QString ) override;
    QString activities() const override;
    void setActivities(QString ) override;
    QString interests() const override;
    void setInterests(QString ) override;
    QString politicalViews() const override;
    void setPoliticalViews(QString ) override;
    QString religion() const override;
    void setReligion(QString ) override;

    QString favoriteBooks() const override;
    void setFavoriteBooks(QString ) override;
    QString favoriteMovies() const override;
    void setFavoriteMovies(QString ) override;
    QString favoriteMusic() const override;
    void setFavoriteMusic(QString ) override;
    QString favoriteQuotes() const override;
    void setFavoriteQuotes(QString ) override;
    QString favoriteTvShows() const override;
    void setFavoriteTvShows(QString ) override;

    QImage picture() const override;
    void setPicture(QImage ) override;
    ActivityLookedFor lookingFor() const override;
    void setLookingFor(ActivityLookedFor ) override;
    IPerson *inRelationshipWith() const override;
    void setInRelationshipWith(IPerson *) override;
     RelationshipType relationshipType() const override;
     void setRelationshipType(RelationshipType ) override;
    QString location() const override;
    void setLocation(QString ) override;

private:
    QString m_firstName;
    QString m_lastName;
    Gender m_gender;
    QDateTime m_birthday;

    QList<IPost *> m_posts;

    QDateTime m_lastUpdated;

    QString m_status;
    QString m_aboutMe;
    QString m_activities;
    QString m_interests;
    QString m_politicalViews;
    QString m_religion;

    QString m_favoriteBooks;
    QString m_favoriteMovies;
    QString m_favoriteMusic;
    QString m_favoriteQuotes;
    QString m_favoriteTvShows;

    QImage m_picture;
    ActivityLookedFor m_lookingFor;
    IPerson *m_inRelationshipWith;
    RelationshipType m_relationshipType;
    QString m_location;

    virtual bool comparisonImpl(const IPerson &other) const override;
};

#endif // PERSON_H
