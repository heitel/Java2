package chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClientWin extends JFrame implements ActionListener {
	private int portNr;

	private JTextArea display = new JTextArea();
	private JScrollPane scroller = new JScrollPane(display);
	private JTextField line = new JTextField();
	private JButton sendButton = new JButton("send");
	private JPanel southPanel = new JPanel();

	private Socket socket;
	private OutputStream out;
	private InputStream in;

	public ChatClientWin(String title, int portNr) {
		super(title);

		this.portNr = portNr;

		buildWindow();
	}

	private void buildWindow() {
		Container cont = getContentPane();

		cont.add(scroller, BorderLayout.CENTER);
		cont.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout());
		southPanel.add(line, BorderLayout.CENTER);
		southPanel.add(sendButton, BorderLayout.EAST);
		sendButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == sendButton) {
			sendData();
		}
	}

	private void sendData() {
		try {
			if (socket != null) {
				String txt = line.getText() + "\n";
				out.write(txt.length());
				out.write(txt.getBytes());
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			socket = new Socket("localhost", portNr);
			in = socket.getInputStream();
			out = socket.getOutputStream();

			@SuppressWarnings("unused")
			ChatClientInputHandler cih = new ChatClientInputHandler(in, display);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ChatClientWin win = new ChatClientWin("Name", ChatServerWin.SERVER_PORT);
		win.setBounds(300, 300, 300, 300);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.connect();

	}
}
