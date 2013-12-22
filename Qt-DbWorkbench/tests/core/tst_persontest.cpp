#include "tst_persontest.h"
#include "person.h"

PersonTest::PersonTest()
{
}

void PersonTest::testEquality()
{
    IPerson *first = new Person();
    IPerson *second = new Person();
    QCOMPARE(*first == *second, true);
}

void PersonTest::testInequality()
{
    QFETCH(QString, firstName1);
    QFETCH(QString, secondName1);

    QFETCH(QString, firstName2);
    QFETCH(QString, secondName2);

    IPerson *first = new Person();
    first->setFirstName(firstName1);
    first->setLastName(secondName1);

    IPerson *second = new Person();
    second->setFirstName(firstName2);
    second->setLastName(secondName2);

    QCOMPARE(*first == *second, false);
}

void PersonTest::testInequality_data()
{
    QTest::addColumn<QString>("firstName1");
    QTest::addColumn<QString>("secondName1");
    QTest::addColumn<QString>("firstName2");
    QTest::addColumn<QString>("secondName2");

    QTest::newRow("Different names")
            << QStringLiteral("John")
            << QStringLiteral("Doe")
            << QStringLiteral("Anne")
            << QStringLiteral("Smith");
}
