package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Alexis Hassler
 */
public abstract class SubscriptionCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Arggh, connection lost");
        System.exit(0);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Hey, message arrived on topic " + topic + " : " + new String(mqttMessage.getPayload(), "UTF-8"));
        postMessageArrived();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Oh, delivery complete");
    }

    protected abstract void postMessageArrived() throws MqttException;
}