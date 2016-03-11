package malen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MalWin extends JFrame implements ActionListener, MouseListener,
		ChangeListener {
	private JMenuItem newFile = new JMenuItem("Neu");
	private JMenuItem openFile = new JMenuItem("Öffnen...");
	private JMenuItem saveFile = new JMenuItem("Sichern");
	private JMenuItem saveAsFile = new JMenuItem("Sichern unter...");
	private JMenuItem printFile = new JMenuItem("Drucken");
	private JMenuItem quitFile = new JMenuItem("Beenden");

	private JToolBar toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
	private JLabel colorLabel = new JLabel("Color...");
	private JSlider strokeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
	private JComboBox<String> combo = new JComboBox<String>(new String[]{"rund", "eckig"});
	
	private MalPanel malPanel = new MalPanel();

	public MalWin(String title) throws HeadlessException {
		super(title);

		buildMenu();
		buildWindow();
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Datei");

		setJMenuBar(bar);// Menübalken ins Fenster
		bar.add(file); // fileMenu in den Menübalken

		// Einträge im Filemenü einbauen
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveAsFile);
		file.addSeparator();
		file.add(printFile);
		file.addSeparator();
		file.add(quitFile);

		// Short cut hinzufügen
		newFile.setAccelerator(KeyStroke.getKeyStroke('N',
				KeyEvent.CTRL_DOWN_MASK));
		openFile.setAccelerator(KeyStroke.getKeyStroke('O',
				KeyEvent.CTRL_DOWN_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke('S',
				KeyEvent.CTRL_DOWN_MASK));
		printFile.setAccelerator(KeyStroke.getKeyStroke('P',
				KeyEvent.CTRL_DOWN_MASK));
		quitFile.setAccelerator(KeyStroke.getKeyStroke('Q',
				KeyEvent.CTRL_DOWN_MASK));

		// ActionListener setzen
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		saveAsFile.addActionListener(this);
		printFile.addActionListener(this);
		quitFile.addActionListener(this);

	}

	private void buildWindow() {
		toolBar.add(colorLabel);
		toolBar.add(strokeSlider);
		toolBar.add(combo);

		add(toolBar, BorderLayout.PAGE_START);
		add(malPanel, BorderLayout.CENTER);

		colorLabel.addMouseListener(this);
		colorLabel.setPreferredSize(new Dimension(50, 25));
		colorLabel.setOpaque(true);
		colorLabel.setBackground(malPanel.getCurrentColor());

		strokeSlider.addChangeListener(this);
		combo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == newFile) {
			malPanel.doNew();
		}

		if (src == openFile) {
			malPanel.doOpen();
		}

		if (src == saveFile) {
			malPanel.doSave();
		}

		if (src == saveAsFile) {
			malPanel.doSaveAs();
		}

		if (src == printFile) {
			malPanel.doPrint();
		}

		if (src == quitFile) {
			System.exit(0);
		}

		if (src == combo) {
			doCombo();
		}
	}

	private void doCombo() {
		int val = combo.getSelectedIndex();
		malPanel.setStyleIndex(val);
	}

	private void doColorButton() {
		Color color = JColorChooser.showDialog(this,
				"Wählen Sie die Vordergrundfarbe",
				malPanel.getCurrentColor());
		if (color != null) {
			malPanel.setCurrentColor(color);
			colorLabel.setBackground(color);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();

		if (src == colorLabel) {
			doColorButton();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object src = e.getSource();

		if (src == strokeSlider) {
			int val = strokeSlider.getValue();
			malPanel.setStrokeSize(val);
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
		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		MalWin win = new MalWin("Ein Malprogramm");
		win.setBounds(0, 0, 600, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
