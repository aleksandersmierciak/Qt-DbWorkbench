QT += core gui quick qml

DEFINES += QT_NO_CAST_FROM_ASCII \
    QT_NO_CAST_TO_ASCII

CONFIG += c++11

SOURCES += main.cpp

OTHER_FILES += \
	qml/ui/main.qml \
	qml/ui/ActionsPane.qml \
	qml/ui/ConnectionSettingsPane.qml \
	qml/ui/CredentialsInput.qml

RESOURCES += \
    resources.qrc
