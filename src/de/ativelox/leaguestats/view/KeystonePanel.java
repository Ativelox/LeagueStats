package de.ativelox.leaguestats.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class KeystonePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int COMPONENT_WIDTH = Display.WINDOW_HEIGHT / 20 - DetailPanel.V_GAP;
	private static final int COMPONENT_HEIGHT = Display.WINDOW_HEIGHT / 20 - (2 * DetailPanel.V_GAP);

	private Image keystoneImage;

	/**
	 * 
	 */
	public KeystonePanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, DetailPanel.V_GAP));
		this.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		if (this.keystoneImage == null) {
			return;

		}

		g.drawImage(this.keystoneImage, 0, 0, this.getWidth(), this.getHeight(), null);

	}

	public void setKeystoneImage(final Image mKeystone) {
		this.keystoneImage = mKeystone;

	}
}
