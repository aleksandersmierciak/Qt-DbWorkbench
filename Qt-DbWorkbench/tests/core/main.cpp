#include <QtTest/QtTest>

#include "tst_coretest.cpp"
#include "tst_posttest.cpp"

int main()
{
    CoreTest coreTest;
    QTest::qExec(&coreTest);

    PostTest postTest;
    QTest::qExec(&postTest);

    return 0;
}
