package chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class ChatServerWin extends JFrame implements ActionListener{
	public static final int  SERVER_PORT = 5678;
	
	private int portNr;
	private ServerSocket	serverSocket;
	
	private Vector<ClientHandler> clients = new Vector<ClientHandler>();

	private JList	clientList = new JList(clients);
	private JScrollPane	scroller = new JScrollPane(clientList);
	private JPanel	buttonPanel = new JPanel();
	private JButton kickButton = new JButton("kick");
	
	public ChatServerWin(String title, int portNr) throws HeadlessException {
		super(title);
		this.portNr = portNr;
		buildWindow();
	}

	private void buildWindow() {
		Container	cont = getContentPane();
		
		cont.setLayout(new BorderLayout());
		cont.add(scroller, BorderLayout.CENTER);
		scroller.setPreferredSize(new Dimension(400, 300));
		buttonPanel.add(kickButton);
		cont.add(buttonPanel, BorderLayout.SOUTH);
		
		kickButton.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent e) {
		Object	src = e.getSource();
		
		if (src == kickButton){
			ClientHandler clh = (ClientHandler) clientList.getSelectedValue();
			if (clh != null) {
				clh.setStop(true);
				clients.remove(clh);
				clientList.setListData(clients);
			}
		}
		
	}

	public void startChat() {
		try {
			serverSocket = new ServerSocket(portNr);
			
			System.out.println("Server is listening on port: " + portNr);
			while (true) {
				Socket	clientSocket = serverSocket.accept();
				System.out.println(clientSocket);
				ClientHandler clientHandler = new ClientHandler(this, clientSocket);
				clients.add(clientHandler);
				
				clientList.setListData(clients);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sendMessage(String message) {
		for (ClientHandler element : clients) {
			element.sendMessage(message);
		}		
	}
	
	public static void main(String[] args) {
		ChatServerWin	win = new ChatServerWin("ChatServer Port + " + SERVER_PORT, SERVER_PORT);
		
		win.setSize(400, 300);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.startChat();
	}
}
