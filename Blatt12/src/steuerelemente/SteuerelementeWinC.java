package steuerelemente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class SteuerelementeWinC extends JFrame implements ActionListener {
	private JButton button = new JButton("Bestellen");
	
	private JRadioButton schwarzwaelder = new JRadioButton("Schwarzwälder Kirschtorte");
	private JRadioButton kaesekuchen = new JRadioButton("Käsekuchen");
	private JRadioButton linzer = new JRadioButton("Linzer Torte");	
	
	private JCheckBox sahne = new JCheckBox("Sahne");

	public SteuerelementeWinC(String title) {
		super(title);

		// Steuerelemente müssen von Hand positioniert werden
		setLayout(null);

		JLabel label = new JLabel("Wählen Sie aus:");
		// positionieren
		label.setBounds(10, 10, 100, 30);
		// Hilfspanel für Rahmen anlegen
		JPanel	panel = new JPanel();
		panel.setBounds(5, 40, 250, 110);
		panel.setBorder(new TitledBorder("Kuchenauswahl"));
		panel.setLayout(null);
		schwarzwaelder.setBounds(5, 15, 220, 30);
		kaesekuchen.setBounds(5, 45, 220, 30);
		linzer.setBounds(5, 75, 220, 30);
		panel.add(schwarzwaelder);
		panel.add(kaesekuchen);
		panel.add(linzer);
		
		// Radios gruppieren
		ButtonGroup bg = new ButtonGroup();
		bg.add(schwarzwaelder);
		bg.add(kaesekuchen);
		bg.add(linzer);
		// Standardauswahl Schwarzwälder
		schwarzwaelder.setSelected(true);
		sahne.setBounds(10, 170, 100, 30);
		button.setBounds(10, 210, 100, 30);

		// Steuerelemente mit Fenster verbinden
		add(label);
		add(panel);
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
			
			if (schwarzwaelder.isSelected()) {
				txt += "Schwarzwälder ";
			}
			
			if (kaesekuchen.isSelected()) {
				txt += "Käsekuchen ";
			}			
			
			if (linzer.isSelected()) {
				txt += "Linzer Torte ";
			}
			
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
		SteuerelementeWinC win = new SteuerelementeWinC("Test Controls");

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 400, 400);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
