package stoppuhr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StoppuhrWin extends JFrame implements ActionListener {
	// Konstanten
	private final static String TEXT = "00:00:00.000";
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	
	// Steuerelemente
	private JLabel anzeige = new JLabel(TEXT);
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stopp");
	private JButton lap = new JButton("Lap");
	private JButton reset = new JButton("Reset");
	private Timer timer = new Timer(10, this);
	
	// Variablen
	private long startZeit = 0;
	private long lastZeit = 0;
	private boolean lapMode = false;

	public StoppuhrWin(String title) {
		super(title);
		sdf.setTimeZone(TimeZone.getTimeZone("Europa/Berlin"));
		buildWindow();
	}

	private void buildWindow() {
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, this.getClass()
					.getResourceAsStream("EHSMB.TTF"));
			Font font = f.deriveFont(Font.TRUETYPE_FONT, 64);
			anzeige.setFont(font);
			anzeige.setBackground(Color.BLACK);
			anzeige.setForeground(Color.GREEN);
			anzeige.setOpaque(true);
			anzeige.setHorizontalAlignment(JLabel.CENTER);
			add(anzeige, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.add(start);
			buttonPanel.add(stop);
			buttonPanel.add(lap);
			buttonPanel.add(reset);

			start.addActionListener(this);
			stop.addActionListener(this);
			lap.addActionListener(this);
			reset.addActionListener(this);

			add(buttonPanel, BorderLayout.SOUTH);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == start) {
			doStart();
		}
		if (src == stop) {
			doStop();
		}
		if (src == lap) {
			doLap();
		}
		if (src == reset) {
			doReset();
		}
		if (src == timer) {
			doTimer();
		}
	}

	private void doStart() {
		timer.start();
		// Startzeit merken
		startZeit = System.currentTimeMillis() - lastZeit;
	}

	private void doStop() {
		timer.stop();
	}

	private void doLap() {
		if (timer.isRunning()) {
			lapMode = !lapMode;
		}
	}

	private void doReset() {
		timer.stop();
		anzeige.setText(TEXT);
		lastZeit = 0;
		lapMode = false;
	}

	private void doTimer() {
		if (!lapMode) {
			// Zeitdifferenz ausrechnen und anzeigen
			lastZeit = System.currentTimeMillis() - startZeit;
			Date d = new Date(lastZeit);
			String txt = sdf.format(d);
			anzeige.setText(txt);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StoppuhrWin win = new StoppuhrWin("Stoppuhr");

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 550, 200);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
