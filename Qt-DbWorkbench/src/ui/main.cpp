#include <QtGui/QGuiApplication>
#include <QtQml/QQmlEngine>
#include <QtQml/QQmlComponent>
#include <QtQuick/QQuickWindow>
#include <QtCore/QUrl>
#include <QDebug>

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);
    QQmlEngine engine;
    QQmlComponent component(&engine);
    QQuickWindow::setDefaultAlphaBuffer(true);
    component.loadUrl(QUrl::fromEncoded("qrc:/qml/ui/main.qml"));
    if (component.isReady())
    {
        QObject *object = component.create();
        QQuickWindow *window = qobject_cast<QQuickWindow *>(object);
        window->show();
    }
    else
    {
        qWarning() << component.errorString();
    }

    return app.exec();
}
