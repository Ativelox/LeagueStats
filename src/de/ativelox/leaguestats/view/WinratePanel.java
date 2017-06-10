package de.ativelox.leaguestats.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import de.ativelox.leaguestats.util.ScreenEssentials;

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

	private final ColorableTextPane winrateTextArea;

	/**
	 * 
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

	public void setWinrate(final String mWinrate) {
		String winrate[] = mWinrate.split("/");
		String winrateWins = winrate[0];
		String winrateLosses = winrate[1];
		String winratePercentage = winrate[2];

		this.setWinrate(winrateWins, winrateLosses, winratePercentage);

	}

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
