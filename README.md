Before you can launch the example, you need a MQTT server.

With the M2M Eclipse sandbox server
================

Nothing to start locally, but you'll have to check the value of the Setting.SERVER_URL constant to :

    public static final String SERVER_URL = "tcp://m2m.eclipse.org:1883";

You can see the content of your topic with the HTTP bridge at http://eclipse.mqttbridge.com/sewatech

With a Moquette server
================

Run the Moquette server with the script moquette-broker.sh :

    ./src/scripts/moquette-broker.sh

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";


With an ActiveMQ server
================

Run the ActiveMQ broker with the script activemq-broker.sh :

    ./src/scripts/activemq-broker.sh

MQTT is activated by defaut in version 5.9, but it looks like messages aren't retained.

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";

With a TomEE server
================

Run
TODO

Access locally (Settings) :

    public static final String SERVER_URL = "tcp://localhost:1883";

