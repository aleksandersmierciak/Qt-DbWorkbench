TARGET = core
TEMPLATE = lib
CONFIG += staticlib

QT += network

DEFINES += QT_NO_CAST_FROM_ASCII \
    QT_NO_CAST_TO_ASCII

CONFIG += c++11

QMAKE_CXXFLAGS += -pthread -lmariadb -lmongoclient -lboost_thread-mt -lboost_filesystem -lboost_program_options -lboost_system

HEADERS += \
    iequatable.h \
    ipost.h \
    post.h \
    iperson.h \
    person.h \
    activitylookedfor.h \
    gender.h \
    relationshiptype.h \
    dbconnectiondata.h \
    dbconnectorbase.h

SOURCES += \
    post.cpp \
    person.cpp \
    activitylookedfor.cpp \
    gender.cpp \
    relationshiptype.cpp \
    dbconnectiondata.cpp \
    dbconnectorbase.cpp
