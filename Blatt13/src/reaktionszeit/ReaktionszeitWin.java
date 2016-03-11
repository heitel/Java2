package reaktionszeit;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.SoftBevelBorder;

public class ReaktionszeitWin extends JFrame implements
		ActionListener {
	public static final int RESET_STATE = 0;
	public static final int HOT_STATE = 1;
	public static final int WAIT_STATE = 2;
	public static final int FINAL_STATE = 3;
	public static final int ERROR_STATE = 4;

	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JLabel timeDisplay = new JLabel("0:00:00.000");
	private JButton startButton = new JButton("Start");
	private JButton resetButton = new JButton("Reset");
	private DisplayPanel display = new DisplayPanel(this);
	private int currentState = RESET_STATE;
	private Timer timer = null;
	private Timer stopUhrTimer = null;
	private long startTime;

	public ReaktionszeitWin(String title) throws HeadlessException {
		super(title);

		buildWindow();
		setCurrentState(RESET_STATE);
	}

	private void buildWindow() {
		northPanel.add(timeDisplay);
		add(northPanel, BorderLayout.NORTH);
		northPanel.setBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED));

		southPanel.add(startButton);
		southPanel.add(resetButton);
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED));

		add(display);

		startButton.addActionListener(this);
		resetButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == startButton) {
			setCurrentState(HOT_STATE);
		}

		if (src == resetButton) {
			setCurrentState(RESET_STATE);
		}

		if (src == timer) {
			setCurrentState(WAIT_STATE);
		}

		if (src == stopUhrTimer) {
			doStopUhrTimer();
		}
	}

	private void doStopUhrTimer() {
		long diff = new Date().getTime() - startTime;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date(diff));
		cal.add(Calendar.HOUR_OF_DAY, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		timeDisplay.setText(sdf.format(cal.getTime()));
	}

	

	private void doReset() {
		startButton.setEnabled(true);
		timeDisplay.setText("0:00:00.000");
		startButton.setText("Start");
		display.setImState(-1);
		if (timer != null) {
			timer.stop();
		}
		if (stopUhrTimer != null) {
			stopUhrTimer.stop();
		}
	}

	

	private int calcRandomTime() {
		int zz = (int) (Math.random() * 4000) + 1000;
		System.out.println("calcRandomTime: " + zz);
		return zz;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int state) {
		currentState = state;
		switch (currentState) {
		case RESET_STATE:
			System.out.println("RESET_STATE");
			doReset();
			break;
		case HOT_STATE:
			startButton.setEnabled(false);
			System.out.println("HOT_STATE");
			timer = new Timer(calcRandomTime(), this);
			timer.start();			
			break;
		case WAIT_STATE:
			System.out.println("WAIT_STATE");
			timer.stop();
			display.setImState(0);
			stopUhrTimer = new Timer(1, this);
			stopUhrTimer.start();
			startTime = new Date().getTime();
			break;
		case ERROR_STATE:
			System.out.println("ERROR_STATE");
			display.setImState(1);
			timer.stop();
			timeDisplay.setText("Frühstart");
			break;
		case FINAL_STATE:
			System.out.println("FINAL_STATE");
			stopUhrTimer.stop();
			break;
		default:
			break;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReaktionszeitWin win = new ReaktionszeitWin(
				"Reaktionszeitmesser");

		win.setBounds(0, 0, 500, 500);
		win.setVisible(true);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
