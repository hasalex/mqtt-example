package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.*;

/**
 * @author Alexis Hassler
 */
public class NonBlockingPublisher {
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient(SERVER_URL, "NonBlocking1", null); // null for no client-side persistence
        client.connect();

        MqttTopic topic = client.getTopic(TOPIC_NAME);
        MqttMessage message = new MqttMessage();
        message.setPayload("Helloxx".getBytes());
        message.setRetained(true);
        topic.publish(message);
        System.out.println("Publishing succeeded");

        client.disconnect();
        client.close();
    }

}
