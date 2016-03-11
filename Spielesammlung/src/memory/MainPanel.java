package memory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class MainPanel extends JRootPane implements ActionListener{
	static public String codeBase = "";
	private JApplet applet;
	
	private final static int STATE0 = 0; // keine Karte aufgedeckt
	private final static int STATE1 = 1; // eine Karte aufgedeckt
//	private final static int STATE2 = 2; // zwei Karten aufgedeckt, keine
	// verdeckten Karten mehr
//	private final static int STATE3 = 3; // zwei Karten aufgedeckt, Karten
	// sind gleich
	private final static int STATE4 = 4; // zwei Karten aufgedeckt, Karten
	// sind ungleich

	private final static String meldung[] = new String[] {
			"Erste Karte aufgedeckt...", "Zeit zum rumdrehen ist abgelaufen",
			"Zwei gleiche Karten aufgedeckt", "Karten waren nicht gleich",
			"Spieler 1 hat gewonnen", "Spieler 2 hat gewonnen", "unentschieden" // gibt's
																				// nicht!
	};

	private Karte karte[] = new Karte[30];
	private JPanel gridPane = new JPanel();

	private JMenuBar bar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Datei");
	private JMenuItem newGame = new JMenuItem("Neues Spiel");
	private JMenuItem quit = new JMenuItem("Beenden");

	private JLabel punkteLabel = new JLabel("Punkte");
	private JLabel akt1 = new JLabel(">");
	private JLabel s1Label = new JLabel("Spieler 1");
	private JTextField s1Punkte = new JTextField(3);

	private JLabel akt2 = new JLabel(">");
	private JLabel s2Label = new JLabel("Spieler 2");
	private JTextField s2Punkte = new JTextField(3);
	private JLabel meldungLabel = new JLabel("Meldung:");
	private JTextField meldungText = new JTextField();

	private int state;
	private Karte k1, k2;
	private Timer timer = new Timer(3000, this);
	private boolean aktSpieler = false;
	private int p1, p2;
	private boolean over = false;

	public MainPanel(JApplet aApplet) throws HeadlessException {
		applet = aApplet;
		
		if (aApplet == null) {
			buildMenuForApplication();
		} else {
			buildMenuForApplet();
			codeBase = applet.getCodeBase().toString();
			System.out.println(codeBase);
		}

		buildPanel();

	}

	private void buildMenuForApplet() {
		newGame.addActionListener(this);

		fileMenu.add(newGame);
		bar.add(fileMenu);
		

		setJMenuBar(bar);
	}

	private void buildPanel() {
		Container	cont = getContentPane();
		JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500,500));
		
		gridPane.setBorder(new LineBorder(Color.BLACK, 3));
		gridPane.setBounds(0, 0, 296, 360);
		gridPane.setLayout(new GridLayout(6, 5));

		for (int i = 0; i < karte.length; i++) {
			karte[i] = new Karte(i % 15);
			karte[i].addActionListener(this);
		}

		panel.add(gridPane);

		punkteLabel.setBounds(430, 70, 50, 30);
		akt1.setBounds(350, 100, 20, 30);
		s1Label.setBounds(370, 100, 50, 30);
		s1Punkte.setBounds(430, 100, 40, 30);
		s1Punkte.setHorizontalAlignment(JTextField.RIGHT);
		s1Punkte.setEnabled(false);

		akt2.setBounds(350, 130, 20, 30);
		s2Label.setBounds(370, 130, 50, 30);
		s2Punkte.setBounds(430, 130, 40, 30);
		s2Punkte.setHorizontalAlignment(JTextField.RIGHT);
		s2Punkte.setEnabled(false);

		panel.add(punkteLabel);
		panel.add(akt1);
		panel.add(s1Label);
		panel.add(s1Punkte);

		panel.add(akt2);
		panel.add(s2Label);
		panel.add(s2Punkte);

		meldungLabel.setBounds(5, 360, 100, 30);
		meldungText.setBounds(5, 385, 470, 30);
		meldungText.setEnabled(false);

		panel.add(meldungLabel);
		panel.add(meldungText);		
	}

	private void buildMenuForApplication() {
		newGame.setAccelerator(KeyStroke
				.getKeyStroke('N', InputEvent.CTRL_MASK));
		newGame.addActionListener(this);
		quit.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_MASK));
		quit.addActionListener(this);

		bar.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.addSeparator();
		fileMenu.add(quit);

		setJMenuBar(bar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == quit) {
			System.exit(0);
		}

		if (src == newGame) {
			doNewGame();
		}

		if (src instanceof Karte && !over && state != STATE4) {// (state!=STATE1)
																// &&
			doKlick((Karte) src);
			checkWin();
		}

		if (src == timer) {
			doTimer();
		}		
	}
	
	private void doNewGame() {
		meldungText.setText("");
		timer.stop();
		p1 = 0;
		p2 = 0;
		s1Punkte.setText("" + p1);
		s2Punkte.setText("" + p2);
		state = STATE0;
		akt2.setVisible(false);

		for (int i = 0; i < karte.length; i++) {
			karte[i].setVisible(true);
			karte[i].deckeZu();
		}
		mischen(10);
		over = false;
	}

	
	private void mischen(int wieOft) {
		gridPane.removeAll();

		for (int i = 0; i < wieOft; i++) {
			for (int j = 0; j < karte.length; j++) {
				int zz = (int) (Math.random() * karte.length);
				Karte tmp = karte[j];
				karte[j] = karte[zz];
				karte[zz] = tmp;
			}
		}

		for (int j = 0; j < karte.length; j++) {
			gridPane.add(karte[j]);
		}

	}
	
	private void doKlick(Karte k) {
		switch (state) {
		case STATE0:	
			if (k1 == k) {
				break;
			}
			if (!k.isOpen()) {
			k.deckeAuf();
			k1 = k;
			state = STATE1;
			meldungText.setText(meldung[0]);
			timer.start();
			}
			break;
		case STATE1:
			if (k1 == k) {
				break;
			}
			if (!k.isOpen()) {
			k2 = k;
			k.deckeAuf();
			if (k1.getNr() == k.getNr()) {
				meldungText.setText(meldung[2]);
				k1.setVisible(false);
				k.setVisible(false);
				if (aktSpieler) {
					p2++;
					s2Punkte.setText("" + p2);
				} else {
					p1++;
					s1Punkte.setText("" + p1);
				}
				state = STATE0;
				timer.stop();
			} else {
				state = STATE4;
				meldungText.setText(meldung[3]);
				timer.start();
				wechselSpieler();
			}
			}
			break;
		default:
			System.out.println("DEFAULT");
			break;
		}
	}

	private void checkWin() {
		over = true;

		for (int i = 0; i < karte.length; i++) {
			if (karte[i].isVisible()) {
				over = false;
				break;
			}
		}

		if (over) {
			timer.stop();
			if (p1 > p2) {
				meldungText.setText(meldung[4]);
			} else {
				if (p1 == p2) {
					meldungText.setText(meldung[6]);
				} else {
					meldungText.setText(meldung[5]);
				}
			}
		}
	}

	private void doTimer() {
		timer.stop();
		if (state == STATE1) {
			k1.deckeZu();
			k1 = null;
			meldungText.setText(meldung[1]);
			state = STATE0;
			wechselSpieler();
		}

		if (state == STATE4) {
			k1.deckeZu();
			k1 = null;
			k2.deckeZu();
			k2 = null;
			meldungText.setText("");
			state = STATE0;
		}
	}

	private void wechselSpieler() {
		aktSpieler = !aktSpieler;

		if (!aktSpieler) { // Spieler2
			akt1.setVisible(true);
			akt2.setVisible(false);
		} else { // Spieler1
			akt1.setVisible(false);
			akt2.setVisible(true);
		}
	}

}
