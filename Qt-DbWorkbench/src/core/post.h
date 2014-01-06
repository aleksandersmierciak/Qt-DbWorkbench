#ifndef POST_H
#define POST_H

#include "ipost.h"
#include "iperson.h"

class Post : virtual public IPost
{
public:
    Post(IPerson *sender, QList<IPerson *> receivers, QDateTime creationTime, QString title, QString content);
    virtual ~Post() {}

    IPerson *sender() const override;
    QList<IPerson *> receivers() const override;
    QDateTime creationTime() const override;
    QString title() const override;
    QString content() const override;

private:
    IPerson *m_sender;
    QList<IPerson *> m_receivers;
    QDateTime m_creationTime;
    QString m_title;
    QString m_content;

    virtual bool comparisonImpl(const IPost &other) const override;
    template <class T>
    static bool pointerComparisonImpl(T first, T second);
};

#endif // POST_H
