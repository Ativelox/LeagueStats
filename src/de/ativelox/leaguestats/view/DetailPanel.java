package de.ativelox.leaguestats.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * A {@link JPanel} used to provide detailed information for the current
 * summoner.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class DetailPanel extends JPanel {

	/**
	 * The horizontal gap of this panel.
	 */
	public final static int H_GAP = 5;

	/**
	 * The vertical gap of this panel.
	 */
	public final static int V_GAP = 3;

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The panel showing the K/D/A of the current summoner.
	 */
	private final KDAPanel kdaPanel;

	/**
	 * The panel showing the keystone of the current summoner.
	 */
	private final KeystonePanel keystonePanel;

	/**
	 * The panel showing the winrate of the current summoner.
	 */
	private final WinratePanel winratePanel;

	/**
	 * Creates a new {@link JPanel} used to provide detailed information for the
	 * current summoner.
	 */
	public DetailPanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, H_GAP, V_GAP));

		this.setPreferredSize(new Dimension(this.getWidth(), Display.WINDOW_HEIGHT / 20));

		this.setOpaque(false);

		this.winratePanel = new WinratePanel();
		this.kdaPanel = new KDAPanel();
		this.keystonePanel = new KeystonePanel();

		this.add(this.winratePanel);
		this.add(this.kdaPanel);
		this.add(this.keystonePanel);

	}

	/**
	 * Sets the K/D/A for {@link DetailPanel}.
	 * 
	 * @param mKDA
	 *            The K/D/A to set.
	 */
	public void setKDA(final String mKDA) {
		this.kdaPanel.setKDA(mKDA);

	}

	/**
	 * Sets the Keystone for {@link DetailPanel}.
	 * 
	 * @param mKeystone
	 *            The Keystone to set.
	 */
	public void setKeystoneImage(final Image mKeystone) {
		this.keystonePanel.setKeystoneImage(mKeystone);

	}

	/**
	 * Sets the winrate for {@link DetailPanel}.
	 * 
	 * @param mWinrate
	 *            The winrate to set.
	 */
	public void setWinrate(final String mWinrate) {
		this.winratePanel.setWinrate(mWinrate);

	}
}
