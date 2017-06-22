package de.ativelox.leaguestats.tray.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;

/**
 * An action listener which handles the button press for "exit" on the tray.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class ExitListener implements ActionListener {

	/**
	 * The logger used for logging.
	 */
	private final ILogger logger;

	/**
	 * The application used to shut it down.
	 */
	private final LeagueStats leagueStats;

	/**
	 * Creates a new exit listener which shuts this application down when an
	 * action got performed.
	 * 
	 * @param mLeagueStats
	 *            The application to shut down.
	 */
	public ExitListener(final LeagueStats mLeagueStats) {
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
		this.logger.logInfo("Exit button has been clicked...");
		this.leagueStats.stop();

	}

}
