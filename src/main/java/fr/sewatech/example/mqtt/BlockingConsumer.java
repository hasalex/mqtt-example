package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.SERVER_URL;
import static fr.sewatech.example.mqtt.Settings.TOPIC_NAME;

/**
 * @author Alexis Hassler
 */
public class BlockingConsumer {

    public static void main(String[] args) throws MqttException {
        final MqttClient client = new MqttClient(SERVER_URL, "Blocking2");
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

}
