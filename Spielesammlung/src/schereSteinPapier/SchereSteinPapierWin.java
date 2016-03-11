package schereSteinPapier;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class SchereSteinPapierWin extends JFrame implements ActionListener {
	private final static String IMG_FOLDER = "imgSchereSteinPapier/";
	private final static String[] G_IMG = { IMG_FOLDER + "schere.png",
			IMG_FOLDER + "stein.png", IMG_FOLDER + "papier.png" };
	private JMenuItem newGame = new JMenuItem("Neues Spiel");
	private JMenuItem quit = new JMenuItem("Beenden");
	private JLabel spielstand = new JLabel("0:0");
	private JButton schere = new JButton(new ImageIcon(G_IMG[0]));
	private JButton stein = new JButton(new ImageIcon(G_IMG[1]));
	private JButton papier = new JButton(new ImageIcon(G_IMG[2]));
	private int punkt1 = 0;
	private int punkt2 = 0;
	private int runde = 0;
	private JLabel d1 = new JLabel();
	private JLabel d2 = new JLabel();
	private JLabel rundeLabel = new JLabel("Runde: 0");
	private String player1;

	public SchereSteinPapierWin(String title) throws HeadlessException {
		super(title);
		buildMenu();
		buildWindow();
	}

	private void buildWindow() {
		setLayout(null);
		player1 = JOptionPane.showInputDialog("Wer bist Du?");
		// player1 = "Gandalf";
		Font fireEye = new Font("Fireye GF 3", Font.PLAIN, 32);
		JLabel pl1 = new JLabel(player1);
		pl1.setBounds(5, 5, 400, 30);
		pl1.setFont(fireEye);
		pl1.setHorizontalAlignment(JLabel.RIGHT);
		add(pl1);
		spielstand.setBounds(410, 7, 100, 30);
		spielstand.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		Font eHigh = new Font("Electronic Highway Sign", Font.PLAIN, 32);
		spielstand.setFont(eHigh);
		spielstand.setBackground(Color.BLACK);
		spielstand.setForeground(Color.GREEN);
		spielstand.setOpaque(true);
		spielstand.setHorizontalAlignment(JLabel.CENTER);
		add(spielstand);

		JLabel pl2 = new JLabel("Computer");
		pl2.setBounds(515, 5, 400, 30);
		pl2.setFont(fireEye);
		add(pl2);

		schere.setBounds(300, 100, 100, 100);
		stein.setBounds(425, 100, 100, 100);
		papier.setBounds(550, 100, 100, 100);
		add(schere);
		add(stein);
		add(papier);
		schere.addActionListener(this);
		stein.addActionListener(this);
		papier.addActionListener(this);

		d1.setBounds(410, 50, 50, 50);
		d2.setBounds(460, 50, 50, 50);
		add(d1);
		add(d2);

		Font f2 = new Font("Fireye GF 3", Font.PLAIN, 16);
		rundeLabel.setBounds(5, 50, 150, 30);
		rundeLabel.setFont(f2);
		add(rundeLabel);
		doNewGame();
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Datei");
		bar.add(file);
		file.add(newGame);
		file.addSeparator();
		file.add(quit);
		setJMenuBar(bar);

		// Short cut
		newGame.setAccelerator(KeyStroke.getKeyStroke('N',
				KeyEvent.CTRL_DOWN_MASK));
		quit.setAccelerator(KeyStroke
				.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));

		// EventHandling
		newGame.addActionListener(this);
		quit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == newGame) {
			doNewGame();
		}

		if (src == quit) {
			System.exit(0);
		}

		if (src == schere) {
			eval(1);
		}

		if (src == stein) {
			eval(2);
		}

		if (src == papier) {
			eval(3);
		}
	}

	private void eval(int wahl) {
		String[] ico = { "", IMG_FOLDER + "schere1.png",
				IMG_FOLDER + "stein1.png", IMG_FOLDER + "papier1.png" };
		int zz = getRandom();
		if ((wahl == 1 && zz == 2) || (wahl == 2 && zz == 3)
				|| (wahl == 3 && zz == 1)) {
			punkt2++;
		}
		if ((wahl == 1 && zz == 3) || (wahl == 2 && zz == 1)
				|| (wahl == 3 && zz == 2)) {
			punkt1++;
		}
		d1.setIcon(new ImageIcon(ico[wahl]));
		d2.setIcon(new ImageIcon(ico[zz]));
		spielstand.setText(punkt1 + ":" + punkt2);
		runde++;
		rundeLabel.setText("Runde: " + runde);
		if (punkt1 >= 3) {
			JOptionPane.showMessageDialog(this, "Gratulation " + player1
					+ ", Du hast gewonnen!");
		}
		if (punkt2 >= 3) {
			JOptionPane.showMessageDialog(this, "Schade " + player1
					+ ", Du hast verloren.");
		}
		if (punkt2 >= 3 || punkt1 >= 3) {
			schere.setEnabled(false);
			stein.setEnabled(false);
			papier.setEnabled(false);
		}
	}

	private int getRandom() {
		return (int) (Math.random() * 3) + 1;
	}

	private void doNewGame() {
		runde = 0;
		rundeLabel.setText("Runde: " + runde);
		d1.setIcon(null);
		d2.setIcon(null);
		punkt1 = 0;
		punkt2 = 0;
		spielstand.setText(punkt1 + ":" + punkt2);

		schere.setEnabled(true);
		stein.setEnabled(true);
		papier.setEnabled(true);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SchereSteinPapierWin win = new SchereSteinPapierWin(
				"Schere - Stein - Papier");
		win.setBounds(0, 0, 950, 300);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
