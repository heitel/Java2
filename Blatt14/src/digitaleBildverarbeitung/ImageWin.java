package digitaleBildverarbeitung;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImageWin extends JFrame implements ActionListener {
	private static final String TITLE = "Digitale Bildverarbeitung";
	private static final String[][] TXT = { { "Rot", "RedFilter" },
			{ "Grün", "GreenFilter" }, { "Blau", "BlueFilter" }, { "-", "-" },
			{ "Negativ", "NegativFilter" }, { "-", "-" },
			{ "Graustufen", "BWFilter" }, { "Sepia", "SepiaFilter" },
			{ "-", "-" }, { "Horizontal spiegeln", "HorizontalFlipFilter" },
			{ "Vertical spiegeln", "VerticalFlipFilter" },
			{ "90° im Uhrzeigersinn drehen", "RotateCWFilter" }, { "-", "-" },
			{ "Weichzeichner", "BlurFilter" },
			{ "Scharfzeichner", "SharpenFilter" },
			{ "-", "-" },
			{ "SobelX", "SobelXFilter" },
			{ "SobelY", "SobelYFilter" },
			{ "Kantendetektor", "SobelFilter" }};

	private ImageDisplay display = new ImageDisplay();
	// FileMenu
	private JMenuItem newFile = new JMenuItem("Neu");
	private JMenuItem openFile = new JMenuItem("Öffnen...");
	private JMenuItem saveFile = new JMenuItem("Sichern");
	private JMenuItem saveAsFile = new JMenuItem("Sichern unter...");
	private JMenuItem printFile = new JMenuItem("Drucken");
	private JMenuItem quitFile = new JMenuItem("Beenden");

	// EditMenu
	private JMenuItem undoEdit = new JMenuItem("Zurück");

	// FilterMenu
	private JMenuItem[] itemFilter = new JMenuItem[TXT.length];

	public ImageWin(String title) throws HeadlessException {
		super(title);

		buildMenu();
		buildWindow();
		display.setFile(new File("ziege.JPG"));
	}

	private void buildWindow() {
		add(display);
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu file = new JMenu("Datei");
		file.add(newFile);
		newFile.setAccelerator(KeyStroke.getKeyStroke('N',
				InputEvent.CTRL_DOWN_MASK));
		newFile.addActionListener(this);
		file.add(openFile);
		openFile.setAccelerator(KeyStroke.getKeyStroke('O',
				InputEvent.CTRL_DOWN_MASK));
		openFile.addActionListener(this);
		file.addSeparator();
		file.add(saveFile);
		saveFile.addActionListener(this);
		saveFile.setAccelerator(KeyStroke.getKeyStroke('S',
				InputEvent.CTRL_DOWN_MASK));
		file.add(saveAsFile);
		saveAsFile.addActionListener(this);
		file.addSeparator();
		file.add(printFile);
		printFile.addActionListener(this);
		printFile.setAccelerator(KeyStroke.getKeyStroke('P',
				InputEvent.CTRL_DOWN_MASK));
		file.addSeparator();
		file.add(quitFile);
		quitFile.addActionListener(this);
		quitFile.setAccelerator(KeyStroke.getKeyStroke('Q',
				InputEvent.CTRL_DOWN_MASK));

		JMenu edit = new JMenu("Bearbeiten");
		edit.add(undoEdit);
		undoEdit.addActionListener(this);
		undoEdit.setAccelerator(KeyStroke.getKeyStroke('Z',
				InputEvent.CTRL_DOWN_MASK));

		// Filter Menu
		JMenu filter = new JMenu("Filter");
		for (int i = 0; i < TXT.length; i++) {
			if (TXT[i][0].equals("-")) {
				filter.addSeparator();
			} else {
				itemFilter[i] = new JMenuItem(TXT[i][0]);
				itemFilter[i].addActionListener(this);
				itemFilter[i].setActionCommand(TXT[i][1]);
				filter.add(itemFilter[i]);
			}
		}

		bar.add(file);
		bar.add(edit);
		bar.add(filter);
		setJMenuBar(bar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == newFile) {
			doNewFile();
			return;
		}

		if (src == openFile) {
			doOpenFile();
			return;
		}

		if (src == saveFile) {
			doSaveFile();
			return;
		}

		if (src == saveAsFile) {
			doSaveAsFile();
			return;
		}

		if (src == printFile) {
			doPrintFile();
			return;
		}

		if (src == quitFile) {
			System.exit(0);
		}

		if (src == undoEdit) {
			display.doUndo();
			return;
		}

		for (int i = 0; i < itemFilter.length; i++) {
			if (src == itemFilter[i]) {
				String cmd = itemFilter[i].getActionCommand();
				display.doFilter("filter." + cmd);
				return;
			}
		}
		
	}

	private void doPrintFile() {
		display.doPrint();
	}

	private void doSaveAsFile() {
		display.doSaveAs();
		adjustTitle();
	}

	private void doSaveFile() {
		display.doSave();
		adjustTitle();
	}

	private void adjustTitle() {
		File f  = display.getFile();
		if (f!=null) {
			setTitle(TITLE + " - " + f.getName());
		}
	}
	private void doOpenFile() {
		JFileChooser chooser = new JFileChooser(".");

		if (chooser.showDialog(this, "Öffnen") == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			display.setFile(f);
			adjustTitle();
		}
	}

	private void doNewFile() {
		// TODO Auto-generated method stub

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
		ImageWin win = new ImageWin(TITLE);
		win.setBounds(0, 0, 500, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
