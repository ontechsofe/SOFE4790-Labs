package Task3;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Scanner;

public class ZClient {

    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");
            Scanner in = new Scanner(System.in);
            while (true) {
                try {
                    String s = in.nextLine();
                    if (s.equalsIgnoreCase("EXIT")) {
                        break;
                    }
//                    int num = Integer.parseInt(s);
                    System.out.println("Sending: " + s);
                    socket.send(s.getBytes(ZMQ.CHARSET), 0);

                    byte[] reply = socket.recv(0);
                    System.out.println("Received " + new String(reply, ZMQ.CHARSET));
                } catch(Exception e) {

                }
            }
//            socket.close();
        }
    }
}