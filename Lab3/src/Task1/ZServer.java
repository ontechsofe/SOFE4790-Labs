import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.util.ArrayList;
import java.util.Arrays;

public class ZServer {

    private static ArrayList<Integer> findPrimes(int num) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1; i < num; i++) {
            boolean found = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);
                int num = Integer.parseInt(new String(reply, ZMQ.CHARSET));
                System.out.println("Received " + ": [" + num + "]");

                ArrayList<Integer> primes = findPrimes(num);


                String response = Arrays.toString(primes.toArray()) + " Prime Nums";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }

            socket.close();
        }
    }
}