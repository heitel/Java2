package bruchRechner;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.SoftBevelBorder;

public class BruchrechnerWin extends JFrame implements ActionListener {
	private JPanel buttonPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JButton calcButton = new JButton("Rechnen");
	private BruchPanel b1Panel = new BruchPanel(this, "Bruch 1", true);
	private JComboBox<String> operatorCombo = new JComboBox<String>(
			new String[] { "+", "-", "*", "/" });
	private BruchPanel b2Panel = new BruchPanel(this, "Bruch 2", true);
	private BruchPanel ergPanel = new BruchPanel(this, "Ergebnis",
			false);

	public BruchrechnerWin(String title) throws HeadlessException {
		super(title);

		buildWindow();
	}

	private void buildWindow() {
		add(mainPanel, BorderLayout.CENTER);

		mainPanel.add(b1Panel);
		mainPanel.add(operatorCombo);
		mainPanel.add(b2Panel);
		mainPanel.add(new JLabel("="));
		mainPanel.add(ergPanel);

		add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED));
		buttonPanel.add(calcButton);
		calcButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == calcButton) {
			Bruch b1 = b1Panel.getBruch();
			Bruch b2 = b2Panel.getBruch();
			Bruch erg = null;
			char op = ((String) operatorCombo.getSelectedItem())
					.charAt(0);

			switch (op) {
			case '+':
				erg = b1.add(b2);
				break;
			case '-':
				erg = b1.sub(b2);
				break;
			case '*':
				erg = b1.mul(b2);
				break;
			case '/':
				erg = b1.div(b2);
				break;
			default:
				break;
			}
			ergPanel.setBruch(erg);
		}
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
		BruchrechnerWin win = new BruchrechnerWin("Bruchrechner");

		win.setBounds(0, 0, 500, 220);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
