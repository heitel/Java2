package wuerfel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

public class WuerfelFenster extends JFrame implements ActionListener {
	private JPanel northPanel = new JPanel();
	private JLabel info = new JLabel("Augenzahl");
	private JTextField augenZahl = new JTextField(3);
	private JButton wuerfeln = new JButton("Würfeln");
	private WPanel wPanel = new WPanel(6);

	private String colHead[] = { "Augenzahl", "1", "2", "3", "4", "5", "6",
			"Gesamt" };
	private String data[][] = {
			{ "Absolut", "0", "0", "0", "0", "0", "0", "0" },
			{ "Relativ", "0", "0", "0", "0", "0", "0", "100.00%" } };

	private int gesamt = 0;
	private int absData[] = new int[6];
	private JTable table = new JTable(data, colHead);
	private JScrollPane scroller = new JScrollPane(table);

	public WuerfelFenster(String title) throws HeadlessException {
		super(title);

		buildWindow();

		doWuerfeln();

		table.setShowGrid(true);
	}

	private void buildWindow() {
		northPanel.add(info);
		northPanel.add(augenZahl);
		northPanel.add(wuerfeln);
		add(northPanel, BorderLayout.NORTH);
		add(wPanel, BorderLayout.CENTER);
		add(scroller, BorderLayout.SOUTH);

		scroller.setPreferredSize(new Dimension(0, 60));
		augenZahl.setBackground(Color.WHITE);
		augenZahl.setEditable(false);
		augenZahl.setHorizontalAlignment(JTextField.RIGHT);
		wuerfeln.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == wuerfeln) {
			doWuerfeln();
		}
	}

	private void doWuerfeln() {
		DecimalFormat dec = new DecimalFormat("0.00%");
		int zz = (int) (Math.random() * 6) + 1;

		augenZahl.setText("" + zz);
		wPanel.setZahl(zz);

		gesamt++;
		table.setValueAt("" + gesamt, 0, 7);

		absData[zz - 1]++;
		table.setValueAt("" + absData[zz - 1], 0, zz);

		for (int i = 1; i < 7; i++) {
			double rel = (double) absData[i - 1] / gesamt;
			table.setValueAt("" + dec.format(rel), 1, i);
		}
	}

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		WuerfelFenster win = new WuerfelFenster("Computer-Würfel");

		win.setBounds(0, 0, 500, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
