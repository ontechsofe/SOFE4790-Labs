package Task4;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.ArrayList;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.Random;

public class ZServer {
    public static void main(String[] args) throws InterruptedException {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket server = context.socket(SocketType.PUB);
        server.bind("tcp://*:5555");
        Thread.sleep(1000);
        System.out.println("Server Running...");
        int postalCode;
        for (postalCode = 0; postalCode < 10; postalCode++) {
            server.send(String.format("%d", postalCode), ZMQ.SNDMORE);
        }
        Random r = new Random(System.currentTimeMillis());
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(500);
            String data = String.format("%d", r.nextInt(10000));
            String dest = String.format("%d", r.nextInt(10));
            server.send(dest, ZMQ.SNDMORE);
            server.send(data);
            System.out.println("Code: [" + dest + "]: Population: " + data);
        }
        server.close();
        context.term();
    }
}