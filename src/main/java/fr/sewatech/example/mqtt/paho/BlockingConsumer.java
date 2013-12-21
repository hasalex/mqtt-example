package fr.sewatech.example.mqtt.paho;

import fr.sewatech.example.mqtt.common.Settings;
import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Alexis Hassler
 */
public class BlockingConsumer {

    public static void main(String[] args) throws MqttException {
        final MqttClient client = new MqttClient(Settings.SERVER_URL, "BlockingConsumer");
        client.connect();
        client.setCallback(new SubscriptionCallback() {
            @Override
            protected void postMessageArrived() throws MqttException {
                client.disconnect();
                client.close();
            }
        });
        client.subscribe(Settings.TOPIC_NAME);
    }

}
