import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UnicodeWin extends JFrame implements ActionListener {
	private final static char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
			'B', 'C', 'D', 'E', 'F' };

	private JComboBox<Character> combo = new JComboBox<Character>();
	private JTextArea text = new JTextArea();

	public UnicodeWin(String title) throws HeadlessException {
		super(title);

		buildWindow();
		showData(0);
	}

	private void buildWindow() {		
		JPanel north = new JPanel();
		north.add(new JLabel("Start:"));
		north.add(combo);
		add(north, BorderLayout.NORTH);
		
		for (int i = 0; i < HEX.length; i++) {
			combo.addItem(HEX[i]);
		}
		combo.addActionListener(this);
		combo.setMaximumRowCount(16);

		Font font = new Font("Sans Serif", Font.PLAIN, 36);
		text.setFont(font);
		JScrollPane scroller = new JScrollPane(text);
		add(scroller);
	}

	private String hex(int z) {
		String erg = "";

		for (int i = 0; i < 4; i++) {
			erg = HEX[z % 16] + erg;
			z >>= 4;
		}

		return erg;
	}

	public void showData(int offset) {
		StringBuffer buffer = new StringBuffer(4 * 1024 * 1024);
		int start = offset * 0x1000;

		for (int i = start; i < start + 0x1000; i++) {
			buffer.append((Console.padStringLeft(i+"", 5) + " | " + hex(i) + " | " + (char) i)); 
			buffer.append('\n');
		}
		text.setText(buffer.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == combo) {
			int i = combo.getSelectedIndex();
			showData(i);
		}	
	}

	public static void main(String[] args) {
		UnicodeWin win = new UnicodeWin("Unicode-Tabelle");
		win.setBounds(0, 0, 500, 900);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
