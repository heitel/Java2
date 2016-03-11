package fahrkartenautomat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FahrkartenautomatWin extends JFrame implements ActionListener,
		ListSelectionListener {
	// Konstanten
	private final static String FORMAT = "0.00 €";
	private final static String LEER = "0,00 €";

	// Steuerelemente
	private JLabel anzeige = new JLabel(LEER);
	private JList<Ziel> zieleList; // initialisiere später
	private JCheckBox bahnCard = new JCheckBox("Bahncard");
	private JCheckBox firstClass = new JCheckBox("1. Klasse");
	private JButton clear = new JButton("Löschen");
	private JTextField eingabe = new JTextField();
	private JButton kaufen = new JButton("Kaufen");
	private JTextArea ausgabe = new JTextArea();

	//
	private Ziel aktuellesZiel = null;

	public FahrkartenautomatWin(String title) {
		super(title);

		leseZiele();
		buildWindow();
	}

	private void buildWindow() {
		anzeige.setFont(new Font("Lucida Console", Font.PLAIN, 48));
		anzeige.setBackground(Color.BLACK);
		anzeige.setForeground(Color.GREEN);
		anzeige.setOpaque(true);
		anzeige.setHorizontalAlignment(JLabel.RIGHT);
		add(anzeige, BorderLayout.NORTH);

		// Nur ein Fahrtziel erlauben
		zieleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Rahmen zur Abtrennung
		zieleList.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JScrollPane scroller = new JScrollPane(zieleList);
		add(scroller, BorderLayout.CENTER);
		// Event Handler aktivieren
		zieleList.addListSelectionListener(this);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		add(southPanel, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(bahnCard);
		buttonPanel.add(firstClass);
		buttonPanel.add(clear);

		bahnCard.addActionListener(this);
		firstClass.addActionListener(this);
		clear.addActionListener(this);
		kaufen.addActionListener(this);

		southPanel.add(buttonPanel, BorderLayout.NORTH);
		southPanel.add(eingabe, BorderLayout.CENTER);
		southPanel.add(kaufen, BorderLayout.EAST);
		
		JScrollPane scroll2 = new JScrollPane(ausgabe);
		ausgabe.setEditable(false);
		southPanel.add(scroll2, BorderLayout.SOUTH);
		eingabe.setHorizontalAlignment(JTextField.RIGHT);
		scroll2.setPreferredSize(new Dimension(0, 150));
	}

	private void leseZiele() {
		String txt[] = FileInput.readTextFile("ziele.txt");
		Ziel ziel[] = new Ziel[txt.length];

		for (int i = 0; i < txt.length; i++) {
			String tmp[] = txt[i].split("~~~");
			ziel[i] = new Ziel(tmp[0], Double.parseDouble(tmp[1]));
		}

		zieleList = new JList<Ziel>(ziel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == bahnCard) {
			berechnePreis();
		}
		if (src == firstClass) {
			berechnePreis();
		}
		if (src == clear) {
			doClear();
		}
		if (src == kaufen) {
			doKaufen();
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();

		if (src == zieleList) {
			aktuellesZiel = zieleList.getSelectedValue();
			berechnePreis();
		}

	}

	private void doClear() {
		bahnCard.setSelected(false);
		firstClass.setSelected(false);
		zieleList.clearSelection();
		aktuellesZiel = null;
		berechnePreis();
		eingabe.setText("");
		ausgabe.setText("");
	}

	private void doKaufen() {
		try {
			double input = Console.String2Double(eingabe.getText());
			double preis = berechnePreis();
			if (input >= preis) {
				berechneRausGeld(input - preis);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void berechneRausGeld(double x) {
		String erg = Console.Double2String(FORMAT, x) + " sind: \n";
		int stue[] = { 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100,
				50, 20, 10, 5, 2, 1 };
		int z = (int) (x * 100 + 0.5);// Kaufmännisch runden

		for (int i = 0; i < stue.length; i++) {
			int anz = z / stue[i];
			z = z % stue[i];
			if (anz > 0) {
				erg += anz + " mal "
						+ Console.Double2String(FORMAT, stue[i] / 100.0) + "\n";
			}
		}
		ausgabe.setText(erg);
	}

	private double berechnePreis() {
		double preis = 0;
		if (aktuellesZiel != null) {
			preis = aktuellesZiel.getPreis();
			if (bahnCard.isSelected()) {
				preis /= 2;
			}
			if (firstClass.isSelected()) {
				preis *= 2;
			}
			String txt = Console.Double2String(FORMAT, preis);
			anzeige.setText(txt);
		} else {
			anzeige.setText(LEER);
		}
		return preis;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FahrkartenautomatWin win = new FahrkartenautomatWin(
				"Fahrkartenautomat Heidelberg");

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 380, 450);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
