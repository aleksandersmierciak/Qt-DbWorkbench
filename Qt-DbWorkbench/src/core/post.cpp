#include "post.h"

Post::Post(QDateTime creationTime, QString title, QString content)
{
    this->m_creationTime = creationTime;
    this->m_title = title;
    this->m_content = content;
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
    // TODO: also compare sender field
    // TODO: also compare recipients collection field
    return m_creationTime == other.creationTime()
        && m_title == other.title()
            && m_content == other.content();
}
