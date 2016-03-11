package http;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(1234);
			System.out.println("Web Server on port 1234");
			
			while (true) {
				Socket client = server.accept();
				System.out.println("Connect From:" + client.getInetAddress());
				ClientRequest req = new ClientRequest(client);
				req.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
