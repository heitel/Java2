package kniffel;
import java.awt.Color;
import java.awt.Font;
import java.beans.Transient;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;



@SuppressWarnings("serial")
public class StringCellRenderer extends DefaultTableCellRenderer {
	private final static Font font = new Font("Comic Sans MS", Font.PLAIN, 22);

	public StringCellRenderer() {
		super();
		setFont(font);
		setForeground(Color.BLUE);
		setHorizontalAlignment(JLabel.CENTER);
	}

	@Override
	@Transient
	public Font getFont() {
		return font;
	}	
}
