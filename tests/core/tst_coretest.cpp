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

QTEST_APPLESS_MAIN(CoreTest)

#include "tst_coretest.moc"
