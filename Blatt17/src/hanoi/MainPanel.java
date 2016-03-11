package hanoi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class MainPanel extends JPanel implements ActionListener{
	private HanoiPanel hanoiPanel = new HanoiPanel(4);
	private JMenuItem newGame = new JMenuItem("Neues Spiel...");
	private JMenuItem animation = new JMenuItem("Animation");
	private JMenuItem quit = new JMenuItem("Beenden");
	
	public MainPanel() {
		super();
		
		setLayout(new BorderLayout());
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("Datei");
		bar.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.add(animation);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		
		newGame.addActionListener(this);
		newGame.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		animation.addActionListener(this);
		quit.addActionListener(this);
		quit.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));
		add(bar, BorderLayout.NORTH);
		add(hanoiPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == newGame) {
			hanoiPanel.doNewGame();
		}
		
		if (src == animation) {
			hanoiPanel.doAnimation();
		}
		
		if (src == quit) {
			System.exit(0);
		}
	}

}
