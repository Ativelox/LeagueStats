package de.ativelox.leaguestats.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import de.ativelox.leaguestats.model.TierIcon;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ScreenEssentials;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class SummonerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image championLoadingArt;

	@SuppressWarnings("unused")
	private Image summonerIcon;

	private TierIcon divisionTier;

	private String summonerName;

	private DetailPanel detailPanel;

	/**
	 * 
	 */
	public SummonerPanel() {
		super();

		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 0, 0, 125));

		this.detailPanel = new DetailPanel();
		this.add(this.detailPanel, BorderLayout.PAGE_END);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
		if(this.championLoadingArt != null){
			g2d.drawImage(this.championLoadingArt, 0, 0, this.getWidth(), this.getHeight(), null);
		}		

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		if(this.divisionTier != null){
			g.setColor(ScreenEssentials.LOW_ALPHA_BLACK);
			g.drawImage(this.divisionTier.getIcon(), 0, 0, Assets.WIDTH / 2, Assets.HEIGHT / 2, null);
			g.fillRect(this.getWidth() / 3, 5, Assets.WIDTH - (this.getWidth() / 3), 20);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(this.divisionTier.getDescription(), this.getWidth() / 3 + this.getWidth() / 30, 20);
		}
		
		if(this.summonerName != null){
			g.setColor(ScreenEssentials.LOW_ALPHA_BLACK);
			g.fillRect(this.getWidth() / 10, this.getHeight() - (this.getHeight() / 6),
					this.getWidth() - (this.getWidth() / 10), 20);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(this.summonerName, this.getWidth() / 10 + this.getWidth() / 30,
					this.getHeight() - (this.getHeight() / 6) + 16);
			
		}


	}

	public void setChampionLoadingArt(final Image mChampionLoadingArt) {
		this.championLoadingArt = mChampionLoadingArt;

	}

	public void setSummonerName(final String mSummonerName) {
		this.summonerName = mSummonerName;

	}

	public void setSummonerIcon(final Image mSummonerIcon) {
		this.summonerIcon = mSummonerIcon;

	}

	public void setDivisionTierImage(final TierIcon mDivisionTier) {
		this.divisionTier = mDivisionTier;

	}

	public void setKeystoneImage(final Image mKeystone) {
		this.detailPanel.setKeystoneImage(mKeystone);

	}

	public void setKDA(final String mKDA) {
		this.detailPanel.setKDA(mKDA);

	}

	public void setWinrate(final String mWinrate) {
		this.detailPanel.setWinrate(mWinrate);

	}
}
