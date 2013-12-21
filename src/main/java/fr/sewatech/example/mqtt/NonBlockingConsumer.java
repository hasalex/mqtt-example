package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import static fr.sewatech.example.mqtt.Settings.*;

/**
 * @author Alexis Hassler
 */
public class NonBlockingConsumer {
    public static void main(String[] args) throws MqttException {
        final MqttAsyncClient client = new MqttAsyncClient(SERVER_URL, "NonBlocking2");
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
                    System.out.println("Yeah, " + client.getClientId() + " connected");
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

}
