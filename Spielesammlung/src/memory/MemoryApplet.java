package memory;
import java.awt.HeadlessException;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class MemoryApplet extends JApplet {

	private MainPanel	mainPanel;
	
	public MemoryApplet() throws HeadlessException {
		super();
	}
	
	/**
	 * mainPane erzeugen und dem Applet zuordnen
	 */
	public void init() {
		super.init();
		
		mainPanel = new MainPanel(this);
		setRootPane(mainPanel);		
	}

}
