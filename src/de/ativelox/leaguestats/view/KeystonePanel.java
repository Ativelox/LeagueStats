package de.ativelox.leaguestats.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * A {@link JPanel} which displays the keystone of the current summoner.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class KeystonePanel extends JPanel {

	/**
	 * The height of this component.
	 */
	private static final int COMPONENT_HEIGHT = Display.WINDOW_HEIGHT / 20 - (2 * DetailPanel.V_GAP);

	/**
	 * The width of this component.
	 */
	private static final int COMPONENT_WIDTH = Display.WINDOW_HEIGHT / 20 - DetailPanel.V_GAP;

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The image of this summoners keystone.
	 */
	private Image keystoneImage;

	/**
	 * Creates a new {@link JPanel} which displays the keystone of the current
	 * summoner.
	 */
	public KeystonePanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, DetailPanel.V_GAP));
		this.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));

	}

	/**
	 * Sets the keystone image to the current keystone image of this summoner.
	 * 
	 * @param mKeystone
	 *            The new keystone image.
	 */
	public void setKeystoneImage(final Image mKeystone) {
		this.keystoneImage = mKeystone;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.keystoneImage == null) {
			return;

		}

		g.drawImage(this.keystoneImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

}
