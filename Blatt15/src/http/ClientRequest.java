package http;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientRequest extends Thread {
	private Socket client;

	public ClientRequest(Socket s) {
		client = s;
	}
	
	@Override
	public void run() {
		work();
	}
	
	public void work() {
		try {
			InputStream is = client.getInputStream();
			String line = "";
			int input;
			while ((input = is.read()) != '\n') {
				line += (char) input;
			}

			System.out.println(line);

			String tmp[] = line.split(" ");
			if (tmp[0].equals("GET")) {
				String fileName = tmp[1].substring(1);
				if (fileName.equals("")) {
					fileName = "index.html";
				}
				sendFile(fileName);
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFile(String fileName) throws IOException {
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		OutputStream os = client.getOutputStream();
		os.write("HTTP/1.0 200 OK\r\n".getBytes());
		
		if (fileName.toUpperCase().endsWith(".JPG")) {
			os.write("Content-Type: image/jpeg\r\n\r\n".getBytes());
		}
		if (fileName.toUpperCase().endsWith(".HTML")) {
			os.write("Content-Type: text/html\r\n\r\n".getBytes());
		}
		
		int buf;
		int count = 0;

		while ((buf = fis.read()) != -1) {
			os.write(buf);
			count++;
		}
		os.close();
		fis.close();
		System.out.println("sendFile:" + fileName + " " + count + "Bytes");
	}
}
