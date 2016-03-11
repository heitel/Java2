package kniffel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class KniffelWin extends JFrame implements ActionListener {
	private final String[] FIGUREN = { "1er", "2er", "3er", "4er", "5er",
			"6er", "3er-Pasch", "4er-Pasch", "Full-House", "Kleine Straﬂe",
			"Groﬂe Straﬂe", "Kniffel", "Chance" };
	private JMenuItem newGame = new JMenuItem("Neues Spiel");
	private JMenuItem spieler1Menu = new JMenuItem("Spieler 1...");
	private JMenuItem spieler2Menu = new JMenuItem("Spieler 2...");
	private JMenuItem quit = new JMenuItem("Beenden");
	private AugenPanel[] ap = new AugenPanel[5];
	private JCheckBox[] box = new JCheckBox[5];
	private JButton buttonWuerfeln = new JButton("W¸rfeln");
	private JComboBox<String> comboFigur = new JComboBox<String>(FIGUREN);
	private JButton buttonAuswaehlen = new JButton("Ausw‰hlen");
	private MyTable[] table = new MyTable[2];
	private JScrollPane[] scroller = new JScrollPane[2];
	private String[] spieler = { "Spieler 1", "Spieler 2" };
	private int aktivSpieler = 0;
	private int wurfCount = 0;

	public KniffelWin(String title) throws HeadlessException {
		super(title);
		buildMenu();
		buildWindow();
	}

	private void buildWindow() {
		setLayout(null);

		JPanel wPanel = new JPanel();
		wPanel.setBounds(10, 10, 250, 65);
		add(wPanel);
		wPanel.setLayout(null);

		for (int i = 0; i < ap.length; i++) {
			ap[i] = new AugenPanel();
			ap[i].setBounds(i * 50, 0, 45, 45);
			wPanel.add(ap[i]);
		}

		for (int i = 0; i < box.length; i++) {
			box[i] = new JCheckBox();
			box[i].setBounds(i * 50 + 8, 45, 25, 25);
			wPanel.add(box[i]);
		}

		JTextArea text = new JTextArea(
				"Bitte die W¸rfel markieren, die nicht mehr in den den W¸rfelbecher kommen!");
		text.setBounds(10, 80, 250, 50);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		add(text);

		buttonWuerfeln.setBounds(10, 135, 250, 30);
		add(buttonWuerfeln);
		buttonWuerfeln.addActionListener(this);
		comboFigur.setBounds(10, 300, 125, 30);
		comboFigur.setMaximumRowCount(FIGUREN.length);
		add(comboFigur);
		comboFigur.addActionListener(this);
		buttonAuswaehlen.setBounds(145, 300, 115, 30);
		add(buttonAuswaehlen);
		buttonAuswaehlen.addActionListener(this);

		for (int i = 0; i < table.length; i++) {
			table[i] = new MyTable(14, 2);
			table[i].setGridColor(Color.LIGHT_GRAY);
			JTableHeader header = table[i].getTableHeader();
			TableColumnModel model = header.getColumnModel();
			model.getColumn(0).setHeaderValue(spieler[i]);
			model.getColumn(1).setHeaderValue("");
			header.setPreferredSize(new Dimension(0, 21));

			Font f = header.getFont();
			Font bf = f.deriveFont(Font.BOLD);
			header.setFont(bf);
			
			for (int j = 0; j < FIGUREN.length; j++) {
				table[i].setValueAt(FIGUREN[j], j, 0);
			}
			table[i].setRowHeight(21);
			table[i].setValueAt("Summe", 13, 0);

			table[i].getColumnModel().getColumn(1)
					.setCellRenderer(new StringCellRenderer());

			scroller[i] = new JScrollPane(table[i]);
			scroller[i].setBounds(270 + i * 260, 10, 250, 321);
			add(scroller[i]);
		}
		doNewGame();
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Datei");
		file.add(newGame);
		file.addSeparator();
		file.add(spieler1Menu);
		file.add(spieler2Menu);
		file.addSeparator();
		file.add(quit);

		bar.add(file);
		setJMenuBar(bar);

		newGame.addActionListener(this);
		spieler1Menu.addActionListener(this);
		spieler2Menu.addActionListener(this);
		quit.addActionListener(this);

		newGame.setAccelerator(KeyStroke.getKeyStroke('N',
				KeyEvent.CTRL_DOWN_MASK));
		spieler1Menu.setAccelerator(KeyStroke.getKeyStroke('1',
				KeyEvent.CTRL_DOWN_MASK));
		spieler2Menu.setAccelerator(KeyStroke.getKeyStroke('2',
				KeyEvent.CTRL_DOWN_MASK));
		quit.setAccelerator(KeyStroke
				.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == newGame) {
			doNewGame();
		}

		if (src == spieler1Menu) {
			updateSpielerName(1);
		}

		if (src == spieler2Menu) {
			updateSpielerName(2);
		}

		if (src == quit) {
			System.exit(0);
		}

		if (src == buttonWuerfeln) {
			doWurfeln();
		}

		if (src == buttonAuswaehlen) {
			doAuswaehlen();
		}
	}

	private void doAuswaehlen() {
		String item = (String) comboFigur.getSelectedItem();
		int auswahl = 0;
		for (int i = 0; i < FIGUREN.length; i++) {
			if (item.equals(FIGUREN[i])) {
				auswahl = i;
				break;
			}
		}

		if (auswahl >= 0 && auswahl <= 5) {
			int punkte = 0;
			for (int i = 0; i < ap.length; i++) {
				int w = ap[i].getAugenzahl();
				if (w == auswahl) {
					punkte += (w + 1);
				}
			}
			table[aktivSpieler].setValueAt("" + punkte, auswahl, 1);
		}

		if (auswahl == 6) { // 3er Pasch
			if (isPasch(3)) {
				table[aktivSpieler].setValueAt("" + summeAlle(), auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 7) { // 4er Pasch
			if (isPasch(4)) {
				table[aktivSpieler].setValueAt("" + summeAlle(), auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 8) { // Full House
			if (isPasch(2) && isPasch(3)) {
				table[aktivSpieler].setValueAt("" + 25, auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 9) { // Kleine Straﬂe
			if (isStrasse(4)) {
				table[aktivSpieler].setValueAt("" + 30, auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 10) {// Groﬂe Straﬂe
			if (isStrasse(5)) {
				table[aktivSpieler].setValueAt("" + 40, auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 11) {// Kniffel
			if (isPasch(5)) {
				table[aktivSpieler].setValueAt("" + 50, auswahl, 1);
			} else {
				table[aktivSpieler].setValueAt("" + 0, auswahl, 1);
			}
		}

		if (auswahl == 12) {// Chance
			table[aktivSpieler].setValueAt("" + summeAlle(), auswahl, 1);
		}

		int summe = 0;
		for (int i = 0; i < 6; i++) {
			String s = (String) table[aktivSpieler].getValueAt(i, 1);
			if (!s.isEmpty()) {
				summe += Integer.parseInt(s);
			}
		}
		if (summe >= 63) {
			summe += 35;
			table[aktivSpieler].getTableHeader().getColumnModel().getColumn(1)
					.setHeaderValue("Bonus +35");
		} else {
			table[aktivSpieler].getTableHeader().getColumnModel().getColumn(1)
					.setHeaderValue("");
		}
		for (int i = 6; i < 13; i++) {
			String s = (String) table[aktivSpieler].getValueAt(i, 1);
			if (!s.isEmpty()) {
				summe += Integer.parseInt(s);
			}
		}
		table[aktivSpieler].setValueAt("" + summe, 13, 1);

		setAktivSpieler((aktivSpieler + 1) % 2);
	}

	private boolean isStrasse(int max) {
		int[] z = new int[ap.length];
		for (int i = 0; i < z.length; i++) {
			z[i] = ap[i].getAugenzahl();
		}
		Arrays.sort(z);
		int len = 1;
		for (int i = 0; i < z.length - 1; i++) {
			if (z[i] + 1 == z[i + 1]) {
				len++;
				if (len >= max) {
					return true;
				}
			}
		}

		return false;
	}

	private int summeAlle() {
		int summe = 0;
		for (int i = 0; i < ap.length; i++) {
			summe += (ap[i].getAugenzahl() + 1);
		}
		return summe;
	}

	private boolean isPasch(int p) {

		int[] anz = new int[6];
		for (int i = 0; i < 5; i++) {
			anz[ap[i].getAugenzahl()]++;
		}

		for (int i = 0; i < anz.length; i++) {
			if (anz[i] >= p) {
				return true;
			}
		}
		return false;
	}

	private void doWurfeln() {
		buttonAuswaehlen.setEnabled(true);
		wurfCount++;
		if (wurfCount >= 3) {
			buttonWuerfeln.setEnabled(false);
		}
		for (int i = 0; i < ap.length; i++) {
			if (!box[i].isSelected()) {
				int zz = getZZ();
				ap[i].setAugenzahl(zz);
			}
		}
		repaint();
	}

	private int getZZ() {
		return (int) (Math.random() * 6);
	}

	private void resetPanel() {
		for (int i = 0; i < ap.length; i++) {
			ap[i].setAugenzahl(-1);
			box[i].setSelected(false);
		}
	}

	private void updateSpielerName(int nr) {
		String s = JOptionPane
				.showInputDialog(this, "Name Spieler " + nr + "?");
		if (s != null) {
			spieler[nr - 1] = s;
			JTableHeader header = table[nr - 1].getTableHeader();
			TableColumnModel model = header.getColumnModel();
			model.getColumn(0).setHeaderValue(spieler[nr - 1]);
			repaint();
		}
	}

	private void doNewGame() {
		table[0].getTableHeader().getColumnModel().getColumn(1)
				.setHeaderValue("");
		table[1].getTableHeader().getColumnModel().getColumn(1)
				.setHeaderValue("");
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].getRowCount(); j++) {
				table[i].setValueAt("", j, 1);
			}
		}
		setAktivSpieler(0);
	}

	private void setAktivSpieler(int nr) {
		aktivSpieler = nr;

		comboFigur.removeAllItems();
		for (int i = 0; i < table[nr].getRowCount() - 1; i++) {
			String s = (String) table[nr].getValueAt(i, 1);
			if (s.isEmpty()) {
				comboFigur.addItem((String) table[nr].getValueAt(i, 0));
			}
		}
		for (int i = 0; i < scroller.length; i++) {
			if (i == nr) {
				scroller[i].setBorder(new MatteBorder(3, 3, 3, 3, new Color(0,
						162, 232)));
			} else {
				scroller[i].setBorder(null);
			}
		}
		wurfCount = 0;
		buttonWuerfeln.setEnabled(true);
		buttonAuswaehlen.setEnabled(false);
		resetPanel();
		repaint();
	}
}
