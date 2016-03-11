package insertionSort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.SoftBevelBorder;

public class BalkenPanel extends JPanel implements ActionListener {
	private static final int SIZE = 20;
	private static final String TXT = "DHBWMANNHEIM";
	private char[] a = TXT.toCharArray();

	private JButton step = new JButton("Step");
	private JButton run = new JButton("Run");
	private JButton reset = new JButton("Reset");
	private InsertionSort ss = new InsertionSort(a);
	private Timer timer = new Timer(1000, this);

	public BalkenPanel() {
		setLayout(new BorderLayout());
		JPanel bp = new JPanel();
		bp.add(step);
		bp.add(run);
		bp.add(reset);

		step.addActionListener(this);
		run.addActionListener(this);
		reset.addActionListener(this);
		add(bp, BorderLayout.SOUTH);
		bp.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		doReset();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (a != null) {
			int width = getWidth();
			int bw = width / a.length;
			int h = 460 - SIZE;

			for (int i = 0; i < a.length; i++) {
				h = (a[i] - 'A' + 1) * SIZE;

				g.setColor(Color.GREEN);
				g.fillRect(i * bw, 460 - h, bw - 2, h);
				g.setColor(Color.WHITE);
				g.drawString("" + a[i], i * bw + 5, 470 - h);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == step) {
			ss.sortOne();			
		}

		if (src == timer) {
			if (!ss.sortOne()){
				timer.stop();
			}
		}
		
		if (src == run) {
			timer.start();
		}
		
		if (src == reset) {
			doReset();
		}
		
		repaint();
	}

	private void doReset() {
		a = TXT.toCharArray();
		ss = new InsertionSort(a);
	}
}
