package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.SERVER_URL;
import static fr.sewatech.example.mqtt.Settings.TOPIC_NAME;

/**
 * @author Alexis Hassler
 */
public class Blocking {

    public static void main(String[] args) throws MqttException {
        publish();
        receive();
    }

    private static void receive() throws MqttException {
        final MqttClient client = new MqttClient(SERVER_URL, "C2");
        client.connect();
        client.setCallback(new SubscriptionCallback() {
            @Override
            protected void postMessageArrived() throws MqttException {
                client.disconnect();
                client.close();
            }
        });
        client.subscribe(TOPIC_NAME);
    }

    private static void publish() throws MqttException {
        MqttClient client = new MqttClient(SERVER_URL, "C1");
        client.connect();

        MqttTopic topic = client.getTopic(TOPIC_NAME);
        MqttMessage message = new MqttMessage();
        message.setPayload("Hellox".getBytes());
        message.setRetained(true);
        MqttDeliveryToken token = topic.publish(message);
        token.setActionCallback(new PublishingListener());

        client.disconnect();
        client.close();
    }
}
