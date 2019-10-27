package Task4;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.Scanner;

public class ZClient {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket client = context.socket(SocketType.SUB);
        client.connect("tcp://localhost:5555");
        System.out.println("Client Running...");
        Scanner input = new Scanner(System.in);

        System.out.print("What do you want to subscribe to: ");
        int postalCode = input.nextInt();
        String subscription = String.format("%d", postalCode);
        System.out.println("Subscribing to: " + subscription);
        client.subscribe(subscription.getBytes(ZMQ.CHARSET));
        System.out.println("SUB SUCCESS");
        while (true) {
            String topic = client.recvStr();
            if (topic == null) {
                break;
            }
            String data = client.recvStr();
            assert (topic.equals(subscription));
            System.out.println("Received Code: [" + topic + "] Population: " + data);
        }
        client.close();
        context.term();
    }
}