package chat;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class ChatClientInputHandler extends Thread{
	private InputStream		in;
	private JTextArea		ta;
	
	public ChatClientInputHandler(InputStream	in, JTextArea ta) {
		this.in = in;
		this.ta = ta;
		start();
	}

	public void run() {
		try {
			while (true) {
				int len = in.read();
				StringBuffer sb = new StringBuffer();
				
				for (int i = 0; i<len; i++) {
					sb.append((char)in.read());
				}
				String msg = sb.toString();
				System.out.println(msg);
				if (msg.startsWith("!!!")) {
					JOptionPane.showMessageDialog(null,"kicked!!!");
					System.exit(0);
					break;
				}
				
				ta.setText(ta.getText() + msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
