package fr.sewatech.example.mqtt.paho;

import fr.sewatech.example.mqtt.common.Settings;
import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Alexis Hassler
 */
public class NonBlockingPublisher {
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient(Settings.SERVER_URL, "NonBlockingPublisher", null); // null for no client-side persistence
        client.connect();

        MqttTopic topic = client.getTopic(Settings.TOPIC_NAME);
        MqttMessage message = new MqttMessage();
        message.setPayload("Hello from the Paho non-blocking client".getBytes());
        message.setRetained(true);
        topic.publish(message);
        System.out.println("Publishing succeeded");

        client.disconnect();
        client.close();
    }

}
