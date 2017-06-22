package de.ativelox.leaguestats.logging;

/**
 * An interface for loggers.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public interface ILogger {

	/**
	 * Whether the debugging channel of this logger is currently enabled or not.
	 * Note that this can be changed by using {@link #setDebugEnabled(boolean)}.
	 * 
	 * @return <tt>True</tt> if the debugging channel of this logger is
	 *         currently enabled, <tt>false</tt> if not
	 */
	public boolean isDebugEnabled();

	/**
	 * Logs the given message to the given logging level.
	 * 
	 * @param mMessage
	 *            The message to log
	 * @param mLevel
	 *            The logging level to log the message to
	 */
	public void log(final String mMessage, final ELogLevel mLevel);

	/**
	 * Logs the given message to the debugging channel. If the debugging channel
	 * is currently disabled the method will not log any message. The channel
	 * can be enabled by using {@link #setDebugEnabled(boolean)} and the current
	 * state can be accessed with {@link #isDebugEnabled()}.
	 * 
	 * @param mMessage
	 *            The message to log
	 */
	public default void logDebug(final String mMessage) {
		log(mMessage, ELogLevel.DEBUG);
	}

	/**
	 * Logs the given message to the error channel.
	 * 
	 * @param mMessage
	 *            The message to log
	 */
	public default void logError(final String mMessage) {
		log(mMessage, ELogLevel.ERROR);
	}

	/**
	 * Logs the given message to the info channel.
	 * 
	 * @param mMessage
	 *            The message to log
	 */
	public default void logInfo(final String mMessage) {
		log(mMessage, ELogLevel.INFO);
	}

	/**
	 * Sets whether the debugging channel of this logger is currently enabled or
	 * not.
	 * 
	 * @param mIsDebugEnabled
	 *            <tt>True</tt> if the debugging channel should get enabled,
	 *            <tt>false</tt> if not
	 */
	public void setDebugEnabled(final boolean mIsDebugEnabled);

}
