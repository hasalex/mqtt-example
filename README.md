The Broker
=========

Before you can launch the example, you need a MQTT server.

With the M2M Eclipse sandbox server
-------------

Nothing to start locally, but you'll have to check the value of the Setting.SERVER_URL constant to :

    public static final String SERVER_URL = "tcp://m2m.eclipse.org:1883";

You can see the content of your topic with the HTTP bridge at http://eclipse.mqttbridge.com/sewatech

With a Moquette server
-------------

Run the Moquette server with the script moquette-broker.sh :

    ./src/scripts/moquette-broker.sh

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";


With an ActiveMQ server
-------------

Run the ActiveMQ broker with the script activemq-broker.sh :

    ./src/scripts/activemq-broker.sh

MQTT is activated by defaut in version 5.9, but it looks like messages aren't retained.

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";

With a TomEE server
-------------

Run the TomEE broker with the script tomee-broker.sh :

    ./src/scripts/tomee-broker.sh

Same behaviour than ActiveMQ, as TomEE 1.6 runs ActiveMQ 5.9.

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";

The Client
=========

The repository shows two Java client APIs : [Eclipse Paho][http://www.eclipse.org/paho/] and [Fuse MQTT-client][http://mqtt-client.fusesource.org/].

Eclipse Paho
-----------

In the fr.sewatech.example.mqtt.paho package, with a blocking and a non-blocking examples.

MQTT-client
-----------

In the fr.sewatech.example.mqtt.fuseclient package, with a blocking, a future and a callback examples.

The Future class is not java.util.concurrent.Future, but an internal one.

Eclipse Paho (JavaScript)
-----------

Open the mqtt.html in a browser.

Does not work with Moquette, as it doesn't support websocket (would need an additional Web Server).

Example inspired by http://architects.dzone.com/articles/mqtt-over-websockets
