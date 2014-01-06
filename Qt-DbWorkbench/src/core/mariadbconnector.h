#ifndef MARIADBCONNECTOR_H
#define MARIADBCONNECTOR_H

// TODO: investigate MariaDB driver errors
//       use MySQL driver for the time being
//#include <mariadbdriver/global.h>
#include <cppconn/connection.h>

#include "dbconnectorbase.h"

class MariaDbConnector : virtual public DbConnectorBase
{
public:
    MariaDbConnector();
};

#endif // MARIADBCONNECTOR_H
