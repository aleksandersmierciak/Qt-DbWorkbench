import QtQuick 2.0
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.0
import QtQuick.Window 2.0

Window {
    width: 300
    height: 500

    TabView {
        anchors.fill: parent

        Tab {
            title: qsTr("Actions")

            ActionsPane {
            }
        }

        Tab {
            title: qsTr("Connection settings")

            ConnectionSettingsPane {
            }
        }
    }
}
