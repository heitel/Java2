package memory;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Karte extends JButton {
	private final static String ICON[] = {"MacOSX.png", "X-mid.png", "swirl.png", "ball.png", 
			"BeEntertainment.png", "BeHome.png", "AmcSafeflop.png", "Alert.png", "Time.png",
			"Messenger.png", "Morpho-Absoloni.png", "tree.png", "Circium-Japonicum-icon.gif",
			"sea-horse-icon.gif", "N5-icon.gif"
	};
	
	private ImageIcon icon;
	private int nr;

	public Karte(int nr) {
		this.nr = nr;
		Class<?> cl = getClass();
		String name = "img/" + ICON[nr];
		URL url = cl.getResource(name);
		icon = new ImageIcon(url);		
		System.out.println("Karte: " + nr + " erzeugt.");
	}

	public void deckeAuf() {
		setIcon(icon);
	}

	public void deckeZu() {
		setIcon(null);
	}

	public int getNr() {
		return nr;
	}

	public boolean isOpen() {
		if (getIcon()!=null) {
			return true;
		}
		return false;
	}
}
