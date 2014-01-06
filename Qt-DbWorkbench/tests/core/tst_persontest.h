#ifndef TST_PERSONTEST_H
#define TST_PERSONTEST_H

#include <QtTest>

class PersonTest : public QObject
{
    Q_OBJECT

public:
    PersonTest();

private Q_SLOTS:
    void testEquality();
    void testInequality();
    void testInequality_data();
};

#endif // TST_PERSONTEST_H
