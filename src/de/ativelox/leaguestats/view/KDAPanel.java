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
public class KDAPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTextArea kdaTextArea;

	/**
	 * 
	 */
	public KDAPanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		this.kdaTextArea = new JTextArea("Waiting...");
		this.kdaTextArea.setEditable(false);
		this.add(this.kdaTextArea);
	}

	public void setKDA(final String mKDA) {
		this.kdaTextArea.setText(mKDA);
	}

}
