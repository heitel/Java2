package steuerelemente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SteuerelementeWinA extends JFrame implements ActionListener {
	private JButton button = new JButton("Test");

	public SteuerelementeWinA(String title) {
		super(title);

		// Steuerelemente müssen von Hand positioniert werden
		setLayout(null);

		JLabel label = new JLabel("Push the button");
		// positionieren
		label.setBounds(10, 10, 100, 30);
		button.setBounds(10, 50, 100, 30);

		// Steuerelemente mit Fenster verbinden
		add(label);
		add(button);

		// Mausklicks vom Button an Fenster schicken
		button.addActionListener(this);
		
		// Farbvarianten
		getContentPane().setBackground(Color.CYAN);
		label.setBackground(Color.RED);
		label.setOpaque(true);// sonst wird Hintergrundfarbe nicht gesetzt
		label.setForeground(new Color(0, 255, 128));
		button.setBackground(Color.ORANGE);
		button.setForeground(Color.BLUE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == button) {
			JOptionPane.showMessageDialog(this, "Getroffen", "Nachricht",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SteuerelementeWinA win = new SteuerelementeWinA("Test Controls");

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 400, 400);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
