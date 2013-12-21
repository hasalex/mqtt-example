package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import static fr.sewatech.example.mqtt.Settings.SERVER_URL;
import static fr.sewatech.example.mqtt.Settings.TOPIC_NAME;

/**
 * @author Alexis Hassler
 */
public class BlockingPublisher {

    public static void main(String[] args) throws MqttException {
        final MqttClient client = new MqttClient(SERVER_URL, "Blocking1");
        client.connect();

        MqttTopic topic = client.getTopic(TOPIC_NAME);
        MqttMessage message = new MqttMessage();
        message.setPayload("Hello".getBytes());
        message.setRetained(true);
        topic.publish(message);
        System.out.println("Publishing succeeded");

        client.disconnect();
        client.close();
    }

}
