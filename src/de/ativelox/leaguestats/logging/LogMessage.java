package de.ativelox.leaguestats.logging;

/**
 * A wrapper used to identify log messages. Every log message comes with its
 * message, its level (ERROR, INFO, DEBUG) and its timestamp.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class LogMessage {

	/**
	 * The log level of this message.
	 */
	private final ELogLevel logLevel;

	/**
	 * The actual content of this message.
	 */
	private final String message;

	/**
	 * The timestamp when this message arrived.
	 */
	private final long timestamp;

	/**
	 * Creates a new logging message with the given data.
	 * 
	 * @param mMessage
	 *            The actual content of the message
	 * @param mLogLevel
	 *            The log level of this message
	 * @param mTimeStamp
	 *            The timestamp when this message arrived
	 */
	public LogMessage(final String mMessage, final ELogLevel mLogLevel, final long mTimeStamp) {
		this.message = mMessage;
		this.logLevel = mLogLevel;
		this.timestamp = mTimeStamp;
	}

	/**
	 * Gets the log level of this message.
	 * 
	 * @return The log level of this message
	 */
	public final ELogLevel getLogLevel() {
		return this.logLevel;
	}

	/**
	 * Gets the actual content of this message.
	 * 
	 * @return The actual content of this message
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * Gets the timestamp when this message arrived
	 * 
	 * @return The timestamp when this message arrived
	 */
	public final long getTimestamp() {
		return this.timestamp;
	}
}