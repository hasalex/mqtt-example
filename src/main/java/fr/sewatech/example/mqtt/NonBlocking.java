package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.QOS_LEVEL;
import static fr.sewatech.example.mqtt.Settings.SERVER_URL;
import static fr.sewatech.example.mqtt.Settings.TOPIC_NAME;

/**
 * @author Alexis Hassler
 */
public class NonBlocking {
    public static void main(String[] args) throws MqttException {
        publish();
        receive();
    }

    private static void receive() throws MqttException {
        final MqttAsyncClient client = new MqttAsyncClient(SERVER_URL, "C2");
        client.setCallback(new SubscriptionCallback() {
            @Override
            protected void postMessageArrived() throws MqttException {
                client.disconnect();
                client.close();
            }
        });

        client.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                try {
                    client.subscribe(TOPIC_NAME, QOS_LEVEL);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                System.out.println("Sigh, not connected");
            }
        });

    }

    private static void publish() throws MqttException {
        MqttClient client = new MqttClient(SERVER_URL, "C2", null); // null for no client-side persistence
        client.connect();

        MqttTopic topic = client.getTopic(TOPIC_NAME);
        MqttMessage message = new MqttMessage();
        message.setPayload("Helloxx".getBytes());
        message.setRetained(true);
        MqttDeliveryToken token = topic.publish(message);
        token.setActionCallback(new PublishingListener());

        client.disconnect();
        client.close();
    }
}
