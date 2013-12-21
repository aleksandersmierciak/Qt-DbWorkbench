import QtQuick 2.0
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.1
import QtQuick.Window 2.0

Window {
    width: 800
    height: 600

    title: "[First name] [Last name] overview"
    flags: Qt.Dialog
    modality: Qt.WindowModal

    PersonView {
        id: personView

        anchors.fill: parent
    }
}
