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

            int shift;
            String characters;
            String instruction;
            System.out.print("Please input the shift value (Int): ");
            while ((shift = Integer.parseInt(in.readLine())) != -1) {
                System.out.print("Please input the characters: ");
                characters = in.readLine();
                instruction = shift + "|" + characters;
                w.println(instruction);
                System.out.println("Server Sent Encoded Text: " + r.readLine());
                System.out.print("Please input the shift value (Int): ");
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
