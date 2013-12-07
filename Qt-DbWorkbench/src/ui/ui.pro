# Add more folders to ship with the application, here
folder_01.source = qml/ui
folder_01.target = qml
DEPLOYMENTFOLDERS = folder_01

DEFINES += QT_NO_CAST_FROM_ASCII \
    QT_NO_CAST_TO_ASCII

CONFIG += c++11

SOURCES += main.cpp

# Please do not modify the following two lines. Required for deployment.
include(qtquick2applicationviewer/qtquick2applicationviewer.pri)
qtcAddDeployment()
