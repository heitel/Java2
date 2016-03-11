package http;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class WebBrowser extends JFrame implements ActionListener, HyperlinkListener{
	private JTextField adresse = new JTextField();
	private JButton goTo = new JButton("goTo");
	private JEditorPane editor = new JEditorPane();

	public WebBrowser(String title) throws HeadlessException {
		super(title);

		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout());
		northPanel.add(adresse, BorderLayout.CENTER);
		northPanel.add(goTo, BorderLayout.EAST);
		goTo.addActionListener(this);
		JScrollPane	scroller = new JScrollPane(editor);
		add(scroller);
		editor.setEditable(false);
		editor.addHyperlinkListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == goTo) {
			try {
				editor.setPage(adresse.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
                HTMLDocument doc = (HTMLDocument)pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            } else {
                try {
                    pane.setPage(e.getURL());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }

		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebBrowser browser = new WebBrowser("Java Browser");
		browser.setBounds(0, 0, 400, 400);
		browser.setVisible(true);
		browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
