package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.SERVER_URL;

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
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Arggh, connection lost");
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("Hey, message arrived on topic " + s + " : " + new String(mqttMessage.getPayload(), "UTF-8"));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("Oh, delivery complete");
            }
        });

        client.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                try {
                    client.subscribe("sample", 1);
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
        MqttClient client = new MqttClient(SERVER_URL, "C1", null); // null for no client-side persistence
        client.connect();
        MqttTopic topic = client.getTopic("sample");
        MqttMessage message = new MqttMessage();
        message.setPayload("Hello".getBytes());
        message.setRetained(true);
        topic.publish(message);
        System.out.println("Message published from client " + client.getClientId());
        client.disconnect();
        client.close();
    }
}
