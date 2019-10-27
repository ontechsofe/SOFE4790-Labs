import java.io.*;
// import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String[] arg) throws Exception {
		ServerSocket server;
		BufferedReader in;
		PrintStream w;
		BufferedReader r;

		try {
			server = new ServerSocket(7896);
			Socket client = server.accept();
			System.out.println("Connection Received");
			r = new BufferedReader(
                new InputStreamReader(client.getInputStream()));			w = new PrintStream(client.getOutputStream());
			in = new BufferedReader(
                new InputStreamReader(System.in));

			int userInputNum;
			int summation;
			double average;
			String x;
            while ((x = r.readLine()) != null) {
				userInputNum = Integer.parseInt(x);
				summation = 0;
				for (int i = 0; i < userInputNum; i++) {
					summation += i;
				}
				average = summation / userInputNum;
                w.println(average);
            }
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}
}
