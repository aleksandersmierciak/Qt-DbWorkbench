import QtQuick 2.0
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.0

ColumnLayout {
    anchors { left: parent.left; top: parent.top; right: parent.right }

    spacing: 5

    signal findPersonByName
    signal findPersonByOther
    signal findGroupMembers
    signal findAttenders
    signal showPersonInfo
    signal showEventInfo
    signal executeCustomCommand

    Button {
        text: qsTr("Find person\nby name")

        Layout.alignment: Qt.AlignCenter

        onClicked: findPersonByName()
    }

    Button {
        text: qsTr("Find person\nby other data")

        Layout.alignment: Qt.AlignCenter

        onClicked: findPersonByOther()
    }

    Button {
        text: qsTr("Find members of\na specific group")

        Layout.alignment: Qt.AlignCenter

        onClicked: findGroupMembers()
    }

    Button {
        text: qsTr("Find attenders of\na specific event")

        Layout.alignment: Qt.AlignCenter

        onClicked: findAttenders()
    }

    Button {
        text: qsTr("Show information\nabout a certain person")

        Layout.alignment: Qt.AlignCenter

        onClicked: showPersonInfo()
    }

    Button {
        text: qsTr("Show information\nabout a certain event")

        Layout.alignment: Qt.AlignCenter

        onClicked: showEventInfo()
    }

    Button {
        text: qsTr("Execute custom\nSQL/NoSQL command")

        Layout.alignment: Qt.AlignCenter

        onClicked: executeCustomCommand()
    }
}
