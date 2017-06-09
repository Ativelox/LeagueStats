package de.ativelox.leaguestats.view;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class WinratePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTextArea winrateTextArea;

	/**
	 * 
	 */
	public WinratePanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		this.winrateTextArea = new JTextArea("Waiting...");
		this.winrateTextArea.setEditable(false);
		this.add(this.winrateTextArea);
	}

	public void setWinrate(final String mWinrate) {
		this.winrateTextArea.setText(mWinrate);

	}

}
