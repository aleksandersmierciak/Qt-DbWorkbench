#ifndef POST_H
#define POST_H

#include "ipost.h"

class Post : virtual public IPost
{
public:
    Post(QDateTime creationTime, QString title, QString content);
    virtual ~Post() {}

    QDateTime creationTime() const override;
    QString title() const override;
    QString content() const override;

private:
    // TODO: create a sender field
    // TODO: create a recipients collection field
    QDateTime m_creationTime;
    QString m_title;
    QString m_content;

    virtual bool comparisonImpl(const IPost &other) const override;
};

#endif // POST_H
