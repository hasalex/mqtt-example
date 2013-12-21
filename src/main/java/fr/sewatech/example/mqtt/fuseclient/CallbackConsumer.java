package fr.sewatech.example.mqtt.fuseclient;

import fr.sewatech.example.mqtt.common.Settings;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

/**
 * @author Alexis Hassler
 */
public class CallbackConsumer {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost(Settings.SERVER_URL);
        mqtt.setClientId("CallbackConsumer");

        final CallbackConnection connection = mqtt.callbackConnection();

        connection.listener(new Listener() {
            @Override
            public void onConnected() {
                System.out.println("OK, connected");
            }

            @Override
            public void onDisconnected() {
                System.out.println("Disconnected");
            }

            @Override
            public void onPublish(UTF8Buffer topic, Buffer message, Runnable ack) {
                System.out.println("Hey, message arrived on topic " + topic.toString() + " : " + message.utf8());
                ack.run();
            }

            @Override
            public void onFailure(Throwable value) {
                System.out.println("onFailure");
            }
        });


        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                System.out.println("Connection succeeded");
                connection.subscribe(new Topic[]{new Topic(Settings.TOPIC_NAME, QoS.AT_MOST_ONCE)},
                        new Callback<byte[]>() {
                            @Override
                            public void onSuccess(byte[] payload) {
                                System.out.println("Mmmh, subscription succeeded");
                            }

                            @Override
                            public void onFailure(Throwable value) {
                                System.out.println("Pfff, subscription failed");
                            }
                        });
            }

            @Override
            public void onFailure(Throwable value) {
                System.out.println("Tooh, connection failed");
            }
        });

        Thread.sleep(100000);
        close(connection);
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
