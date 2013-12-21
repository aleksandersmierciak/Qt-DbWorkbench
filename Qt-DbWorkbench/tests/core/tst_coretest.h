#ifndef TST_CORETEST_H
#define TST_CORETEST_H

#include <QString>
#include <QtTest>

class CoreTest : public QObject
{
    Q_OBJECT

public:
    CoreTest();

private Q_SLOTS:
    void initTestCase();
    void cleanupTestCase();
    void testCase1();
};

#endif // TST_CORETEST_H
