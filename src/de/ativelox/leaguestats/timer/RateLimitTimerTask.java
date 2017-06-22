package de.ativelox.leaguestats.timer;

import java.util.TimerTask;

/**
 * The timer tasked used by {@link RateLimitTimer}. Resets/Sets the flags needed
 * to run it appropriatly.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class RateLimitTimerTask extends TimerTask {

	/**
	 * The parent-Timer of this timer task.
	 */
	private final RateLimitTimer timer;

	/**
	 * Creates a new rate limit timer task, which resets all the flags needed to
	 * run it appropriatly.
	 * 
	 * @param mTimer
	 *            The parent (Timer) of this timer task.
	 */
	public RateLimitTimerTask(final RateLimitTimer mTimer) {
		this.timer = mTimer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		this.timer.setRequestsMade(0);
		this.timer.setBlocked(false);
		this.timer.setHasScheduleRunning(false);
	}

}
