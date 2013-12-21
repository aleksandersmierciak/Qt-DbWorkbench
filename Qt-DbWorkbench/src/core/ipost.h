#ifndef IPOST_H
#define IPOST_H

#include <QDateTime>
#include <QString>

#include "iequatable.h"

class IPost : virtual public IEquatable<IPost>
{
public:
    IPost() = default;
    virtual ~IPost() {}

    IPost(const IPost &) = delete;
    IPost &operator =(const IPost &) = delete;

    // TODO: create a sender property
    // TODO: create a recipients collection property

    virtual QDateTime creationTime() const = 0;
    virtual QString title() const = 0;
    virtual QString content() const = 0;
};

#endif // IPOST_H
