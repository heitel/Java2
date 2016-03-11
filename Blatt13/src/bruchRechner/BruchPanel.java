package bruchRechner;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BruchPanel extends JPanel implements ActionListener {
	private JTextField zaehlerText = new JTextField();
	private JTextField nennerText = new JTextField();
	private JButton kuerzenButton = new JButton("Kürzen");
	private Bruch bruch = new Bruch();

	public BruchPanel(BruchrechnerWin win, String title,
			boolean editable) {
		setBorder(new TitledBorder(title));

		setLayout(new GridLayout(3, 1));

		zaehlerText.setEditable(editable);
		nennerText.setEditable(editable);
		zaehlerText.setBackground(Color.WHITE);
		nennerText.setBackground(Color.WHITE);
		zaehlerText.setHorizontalAlignment(JTextField.RIGHT);
		nennerText.setHorizontalAlignment(JTextField.RIGHT);
		kuerzenButton.addActionListener(this);
		add(zaehlerText);
		add(nennerText);
		add(kuerzenButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == kuerzenButton) {
			inputBruch();
			bruch.kuerzen();
			outputBruch();
		}

	}

	public Bruch getBruch() {
		inputBruch();
		return bruch;
	}

	public void setBruch(Bruch b) {
		bruch = b;
		outputBruch();
	}

	private void outputBruch() {
		zaehlerText.setText("" + bruch.getZaehler());
		nennerText.setText("" + bruch.getNenner());
	}

	private void inputBruch() {
		bruch.setZaehler(Long.parseLong(zaehlerText.getText()));
		bruch.setNenner(Long.parseLong(nennerText.getText()));
	}

}
