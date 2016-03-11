package puzzle;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class PuzzleWin extends JFrame implements
		ComponentListener, WindowStateListener, ActionListener {
	private JMenuItem rndMove = new JMenuItem("Durcheinander");
	private JMenuItem quit = new JMenuItem("Beenden");
	private JMenuItem undo = new JMenuItem("Zurück");
	
	private PuzzlePanel panel = new PuzzlePanel();

	public PuzzleWin(String title) throws HeadlessException {
		super(title);
		buildMenu();
		buildWindow();
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("Datei");
		bar.add(fileMenu);
		fileMenu.add(rndMove);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		
		JMenu editMenu = new JMenu("Edit");
		bar.add(editMenu);
		editMenu.add(undo);
		
		rndMove.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));
		rndMove.addActionListener(this);
		quit.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));
		quit.addActionListener(this);
		
		undo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_DOWN_MASK));
		undo.addActionListener(this);
		
		setJMenuBar(bar);
	}

	private void buildWindow() {
		setLayout(null);
		add(panel);
		addComponentListener(this);
		addWindowStateListener(this);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		int w = getContentPane().getWidth();
		int h = getContentPane().getHeight();
		int min = Math.min(w, h);
		panel.setBounds((w - min) / 2, (h - min) / 2, min, min);
	}

	public void componentMoved(ComponentEvent e) {
	}
	public void componentShown(ComponentEvent e) {
	}
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		componentResized(null);
		panel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == quit) {
			System.exit(0);
		}
		if (src == rndMove) {
			for (int i = 0; i < 100; i++) {
				panel.doRandomMove();
			}
		}
		if (src == undo) {
			panel.doUndo();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PuzzleWin win = new PuzzleWin("Puzzle");
		win.setBounds(0, 0, 500, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
