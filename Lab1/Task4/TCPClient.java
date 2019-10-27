import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String arg[]) throws Exception {
        try {
            Socket client = new Socket("127.0.0.1", 7896);
            BufferedReader r = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
            PrintStream w = new PrintStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

            int userNum;
            while ((userNum = Integer.parseInt(in.readLine())) != -1) {
                w.println(userNum);
                System.out.println("Server Sent: " + r.readLine());
            }
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }
}
