import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class EchoClient {
	public static void main(String[] args) {
		String server = "localhost";// server name
		int port = 7; // echo port
		try {
			Socket socket = new Socket(server, port);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			os.write("Hallo Welt!".getBytes());
			os.flush();
			
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