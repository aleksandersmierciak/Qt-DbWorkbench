#include <QtTest/QtTest>

#include "tst_coretest.cpp"

int main()
{
    CoreTest coreTest;
    QTest::qExec(&coreTest);

    return 0;
}
