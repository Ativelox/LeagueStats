package de.ativelox.leaguestats.logging;

import de.ativelox.leaguestats.view.logging.DebugFrame;

/**
 * A factory which provides static access to a logger object.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class LoggerFactory {

	/**
	 * The current logger instance.
	 */
	private static ILogger loggerInstance = null;

	/**
	 * The current frame used for logging.
	 */
	private static DebugFrame frame = null;

	/**
	 * Sets the debug frame used for logging.
	 * 
	 * @param mFrame
	 *            The frame mentioned.
	 */
	public static void setDebugFrame(final DebugFrame mFrame) {
		loggerInstance = null;
		frame = mFrame;

	}

	/**
	 * Gets a logger object which can be used for logging.
	 * 
	 * @return The logger mentioned.
	 */
	public final static ILogger getLogger() {
		if (loggerInstance == null) {
			loggerInstance = new FrameLogger(frame);
		}
		return loggerInstance;
	}

	/**
	 * Factory class. No implementation needed.
	 */
	private LoggerFactory() {

	}

}
