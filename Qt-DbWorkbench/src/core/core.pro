TARGET = core
TEMPLATE = lib
CONFIG += staticlib

DEFINES += QT_NO_CAST_FROM_ASCII \
    QT_NO_CAST_TO_ASCII

CONFIG += c++11

HEADERS += \
    iequatable.h \
    ipost.h \
    post.h \
    iperson.h \
    activitylookedfor.h \
    gender.h \
    relationshiptype.h

SOURCES += \
    post.cpp \
    activitylookedfor.cpp \
    gender.cpp \
    relationshiptype.cpp
