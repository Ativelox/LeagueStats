package de.ativelox.leaguestats.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import de.ativelox.leaguestats.util.ScreenEssentials;

/**
 * A {@link JPanel} used to display information about the K/D/A of the current
 * summoner.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class KDAPanel extends JPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The textpane used to display the K/D/A.
	 */
	private final ColorableTextPane kdaTextArea;

	/**
	 * Creates a new {@link KDAPanel} used to display information about the
	 * K/D/A of the current summoner.
	 */
	public KDAPanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		this.setBackground(ScreenEssentials.HIGH_ALPHA_BLACK);

		this.kdaTextArea = new ColorableTextPane();
		this.kdaTextArea.setEditable(false);

		this.setKDA("0", "0", "0");

		this.add(this.kdaTextArea);
	}

	/**
	 * Sets the given K/D/A to {@link KDAPanel#kdaTextArea}.
	 * 
	 * @param mKDA
	 *            The K/D/A to set.
	 * 
	 * @see KDAPanel#setKDA(String, String, String)
	 */
	public void setKDA(final String mKDA) {
		String kda[] = mKDA.split("/");
		String kdaWins = kda[0].replace(",", ".");
		String kdaDeaths = kda[1].replace(",", ".");
		String kdaAssists = kda[2].replace(",", ".");

		this.setKDA(kdaWins, kdaDeaths, kdaAssists);
	}

	/**
	 * Sets the given kills/deaths/assists in a colored way to
	 * {@link KDAPanel#kdaTextArea}.
	 * 
	 * @param mKills
	 *            The kills of the K/D/A.
	 * @param mDeaths
	 *            The deaths of the K/D/A.
	 * @param mAssists
	 *            The assists of the K/D/A.
	 */
	private void setKDA(final String mKills, final String mDeaths, final String mAssists) {
		this.kdaTextArea.setText(null);
		this.kdaTextArea.appendText(mKills, ScreenEssentials.BLUE);
		this.kdaTextArea.appendText("/", Color.WHITE);
		this.kdaTextArea.appendText(mDeaths, ScreenEssentials.RED);
		this.kdaTextArea.appendText("/", Color.WHITE);
		this.kdaTextArea.appendText(mAssists, ScreenEssentials.GREEN);

	}
}
