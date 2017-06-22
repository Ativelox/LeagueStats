package de.ativelox.leaguestats.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import de.ativelox.leaguestats.model.TierIcon;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ScreenEssentials;

/**
 * A {@link JPanel} used to display non-detailed information of the current
 * summoner.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class SummonerPanel extends JPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The image of champion played by the current summoner.
	 */
	private Image championLoadingArt;

	/**
	 * The detail panel for the current summoner.
	 */
	private DetailPanel detailPanel;

	/**
	 * The flex TierIcon of the current summoner.
	 */
	private TierIcon flexDivisionTier;

	/**
	 * Determining whether this summoner is the current player.
	 */
	private boolean isPlayer;

	/**
	 * The solo TierIcon of the current summoner.
	 */
	private TierIcon soloDivisionTier;

	/**
	 * The summoner icon of the current summoner.
	 */
	@SuppressWarnings("unused")
	private Image summonerIcon;

	/**
	 * The summoner name of the current summoner.
	 */
	private String summonerName;

	/**
	 * Creates a new {@link JPanel} used to display non-detailed information of
	 * the current summoner.
	 */
	public SummonerPanel() {
		super();

		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 0, 0, 125));

		this.detailPanel = new DetailPanel();
		this.add(this.detailPanel, BorderLayout.PAGE_END);

	}

	/**
	 * Sets the championLoadingArt for {@link SummonerPanel}.
	 * 
	 * @param mChampionLoadingArt
	 *            The championLoadingArt to set.
	 */
	public void setChampionLoadingArt(final Image mChampionLoadingArt) {
		this.championLoadingArt = mChampionLoadingArt;

	}

	/**
	 * Sets the solo division tier for {@link SummonerPanel}.
	 * 
	 * @param mDivisionTier
	 *            The division tier to set.
	 */
	public void setSoloDivisionTierImage(final TierIcon mDivisionTier) {
		this.soloDivisionTier = mDivisionTier;

	}

	/**
	 * Sets the flex division tier for {@link SummonerPanel}.
	 * 
	 * @param mDivisionTier
	 *            The division tier to set.
	 */
	public void setFlexDivisionTierImage(final TierIcon mDivisionTier) {
		this.flexDivisionTier = mDivisionTier;

	}

	/**
	 * Sets the K/D/A for {@link SummonerPanel}.
	 * 
	 * @param mKDA
	 *            The K/D/A to set.
	 */
	public void setKDA(final String mKDA) {
		this.detailPanel.setKDA(mKDA);

	}

	/**
	 * Sets the keystone for {@link SummonerPanel}.
	 * 
	 * @param mKeystone
	 *            The keystone to set.
	 */
	public void setKeystoneImage(final Image mKeystone) {
		this.detailPanel.setKeystoneImage(mKeystone);

	}

	/**
	 * Sets the summoner icon for {@link SummonerPanel}.
	 * 
	 * @param mSummonerIcon
	 *            The summoner icon to set.
	 */
	public void setSummonerIcon(final Image mSummonerIcon) {
		this.summonerIcon = mSummonerIcon;

	}

	/**
	 * Sets the summoner name for {@link SummonerPanel}.
	 * 
	 * @param mSummonerName
	 *            The summoner name to set.
	 */
	public void setSummonerName(final String mSummonerName, final boolean mIsPlayer) {
		this.summonerName = mSummonerName;
		this.isPlayer = mIsPlayer;

	}

	/**
	 * Sets the winrate for {@link SummonerPanel}.
	 * 
	 * @param mWinrate
	 *            The winrate to set.
	 */
	public void setWinrate(final String mWinrate) {
		this.detailPanel.setWinrate(mWinrate);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// sets the alpha component of these graphics to an transparency of 30%.
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

		// prints the champion loading art onto this panel
		if (this.championLoadingArt != null) {
			g2d.drawImage(this.championLoadingArt, 0, 0, this.getWidth(), this.getHeight(), null);
		}

		// resets the alpha component of these graphics to an transparency of 0%
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// prints the flex division icon and its description onto this panel
		if (this.flexDivisionTier != null) {
			g.setColor(ScreenEssentials.LOW_ALPHA_BLACK);
			g.drawImage(this.flexDivisionTier.getIcon(), this.getWidth() - (Assets.WIDTH / 2), 0, Assets.WIDTH / 2,
					Assets.HEIGHT / 2, null);
			g.fillRect(this.getWidth() - (Assets.WIDTH / 2) + 10, Assets.HEIGHT / 2, Assets.WIDTH / 2 - 20, 20);
			g.fillRect(this.getWidth() - (Assets.WIDTH / 2) + 10, 0, Assets.WIDTH / 2 - 20, 20);
			g.setColor(Color.WHITE);
			g.setFont(Assets.NAME_FONT);
			g.drawString(this.flexDivisionTier.getDescription(), this.getWidth() - (Assets.WIDTH / 2) + 15,
					(Assets.HEIGHT / 2) + 18);
			g.setFont(Assets.SMALL_NAME_FONT);
			g.drawString("Flex", this.getWidth() - (Assets.WIDTH / 2) + 35, 18);

		}

		if (this.soloDivisionTier != null) {
			g.setColor(ScreenEssentials.LOW_ALPHA_BLACK);
			g.drawImage(this.soloDivisionTier.getIcon(), 0, 0, Assets.WIDTH / 2, Assets.HEIGHT / 2, null);
			g.fillRect(10, Assets.HEIGHT / 2, Assets.WIDTH / 2 - 20, 20);
			g.fillRect(10, 0, Assets.WIDTH / 2 - 20, 20);
			g.setColor(Color.WHITE);
			g.setFont(Assets.NAME_FONT);
			g.drawString(this.soloDivisionTier.getDescription(), 15, (Assets.HEIGHT / 2) + 18);
			g.setFont(Assets.SMALL_NAME_FONT);
			g.drawString("Solo/Duo", 22, 18);

		}

		// prints the summoner name onto this panel.
		if (this.summonerName != null) {
			g.setColor(ScreenEssentials.LOW_ALPHA_BLACK);
			g.fillRect(this.getWidth() / 10, this.getHeight() - (this.getHeight() / 6),
					this.getWidth() - (2 * (this.getWidth() / 10)), 20);
			g.setColor(Color.WHITE);

			if (this.isPlayer) {
				g.setColor(ScreenEssentials.GOLD);
			}

			g.setFont(Assets.NAME_FONT);
			g.drawString(this.summonerName, this.getWidth() / 10 + this.getWidth() / 30,
					this.getHeight() - (this.getHeight() / 6) + 16);

		}

	}
}
