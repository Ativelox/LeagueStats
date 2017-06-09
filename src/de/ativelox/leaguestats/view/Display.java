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
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Display extends JFrame {

	private final static double WINDOW_SCALING_FACTOR = 8f / 10;

	public final static int WINDOW_WIDTH = (int) (ScreenEssentials.SCREEN_WIDTH * WINDOW_SCALING_FACTOR);
	public final static int WINDOW_HEIGHT = (int) (ScreenEssentials.SCREEN_HEIGHT * (WINDOW_SCALING_FACTOR + 1/10f));

	public final static Point WINDOW_POSITION = new Point((ScreenEssentials.SCREEN_WIDTH / 2) - (WINDOW_WIDTH / 2),
			(ScreenEssentials.SCREEN_HEIGHT / 2) - (WINDOW_HEIGHT / 2));

	private final SummonerPanel[] alliedSummoners;
	private final SummonerPanel[] hostileSummoners;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Display(final String mTitle) {
		super(mTitle);

		this.alliedSummoners = new SummonerPanel[5];
		this.hostileSummoners = new SummonerPanel[5];

		for (int i = 0; i < this.alliedSummoners.length; i++) {
			this.alliedSummoners[i] = new SummonerPanel();
			this.hostileSummoners[i] = new SummonerPanel();

		}

	}

	public void init() {
		this.setUndecorated(true);
		this.setBackground(Color.green);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT);
		this.setLayout(new GridLayout(2, 5));
		this.setLocation(Display.WINDOW_POSITION);
		this.setResizable(false);

		for (final SummonerPanel panel : this.alliedSummoners) {
			this.add(panel);

		}

		for (final SummonerPanel panel : this.hostileSummoners) {
			this.add(panel);

		}
		this.setVisible(true);

	}

	public void setSummonerIcon(final ETeamAffiliation mTeam, final int mPlayerNumber, final Image mSummonerIcon) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setSummonerIcon(mSummonerIcon);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setSummonerIcon(mSummonerIcon);

		}
	}

	public void setSummonerName(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mSummonerName) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setSummonerName(mSummonerName);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setSummonerName(mSummonerName);

		}
	}

	public void setChampionLoadingArt(final ETeamAffiliation mTeam, final int mPlayerNumber,
			final Image mChampionImage) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setChampionLoadingArt(mChampionImage);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setChampionLoadingArt(mChampionImage);

		}
	}

	public void setKeystoneImage(final ETeamAffiliation mTeam, final int mPlayerNumber, final Image mKeystone) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setKeystoneImage(mKeystone);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setKeystoneImage(mKeystone);

		}
	}

	public void setDivisionTierImage(final ETeamAffiliation mTeam, final int mPlayerNumber, final TierIcon mTierIcon) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setDivisionTierImage(mTierIcon);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setDivisionTierImage(mTierIcon);

		}
	}

	public void setKDA(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mKDA) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setKDA(mKDA);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setKDA(mKDA);

		}
	}

	public void setWinrate(final ETeamAffiliation mTeam, final int mPlayerNumber, final String mWinrate) {
		if (mTeam == ETeamAffiliation.BLUE) {
			this.alliedSummoners[mPlayerNumber - 1].setWinrate(mWinrate);

		} else if (mTeam == ETeamAffiliation.RED) {
			this.hostileSummoners[mPlayerNumber - 1].setWinrate(mWinrate);

		}
	}
}
