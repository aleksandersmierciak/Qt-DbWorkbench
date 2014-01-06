#include "post.h"

Post::Post(IPerson *sender, QList<IPerson *> receivers, QDateTime creationTime, QString title, QString content)
{
    this->m_sender = sender;
    this->m_receivers = receivers;
    this->m_creationTime = creationTime;
    this->m_title = title;
    this->m_content = content;
}

IPerson *Post::sender() const
{
    return this->m_sender;
}

QList<IPerson *> Post::receivers() const
{
    return this->m_receivers;
}

QDateTime Post::creationTime() const
{
    return this->m_creationTime;
}

QString Post::content() const
{
    return this->m_content;
}

QString Post::title() const
{
    return this->m_title;
}

bool Post::comparisonImpl(const IPost &other) const
{
    return pointerComparisonImpl(m_sender, other.sender())
        && m_receivers == other.receivers()
        && m_creationTime == other.creationTime()
        && m_title == other.title()
        && m_content == other.content();
}

template <class T>
bool Post::pointerComparisonImpl(T first, T second)
{
    return (first == second
         || (first != nullptr && second != nullptr)
         || *first == *second);
}
