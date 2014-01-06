#include "dbconnectiondata.h"

DbConnectionData::DbConnectionData()
{
}

QString DbConnectionData::username()
{
    return m_username;
}

void DbConnectionData::setUsername(QString username)
{
    m_username = username;
}

QString DbConnectionData::password()
{
    return m_password;
}

void DbConnectionData::setPassword(QString password)
{
    m_password = password;
}

QHostAddress DbConnectionData::ipAddress()
{
    return m_ipAddress;
}

void DbConnectionData::setIpAddress(QHostAddress ipAddress)
{
    m_ipAddress = ipAddress;
}

PortNumber DbConnectionData::portNumber()
{
    return m_portNumber;
}

void DbConnectionData::setPortNumber(PortNumber portNumber)
{
    m_portNumber = portNumber;
}
