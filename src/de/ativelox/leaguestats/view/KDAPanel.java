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
public class KDAPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ColorableTextPane kdaTextArea;

	/**
	 * 
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

	public void setKDA(final String mKDA) {
		String kda[] = mKDA.split("/");
		String kdaWins = kda[0].replace(",", ".");
		String kdaDeaths = kda[1].replace(",", ".");
		String kdaAssists = kda[2].replace(",", ".");
		
		this.setKDA(kdaWins, kdaDeaths, kdaAssists);
	}
	
	private void setKDA(final String mWins, final String mDeaths, final String mAssists){
		this.kdaTextArea.setText(null);
		this.kdaTextArea.appendText(mWins, ScreenEssentials.BLUE);
		this.kdaTextArea.appendText("/", Color.WHITE);
		this.kdaTextArea.appendText(mDeaths, ScreenEssentials.RED);
		this.kdaTextArea.appendText("/", Color.WHITE);
		this.kdaTextArea.appendText(mAssists, ScreenEssentials.GREEN);
	}

}
