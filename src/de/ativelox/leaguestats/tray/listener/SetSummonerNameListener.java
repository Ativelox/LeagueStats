package de.ativelox.leaguestats.tray.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import de.ativelox.leaguestats.util.Utils;

/**
 * An action listener which handles the button press for "set summoner name" on the
 * tray.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class SetSummonerNameListener implements ActionListener {

	/**
	 * The logger used for logging.
	 */
	private final ILogger logger;

	/**
	 * The application used to shut it down.
	 */
	private final LeagueStats leagueStats;

	/**
	 * Creates a new set summoner name listener which sets a new summoner name when an
	 * action got performed.
	 * 
	 * @param mLeagueStats
	 *            The application to shut down.
	 */
	public SetSummonerNameListener(final LeagueStats mLeagueStats) {
		this.leagueStats = mLeagueStats;
		this.logger = LoggerFactory.getLogger();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.logger.logInfo("Setting new Summoner Name...");
		this.leagueStats.getSettingsProvider().setSummonerName(Utils.setSummonerNamePopup());
		this.leagueStats.getSettingsProvider().saveSettings();
		this.leagueStats.stop();

	}

}
