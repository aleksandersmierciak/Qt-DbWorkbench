import QtQuick 2.0
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.0

GridLayout {
    columns: 2

    signal verifyConnection (string ip, string port, string database, string login, string password)

    Label {
        text: qsTr("IP address:")
    }

    TextField {
        id: ipAddressText

        implicitWidth: 120
        inputMask: '000.000.000.000;_'
        font.family: 'Monospace'
    }

    Label {
        text: qsTr("Port number:")
    }

    TextField {
        id: portNumberText

        Layout.alignment: Qt.AlignRight

        validator: IntValidator {bottom: 1; top: 65536;}
    }

    Label {
        text: qsTr("Database instance:")
    }

    TextField {
        id: dbInstanceText

        Layout.alignment: Qt.AlignRight
    }

    Label {
        text: qsTr("User login:")
    }

    TextField {
        id: userLoginText

        Layout.alignment: Qt.AlignRight
    }

    Label {
        text: qsTr("User password:")
    }

    TextField {
        id: userPasswordText

        Layout.alignment: Qt.AlignRight

        echoMode: TextInput.Password
        inputMethodHints: Qt.ImhHiddenText | Qt.ImhSensitiveData
    }

    Button {
        text: qsTr("Verify connection")

        Layout.columnSpan: 2
        Layout.alignment: Qt.AlignCenter

        onClicked: parent.verifyConnection(ipAddressText.text, portNumberText.text, dbInstanceText.text, userLoginText.text, userPasswordText.text)
    }
}
