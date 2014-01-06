#ifndef MONGODBCONNECTOR_H
#define MONGODBCONNECTOR_H

#include <mongo/client/dbclient.h>
#include "dbconnectorbase.h"

class MongoDbConnector : virtual public DbConnectorBase
{
public:
    MongoDbConnector();
};

#endif // MONGODBCONNECTOR_H
