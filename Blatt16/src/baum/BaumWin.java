package baum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BaumWin extends JFrame implements ActionListener, ChangeListener {
	private JComboBox<String> typeCombo = new JComboBox<String>(new String[]{"Pytagoras", "Sierpinski", "Von Koch"});
	private JComboBox<Integer> levelCombo = new JComboBox<Integer>();
	private JSlider slider = new JSlider(JSlider.HORIZONTAL, 50, 1000, 100);
	private BaumPanel baumPanel = new BaumPanel();
	private JLabel size = new JLabel();
	private JButton save = new JButton("speichern...");
	
	public BaumWin(String title) throws HeadlessException {
		super(title);
		
		buildWindow();
	}

	private void buildWindow() {
		for (int i = 1; i <= 18; i++) {
			levelCombo.addItem(i);
		}
		levelCombo.setMaximumRowCount(18);
		levelCombo.addActionListener(this);
		typeCombo.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Typ: "));
		panel.add(typeCombo);
		panel.add(new JLabel("Level: "));
		panel.add(levelCombo);		
		panel.add(new JLabel("Size: "));
		panel.add(slider);
		slider.addChangeListener(this);
		panel.add(size);
		size.setPreferredSize(new Dimension(40, 25));
		save.addActionListener(this);
		panel.add(save);
		panel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		add(panel, BorderLayout.NORTH);
		baumPanel.setBackground(Color.BLACK);
		JScrollPane scroller = new JScrollPane(baumPanel);
		add(scroller, BorderLayout.CENTER);
		updatePanel();
	}
	

	@Override
	public void stateChanged(ChangeEvent e) {
		Object src = e.getSource();
		
		if (src == slider) {
			int value = slider.getValue();
			size.setText(""+value);
			baumPanel.setScale(value);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == typeCombo) {
			updatePanel();
		}
		
		if (src == levelCombo) {
			int level = ((Integer)levelCombo.getSelectedItem()).intValue();
			baumPanel.setLevel(level);
		}
		
		if (src == save) {
			doSaveAs();
		}
	}

	private void doSaveAs() {
		JFileChooser chooser = new JFileChooser(new File("."));

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				baumPanel.save(f);
		}
	}

	private void updatePanel() {
		int level = ((Integer)levelCombo.getSelectedItem()).intValue();
		int scale = slider.getValue();
		int type = typeCombo.getSelectedIndex();
		
		size.setText(""+scale);
		baumPanel.createTree(level, scale, type);
	}

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager
		.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	
		BaumWin	win = new BaumWin("Rekursive Baumstrukturen");
		win.setUndecorated(true);
		win.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		win.setBounds(0, 0, 900, 700);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
