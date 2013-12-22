#ifndef IPOST_H
#define IPOST_H

#include <QDateTime>
#include <QList>
#include <QString>

#include "iequatable.h"
#include "iperson.h"

class IPerson;

class IPost : virtual public IEquatable<IPost>
{
public:
    IPost() = default;
    virtual ~IPost() {}

    IPost(const IPost &) = delete;
    IPost &operator =(const IPost &) = delete;

    virtual IPerson *sender() const = 0;
    virtual QList<IPerson *> receivers() const = 0;
    virtual QDateTime creationTime() const = 0;
    virtual QString title() const = 0;
    virtual QString content() const = 0;
};

#endif // IPOST_H
