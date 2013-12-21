package fr.sewatech.example.mqtt.fuseclient;

import fr.sewatech.example.mqtt.common.Settings;
import org.fusesource.mqtt.client.*;

/**
 * @author Alexis Hassler
 */
public class CallbackPublisher {
    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost(Settings.SERVER_URL);
        mqtt.setClientId("CallbackPublisher");

        final CallbackConnection connection = mqtt.callbackConnection();
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                connection.publish(Settings.TOPIC_NAME, "Hello from the Fuse callback client".getBytes(), QoS.AT_MOST_ONCE, true,
                        new Callback<Void>() {
                            @Override
                            public void onSuccess(Void value) {
                                System.out.println("Hee, publishing succeeded");
                                close(connection);
                            }

                            @Override
                            public void onFailure(Throwable value) {
                                System.out.println("Pfff, publishing failed");
                                close(connection);
                            }
                        });
            }

            @Override
            public void onFailure(Throwable value) {
                System.out.println("Tooh, connection failed");
            }
        });

        Thread.sleep(1000);
    }

    private static void close(CallbackConnection connection) {
        connection.disconnect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                System.out.println("Closing successed");
            }

            @Override
            public void onFailure(Throwable value) {
                System.out.println("Closing failed");
            }
        });
    }

}
