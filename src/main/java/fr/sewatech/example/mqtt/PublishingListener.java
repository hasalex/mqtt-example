package fr.sewatech.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author Alexis Hassler
 */
public class PublishingListener implements IMqttActionListener {
    @Override
    public void onSuccess(IMqttToken token) {
        System.out.println("Publishing succeeded");
    }

    @Override
    public void onFailure(IMqttToken token, Throwable throwable) {
        System.out.println("Publishing failed");
    }
}
