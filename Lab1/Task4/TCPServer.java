import java.net.*;
import java.util.TooManyListenersException;
import java.io.*;

public class TCPServer {
	private final int MAXCLIENTS = 2;
	private int connected = 0;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		TCPServer es = new TCPServer();
		es.run(portNumber);
	}

	public void run(int portNumber) {
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			while (true) {
				if (this.connected <= this.MAXCLIENTS) {
					Socket client = serverSocket.accept();
					System.out.println("Connection Received!");
					this.connected += 1;
					Connection cc = new Connection(client);
				} else {
					throw new Exception("Too many Clients (Maximum 2)");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

}

class Connection extends Thread {
	Socket client;
	PrintWriter out;
	BufferedReader in;

	public Connection(Socket s) { // constructor
		client = s;

		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			try {
				client.close();
			} catch (IOException ex) {
				System.out.println("Error while getting socket streams.." + ex);
			}
			return;
		}
		this.start(); // Thread starts here...this start() will call run()
	}

	public void run() {
		try {
			int userInputNum, summation;
			double average;
			String x;
			while ((x = in.readLine()) != null) {
				userInputNum = Integer.parseInt(x);
				summation = 0;
				for (int i = 0; i < userInputNum; i++) {
					summation += i;
				}
				average = summation / userInputNum;
				out.println(average);
			}
			client.close();
		} catch (IOException e) {
			System.out.println("Exception caught...");
			System.out.println(e.getMessage());
		}
	}
}