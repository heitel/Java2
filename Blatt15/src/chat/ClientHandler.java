package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
	private boolean stop = false;
	private ChatServerWin	server;
	private Socket socket;

	public ClientHandler(ChatServerWin server, Socket clientSocket) {
		this.server = server;
		socket = clientSocket;
		start();
	}
	
	public void run() {
		try {
			InputStream		in = socket.getInputStream();
			
			try {
				while (!stop) {
					int len = in.read();
					StringBuffer sb = new StringBuffer();
					
					for (int i = 0; i<len; i++) {
						sb.append((char)in.read());
					}
					
					server.sendMessage(sb.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
		sendMessage("!!! " + this + " kicked!!!");
	}

	public void sendMessage(String message) {
		try {
			System.out.println("sendMessage: " + message);
			OutputStream	out = socket.getOutputStream();
			
			out.write(message.length());
			out.write(message.getBytes());
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
