import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class QotdClient {
	public static void main(String[] args) {
		String server = "localhost";// server name
		int port = 17; // qotd port
		try {
			Socket socket = new Socket(server, port);

			InputStream is = socket.getInputStream();
			int data;

			while ((data = is.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}