package de.ativelox.leaguestats.timer;

import java.util.Timer;

import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;

/**
 * A Timer which is able to determine when to block after a given amount of GET
 * requests have been made.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class RateLimitTimer extends Timer {

	/**
	 * The time to wait until the rate limit gets reset. The 3 extra seconds are
	 * a little puffer, because the 10 seconds aren't exactly accurate on RIOTs
	 * end.
	 */
	private static final int MILLISECOND_DELAY = 13_000;

	/**
	 * The maximum requests which can be sent in the time interval specified in
	 * {@link RateLimitTimer#MILLISECOND_DELAY}.
	 */
	private static final int MAX_REQUESTS = 10;

	/**
	 * Determining whether requests can be made or it has to be waited until the
	 * rate limit gets reset.
	 */
	private boolean isBlocked;

	/**
	 * Determining whether currently a schedule is running or not.
	 */
	private boolean hasScheduleRunning;

	/**
	 * The value of how many requests there have been made to the api since the
	 * last rate limit reset.
	 */
	private int requestsMade;

	/**
	 * The logger used for logging.
	 */
	private final ILogger logger;

	/**
	 * Creates a new RateLimitTimer which is able to determine when to block
	 * after a given amount of GET requests have been made.
	 */
	public RateLimitTimer() {
		super();

		this.requestsMade = 0;
		this.isBlocked = false;
		this.hasScheduleRunning = false;
		this.logger = LoggerFactory.getLogger();

	}

	/**
	 * Signals that a get request has been made to the API, thus checking if any
	 * more can be sent or not. Sets {@link RateLimitTimer#isBlocked}
	 * accordingly.
	 */
	public void getRequest() {
		this.requestsMade++;

		if (this.requestsMade >= MAX_REQUESTS) {
			this.logger.logInfo("Rate limit exceeded, waiting for rate limit to get reset...");
			this.isBlocked = true;

		}

		if (this.hasScheduleRunning) {
			return;
		}

		this.schedule(new RateLimitTimerTask(this), MILLISECOND_DELAY);
		this.hasScheduleRunning = true;

	}

	/**
	 * Returns whether the GET requests are blocked or not.
	 * 
	 * @return <tt>true</tt> if no more GET requests can be issues,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean isBlocked() {
		return this.isBlocked;

	}

	/**
	 * Sets the blocked status of this timer.
	 * 
	 * @param mIsBlocked
	 *            The new blocked status.
	 */
	public void setBlocked(final boolean mIsBlocked) {
		this.logger.logInfo("Rate limit got reset...");
		this.isBlocked = mIsBlocked;

	}

	/**
	 * Sets the amount of requests made of this timer.
	 * 
	 * @param mRequestsMade
	 *            The new amount of requests made.
	 */
	public void setRequestsMade(final int mRequestsMade) {
		this.requestsMade = mRequestsMade;

	}

	/**
	 * Sets whether a schedule is running on this timer.
	 * 
	 * @param mHasScheduleRunning
	 *            The new value of whether a schedule is running or not.
	 */
	public void setHasScheduleRunning(final boolean mHasScheduleRunning) {
		this.hasScheduleRunning = mHasScheduleRunning;

	}
}
