package fr.sewatech.example.mqtt.fuseclient;

import fr.sewatech.example.mqtt.common.Settings;
import org.fusesource.mqtt.client.*;

/**
 * @author Alexis Hassler
 */
public class BlockingConsumer {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost(Settings.SERVER_URL);
        mqtt.setClientId("BlockingConsumer");
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        connection.subscribe(new Topic[]{new Topic(Settings.TOPIC_NAME, QoS.AT_MOST_ONCE)});

        Message message = connection.receive();
        System.out.println("Hey, message arrived on topic " + message.getTopic() + " : " + new String(message.getPayload(), "UTF-8"));
        message.ack();

        connection.disconnect();
    }

}
