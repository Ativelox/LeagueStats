package de.ativelox.leaguestats.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.model.TierIcon;
import de.ativelox.leaguestats.util.ScreenEssentials;

/**
 * The main frame used to display all the information. Provides methods to
 * interact with it's children.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Display extends JFrame {

	/**
	 * The factor at which to scale the window
	 */
	private final static double WINDOW_SCALING_FACTOR = 8f / 10;

	/**
	 * The window width.
	 */
	public final static int WINDOW_WIDTH = (int) (ScreenEssentials.SCREEN_WIDTH * WINDOW_SCALING_FACTOR);

	/**
	 * The window height.
	 */
	public final static int WINDOW_HEIGHT = (int) (ScreenEssentials.SCREEN_HEIGHT * (WINDOW_SCALING_FACTOR + 1 / 10f));

	/**
	 * The position at which this window should be placed.
	 */
	public final static Point WINDOW_POSITION = new Point((ScreenEssentials.SCREEN_WIDTH / 2) - (WINDOW_WIDTH / 2),
			(ScreenEssentials.SCREEN_HEIGHT / 2) - (WINDOW_HEIGHT / 2));

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * All the {@link SummonerPanel}s for the blue-side summoners.
	 */
	private final SummonerPanel[] blueSummoners;

	/**
	 * All the {@link SummonerPanel}s for the red-side summoners.
	 */
	private final SummonerPanel[] redSummoners;

	/**
	 * The main frame used to display all the information. Provides methods to
	 * interact with it's children. Call {@link Display#init()} to start adding
	 * the components to this frame.
	 * 
	 * @param mTitle
	 *            The title of this frame.
	 */
	public Display(final String mTitle) {
		super(mTitle);

		this.blueSummoners = new SummonerPanel[5];
		this.redSummoners = new SummonerPanel[5];

		for (int i = 0; i < this.blueSummoners.length; i++) {
			this.blueSummoners[i] = new SummonerPanel();
			this.redSummoners[i] = new SummonerPanel();

		}

	}

	/**
	 * Initializes this frame by adding all the components to it.
	 */
	public void init() {
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT);
		this.setLayout(new GridLayout(2, 5));
		this.setLocation(Display.WINDOW_POSITION);
		this.setResizable(false);

		JFrame.setDefaultLookAndFeelDecorated(true);

		for (final SummonerPanel panel : this.blueSummoners) {
			this.add(panel);

		}

		for (final SummonerPanel panel : this.redSummoners) {
			this.add(panel);

		}
		this.setVisible(true);

	}

	/**
	 * Sets the championLoadingArt for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the summonerName.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the summonerName.
	 * 
	 * @param mChampionImage
	 *            The championImage to set for the player.
	 */
	public void setChampionLoadingArt(final ETeamAffiliation mTeam, final int mPlayerNumber,
			final Image mChampionImage) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setChampionLoadingArt(mChampionImage);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setChampionLoadingArt(mChampionImage);

		}
	}

	/**
	 * Sets the tierIcon for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the tierIcon.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the tierIcon.
	 * 
	 * @param mTierIcon
	 *            The tierIcon to set for the player.
	 */
	public void setDivisionTierImage(final ETeamAffiliation mTeam, final int mPlayerNumber, final TierIcon mTierIcon) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setDivisionTierImage(mTierIcon);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setDivisionTierImage(mTierIcon);

		}
	}

	/**
	 * Sets the K/D/A for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the K/D/A.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the K/D/A.
	 * 
	 * @param mKDA
	 *            The K/D/A to set for the player.
	 */
	public void setKDA(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mKDA) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setKDA(mKDA);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setKDA(mKDA);

		}
	}

	/**
	 * Sets the keystone for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the keystone.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the keystone.
	 * 
	 * @param mKeystone
	 *            The keystone to set for the player.
	 */
	public void setKeystoneImage(final ETeamAffiliation mTeam, final int mPlayerNumber, final Image mKeystone) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setKeystoneImage(mKeystone);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setKeystoneImage(mKeystone);

		}
	}

	/**
	 * Sets the summonerIcon for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the summonerIcon.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the summonerIcon.
	 * 
	 * @param mSummonerIcon
	 *            The summonerIcon to set for the player.
	 */
	public void setSummonerIcon(final ETeamAffiliation mTeam, final int mPlayerNumber, final Image mSummonerIcon) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setSummonerIcon(mSummonerIcon);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setSummonerIcon(mSummonerIcon);

		}
	}

	/**
	 * Sets the summonerName for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the summonerName.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the summonerName.
	 * 
	 * @param mSummonerName
	 *            The summonerName to set for the player.
	 */
	public void setSummonerName(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mSummonerName) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setSummonerName(mSummonerName);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setSummonerName(mSummonerName);

		}
	}

	/**
	 * Sets the winrate for a given {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to set the winrate.
	 * 
	 * @param mPlayerNumber
	 *            The playerNumber for which to set the winrate.
	 * 
	 * @param mWinrate
	 *            The winrate to set for the player.
	 */
	public void setWinrate(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mWinrate) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.blueSummoners[mPlayerNumber - 1].setWinrate(mWinrate);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.redSummoners[mPlayerNumber - 1].setWinrate(mWinrate);

		}
	}
}
