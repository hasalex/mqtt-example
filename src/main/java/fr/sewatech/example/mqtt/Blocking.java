package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Alexis Hassler
 */
public class Blocking {
    public static void main(String[] args) throws MqttException {
        publish();
        receive();
    }

    private static void receive() throws MqttException {
        final MqttClient client = new MqttClient("tcp://localhost:1883", "C2");
        client.connect();
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Arggh, connection lost");
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("Hey, message arrived on topic " + s + " : " + new String(mqttMessage.getPayload(), "UTF-8"));
                client.disconnect();
                client.close();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("Oh, delivery complete");
            }
        });
        client.subscribe("sample");

    }

    private static void publish() throws MqttException {
        MqttClient client = new MqttClient("tcp://localhost:1883", "C1");
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
