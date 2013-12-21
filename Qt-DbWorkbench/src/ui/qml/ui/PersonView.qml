import QtQuick 2.0
import QtQuick.Layouts 1.1
import QtQuick.Controls 1.1

GridLayout {
    property string firstName: "First name"
    property string lastName: "Last name"
    property int gender: 1
    property string birthday: "1990-12-17"

    property string lastUpdated: "2013-12-20"

    property string status: "Status"
    property string aboutMe: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eros nulla, fermentum at metus eget, ornare lobortis neque. Cras at orci quis turpis ultricies eleifend."
    property string activities: "Activities"
    property string interests: "Interests"
    property string politicalViews: "Political views"
    property string religion: "Religion"
    //property list<string> posts

    property string favoriteBooks: "Favourite books"
    property string favoriteMovies: "Favourite movies"
    property string favoriteMusic: "Favourite music"
    property string favoriteQuotes: "Favourite quotes"
    property string favoriteTvShows: "Favourite TV shows"

    property string picture: "Picture"
    property string significantOther: "Significant other"
    property string lookingFor: "Looking for"
    property string inRelationshipWith: "In relationship with"
    property string location: "Location"

    columns: 3

    Label {
        text: firstName + " " + lastName
        font.pointSize: 15

        Layout.columnSpan: 3
        Layout.alignment: Qt.AlignCenter
    }

    Label {
        text: status

        Layout.columnSpan: 3
        Layout.alignment: Qt.AlignCenter
    }

    GroupBox {
        title: qsTr("About me")

        Layout.fillWidth: true
        Layout.fillHeight: true

        TextArea {
            anchors.fill: parent
            text: aboutMe
            readOnly: true
        }
    }

    GroupBox {
        title: qsTr("Activities")

        Layout.fillWidth: true
        Layout.fillHeight: true

        TextArea {
            anchors.fill: parent
            text: activities
            readOnly: true
        }
    }

    GroupBox {
        title: qsTr("Interests")

        Layout.fillWidth: true
        Layout.fillHeight: true

        TextArea {
            anchors.fill: parent
            text: interests
            readOnly: true
        }
    }

    GroupBox {
        title: qsTr("Political views")

        Layout.fillWidth: true
        Layout.fillHeight: true

        TextArea {
            anchors.fill: parent
            text: politicalViews
            readOnly: true
        }
    }

    GroupBox {
        title: qsTr("Religion")

        Layout.fillWidth: true
        Layout.fillHeight: true

        TextArea {
            anchors.fill: parent
            text: religion
            readOnly: true
        }
    }

    GroupBox {
        title: qsTr("Favourites")

        Layout.fillWidth: true
        Layout.fillHeight: true

        ColumnLayout {
            Label {
                text: favoriteBooks
            }

            Label {
                text: favoriteMovies
            }

            Label {
                text: favoriteMusic
            }

            Label {
                text: favoriteQuotes
            }

            Label {
                text: favoriteTvShows
            }
        }
    }
}
