#include <stdexcept>

#include "dbconnectorbase.h"

bool DbConnectorBase::isConnected() const
{
    return m_isConnected;
}

void DbConnectorBase::connect(const DbConnectionData &data)
{
    if (!m_isConnected)
    {
        m_isConnected = true;
        connectImpl(data);
    }
    else
    {
        throw std::logic_error("this connector object is already connected");
    }
}

void DbConnectorBase::executeQuery() const
{
    if (m_isConnected)
    {
        executeQueryImpl();
    }
    else
    {
        throw std::logic_error("this connector object is not connected");
    }
}

void DbConnectorBase::disconnect()
{
    if (m_isConnected)
    {
        disconnectImpl();
        m_isConnected = false;
    }
    else
    {
        throw std::logic_error("this connector object is not connected");
    }
}
