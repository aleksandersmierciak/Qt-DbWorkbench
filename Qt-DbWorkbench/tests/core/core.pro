QT       += testlib

QT       -= gui

TARGET = tst_coretest
CONFIG   += console
CONFIG   -= app_bundle

TEMPLATE = app


SOURCES += tst_coretest.cpp
DEFINES += SRCDIR=\\\"$$PWD/\\\"
