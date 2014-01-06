#ifndef TST_POSTTEST_H
#define TST_POSTTEST_H

#include <QtTest>

class PostTest : public QObject
{
    Q_OBJECT

public:
    PostTest();

private Q_SLOTS:
    void testEquality();
    void testInequality();
    void testInequality_data();
};

#endif // TST_POSTTEST_H
