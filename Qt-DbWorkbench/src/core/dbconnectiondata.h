#ifndef DBCONNECTIONDATA_H
#define DBCONNECTIONDATA_H

#include <QHostAddress>
#include <QString>

typedef unsigned short int PortNumber;

class DbConnectionData
{
public:
    DbConnectionData();

    DbConnectionData(const DbConnectionData &) = delete;
    DbConnectionData &operator =(const DbConnectionData &) = delete;

    virtual QString username();
    void setUsername(QString username);
    QString password();
    void setPassword(QString password);
    QHostAddress ipAddress();
    void setIpAddress(QHostAddress ipAddress);
    PortNumber portNumber();
    void setPortNumber(PortNumber portNumber);

private:
    QString m_username;
    QString m_password;
    QString m_dbName;
    QHostAddress m_ipAddress;
    PortNumber m_portNumber;
};

#endif // DBCONNECTIONDATA_H
