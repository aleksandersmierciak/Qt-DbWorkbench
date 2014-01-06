#include <QtTest/QtTest>

#include "tst_coretest.h"
#include "tst_posttest.h"
#include "tst_persontest.h"

int main()
{
    CoreTest coreTest;
    QTest::qExec(&coreTest);

    PostTest postTest;
    QTest::qExec(&postTest);

    PersonTest personTest;
    QTest::qExec(&personTest);

    return 0;
}
