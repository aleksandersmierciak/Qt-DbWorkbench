QT       += testlib

QT       -= gui

TARGET = tst_coretest
CONFIG   += console
CONFIG   -= app_bundle

TEMPLATE = app

DEFINES += QT_NO_CAST_FROM_ASCII \
	QT_NO_CAST_TO_ASCII

CONFIG += c++11

SOURCES += main.cpp \
    tst_coretest.cpp \
    tst_posttest.cpp

DEFINES += SRCDIR=\\\"$$PWD/\\\"

HEADERS += \
    tst_coretest.h \
    tst_posttest.h

win32:CONFIG(release, debug|release): LIBS += -L$$OUT_PWD/../../src/core/release/ -lcore
else:win32:CONFIG(debug, debug|release): LIBS += -L$$OUT_PWD/../../src/core/debug/ -lcore
else:unix: LIBS += -L$$OUT_PWD/../../src/core/ -lcore

INCLUDEPATH += $$PWD/../../src/core
DEPENDPATH += $$PWD/../../src/core

win32-g++:CONFIG(release, debug|release): PRE_TARGETDEPS += $$OUT_PWD/../../src/core/release/libcore.a
else:win32-g++:CONFIG(debug, debug|release): PRE_TARGETDEPS += $$OUT_PWD/../../src/core/debug/libcore.a
else:win32:!win32-g++:CONFIG(release, debug|release): PRE_TARGETDEPS += $$OUT_PWD/../../src/core/release/core.lib
else:win32:!win32-g++:CONFIG(debug, debug|release): PRE_TARGETDEPS += $$OUT_PWD/../../src/core/debug/core.lib
else:unix: PRE_TARGETDEPS += $$OUT_PWD/../../src/core/libcore.a
