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

			String instruction;
			int shift;
			char[] code;
			String encoded;
            while ((instruction = r.readLine()) != null) {
				shift = Integer.parseInt(instruction.split("|")[0]);
				code = instruction.split("\\|")[1].toCharArray();
				for (int i = 0; i < code.length; i++) {
					code[i] = (char) (code[i] + shift);
				}
				encoded = new String(code);
				System.out.println(encoded);
                w.println(encoded);
            }
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}
}
