package steuerelemente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SteuerelementeWinB extends JFrame implements ActionListener {
	private JButton button = new JButton("Bestellen");
	private JCheckBox sahne = new JCheckBox("Sahne");

	public SteuerelementeWinB(String title) {
		super(title);

		// Steuerelemente müssen von Hand positioniert werden
		setLayout(null);

		JLabel label = new JLabel("Wählen Sie aus:");
		// positionieren
		label.setBounds(10, 10, 100, 30);
		sahne.setBounds(10, 50, 100, 30);
		button.setBounds(10, 90, 100, 30);

		// Steuerelemente mit Fenster verbinden
		add(label);
		add(sahne);
		add(button);
		
		// Mausklicks vom Button an Fenster schicken
		button.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == button) {
			String txt = "Bestellung ";
			if (sahne.isSelected()){
				txt += "mit";
			}
			else {
				txt += "ohne";
			}
			txt += "Sahne!";
			JOptionPane.showMessageDialog(this, txt);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SteuerelementeWinB win = new SteuerelementeWinB("Test Controls");

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 400, 400);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
