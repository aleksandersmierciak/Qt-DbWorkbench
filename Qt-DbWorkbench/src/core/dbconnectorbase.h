#ifndef DBCONNECTORBASE_H
#define DBCONNECTORBASE_H

#include "dbconnectiondata.h"

class DbConnectorBase
{
public:
    DbConnectorBase() = default;
    virtual ~DbConnectorBase() {}

    DbConnectorBase(const DbConnectorBase &) = delete;
    DbConnectorBase &operator =(const DbConnectorBase &) = delete;

    bool isConnected() const;

    void connect(const DbConnectionData &data);
    void executeQuery() const;
    void disconnect();

private:
    virtual void connectImpl(const DbConnectionData &data) const = 0;
    virtual void executeQueryImpl() const = 0;
    virtual void disconnectImpl() const = 0;

    bool m_isConnected;
};

#endif // DBCONNECTORBASE_H
