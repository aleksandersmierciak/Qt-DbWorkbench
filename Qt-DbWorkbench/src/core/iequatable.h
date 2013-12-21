#ifndef IEQUATABLE_H
#define IEQUATABLE_H

template <class T>
class IEquatable
{
public:
    ~IEquatable() {}

    bool operator ==(const T &other) const
    {
        return this->comparisonImpl(other);
    }
    bool operator !=(const T &other) const
    {
        return !this->comparisonImpl(other);
    }

private:
    virtual bool comparisonImpl(const T &other) const = 0;
};

#endif // IEQUATABLE_H
