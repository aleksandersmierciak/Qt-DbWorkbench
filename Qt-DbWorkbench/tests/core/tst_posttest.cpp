#include <QString>
#include <QtTest>

#include "tst_posttest.h"
#include "post.h"

PostTest::PostTest()
{
}

void PostTest::testEquality()
{
    QDateTime now = QDateTime::currentDateTime();
    QString title = QStringLiteral("Title");
    QString content = QStringLiteral("Lorem ipsum dolor sit amet...");

    IPost *first = new Post(nullptr, QList<IPerson *>(), now, title, content);
    IPost *second = new Post(nullptr, QList<IPerson *>(), now, title, content);
    QCOMPARE(*first == *second, true);
}

void PostTest::testInequality()
{
    QFETCH(QDateTime, creationTime1);
    QFETCH(QString, title1);
    QFETCH(QString, content1);
    QFETCH(QDateTime, creationTime2);
    QFETCH(QString, title2);
    QFETCH(QString, content2);

    IPost *first = new Post(nullptr, QList<IPerson *>(), creationTime1, title1, content1);
    IPost *second = new Post(nullptr, QList<IPerson *>(), creationTime2, title2, content2);
    QCOMPARE(first == second, false);
}

void PostTest::testInequality_data()
{
    QTest::addColumn<QDateTime>("creationTime1");
    QTest::addColumn<QString>("title1");
    QTest::addColumn<QString>("content1");
    QTest::addColumn<QDateTime>("creationTime2");
    QTest::addColumn<QString>("title2");
    QTest::addColumn<QString>("content2");

    QTest::newRow("Different times")
            << QDateTime::currentDateTime()
            << QStringLiteral("Title")
            << QStringLiteral("Lorem ipsum dolor sit amet...")
            << QDateTime::currentDateTime()
            << QStringLiteral("Title")
            << QStringLiteral("Lorem ipsum dolor sit amet...");

    QDateTime now = QDateTime::currentDateTime();

    QTest::newRow("Different titles")
            << now
            << QStringLiteral("Title1")
            << QStringLiteral("Lorem ipsum dolor sit amet...")
            << now
            << QStringLiteral("Title2")
            << QStringLiteral("Lorem ipsum dolor sit amet...");

    QTest::newRow("Different contents")
            << now
            << QStringLiteral("Title")
            << QStringLiteral("Lorem ipsum dolor sit amet...1")
            << now
            << QStringLiteral("Title")
            << QStringLiteral("Lorem ipsum dolor sit amet...2");
}
