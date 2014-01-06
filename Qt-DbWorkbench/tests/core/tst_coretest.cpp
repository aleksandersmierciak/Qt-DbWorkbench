#include <QString>
#include <QtTest>

#include "tst_coretest.h"

CoreTest::CoreTest()
{
}

void CoreTest::initTestCase()
{
}

void CoreTest::cleanupTestCase()
{
}

void CoreTest::testCase1()
{
    QVERIFY2(true, "Failure");
}
