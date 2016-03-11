package http;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class SimpleWebBrowser {



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String host = "www.heitel.name";
			Socket socket = new Socket(host, 80); 
			 
			InputStream is = socket.getInputStream();			
			OutputStream os = socket.getOutputStream();
			os.write(("GET / HTTP/1.1\r\nHost:"+host+"\r\n\r\n").getBytes());
			os.flush();
			
			int input;
			while ((input = is.read())!=-1) {
				System.out.print( (char)input);
			}
			is.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
