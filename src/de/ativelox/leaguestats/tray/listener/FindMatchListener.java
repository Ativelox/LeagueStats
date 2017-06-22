package de.ativelox.leaguestats.tray.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;

/**
 * An action listener which handles the button press for "find match" on the
 * tray.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class FindMatchListener implements ActionListener {

	/**
	 * The logger used for logging.
	 */
	private final ILogger logger;

	/**
	 * The application used to shut it down.
	 */
	private final LeagueStats leagueStats;

	/**
	 * Creates a new find match listener which instantly finds a game when an
	 * action got performed.
	 * 
	 * @param mLeagueStats
	 *            The application to find a match for.
	 */
	public FindMatchListener(final LeagueStats mLeagueStats) {
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
	public void actionPerformed(ActionEvent e) {
		this.logger.logInfo("Trying to get data instantly for given name...");
		this.leagueStats.getController().forceGameFinding();

	}

}
