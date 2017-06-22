package de.ativelox.leaguestats.tray.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;

/**
 * An action listener which handles the button press for "toggle debug window"
 * on the tray.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ToggleDebugWindowListener implements ActionListener {

	/**
	 * The logger used for logging.
	 */
	private final ILogger logger;

	/**
	 * The application used to shut it down.
	 */
	private final LeagueStats leagueStats;

	/**
	 * Creates a new toggle debug wind listener which toggles the visibility of
	 * the debug window when an action got performed.
	 * 
	 * @param mLeagueStats
	 *            The application to shut down.
	 */
	public ToggleDebugWindowListener(final LeagueStats mLeagueStats) {
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
		this.logger.logInfo("Toggling debug frame visibility...");
		this.leagueStats.getDebugFrame().setVisible(!this.leagueStats.getDebugFrame().isVisible());

	}

}
