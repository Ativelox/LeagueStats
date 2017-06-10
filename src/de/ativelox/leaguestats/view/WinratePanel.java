package de.ativelox.leaguestats.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import de.ativelox.leaguestats.util.ScreenEssentials;

/**
 * A {@link JPanel} used to display detailed information about the winrate of
 * the current summoner.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class WinratePanel extends JPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The textpane used to display the winrate.
	 */
	private final ColorableTextPane winrateTextArea;

	/**
	 * Creates a new {@link JPanel} used to display detailed information about
	 * the winrate of the current summoner.
	 */
	public WinratePanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		this.setBackground(ScreenEssentials.HIGH_ALPHA_BLACK);

		this.winrateTextArea = new ColorableTextPane();
		this.winrateTextArea.setEditable(false);

		this.setWinrate("0W", "0L", "0%");

		this.add(this.winrateTextArea);
	}

	/**
	 * Sets the given winrate to {@link WinratePanel#winrateTextArea}.
	 * 
	 * @param mWinrate
	 *            The winrate to set.
	 * 
	 * @see WinratePanel#setWinrate(String, String, String)
	 */
	public void setWinrate(final String mWinrate) {
		String winrate[] = mWinrate.split("/");
		String winrateWins = winrate[0];
		String winrateLosses = winrate[1];
		String winratePercentage = winrate[2];

		this.setWinrate(winrateWins, winrateLosses, winratePercentage);

	}

	/**
	 * Sets the given wins/losses: percentage in a colored way to
	 * {@link KDAPanel#kdaTextArea}.
	 * 
	 * @param mWins
	 *            The wins of the winrate.
	 * @param mLosses
	 *            The losses of the winrate.
	 * @param mPercentage
	 *            The percentage of the winrate.
	 */
	private void setWinrate(final String mWins, final String mLosses, final String mPercentage) {
		Color percentageColor = ScreenEssentials.BLUE;
		if (Integer.parseInt(mPercentage.split("%")[0]) < 50) {
			percentageColor = ScreenEssentials.RED;
		}

		this.winrateTextArea.setText(null);
		this.winrateTextArea.appendText(mWins, ScreenEssentials.BLUE);
		this.winrateTextArea.appendText("/", Color.WHITE);
		this.winrateTextArea.appendText(mLosses, ScreenEssentials.RED);
		this.winrateTextArea.appendText(": ", Color.WHITE);
		this.winrateTextArea.appendText(mPercentage, percentageColor);

	}

}
