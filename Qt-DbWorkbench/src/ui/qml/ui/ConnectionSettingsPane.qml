import QtQuick 2.0
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.0

ColumnLayout {
    signal verifyConnection(string type, string ip, string port, string database, string login, string password)
    GroupBox {
        title: qsTr("MariaDB connection")

        Layout.alignment: Qt.AlignCenter

        CredentialsInput {
            onVerifyConnection: {
                console.log("MariaDB connection verification requested")
                console.log(ip + ":" + port + ", " + database + " " + login + " " + password)
            }
        }
    }

    GroupBox {
        title: qsTr("MongoDB connection")

        Layout.alignment: Qt.AlignCenter

        CredentialsInput {
            onVerifyConnection: {
                console.log("MongoDB connection verification requested")
                console.log(ip + ":" + port + ", " + database + " " + login + " " + password)
            }
        }
    }
}
