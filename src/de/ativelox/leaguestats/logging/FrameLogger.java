package de.ativelox.leaguestats.logging;

import de.ativelox.leaguestats.view.logging.DebugFrame;

/**
 * A logger which is able to log messages to a given frame.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class FrameLogger implements ILogger {

	/**
	 * The frame to which should be logged.
	 */
	private final DebugFrame frame;

	/**
	 * A flag determining whether debug prints should be logged or not.
	 */
	private boolean isDebugEnabled;

	/**
	 * Creates a new frame logger for the given frame and sets the debug flag to
	 * <tt>true</tt>.
	 * 
	 * @param mFrame
	 *            The frame to log to.
	 */
	public FrameLogger(final DebugFrame mFrame) {
		this(mFrame, true);

	}

	/**
	 * Creates a new frame logger for the given frame and sets the debug flag to
	 * the value given.
	 * 
	 * @param mFrame
	 *            The frame to log to.
	 * 
	 * @param mIsDebugEnabled
	 *            The flag whether debug prints should be logged or not.
	 */
	public FrameLogger(final DebugFrame mFrame, boolean mIsDebugEnabled) {
		this.frame = mFrame;
		this.isDebugEnabled = mIsDebugEnabled;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.leaguestats.logging.ILogger#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return this.isDebugEnabled;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.leaguestats.logging.ILogger#log(java.lang.String,
	 * de.ativelox.leaguestats.logging.ELogLevel)
	 */
	@Override
	public void log(String mMessage, ELogLevel mLevel) {
		if (mLevel == ELogLevel.DEBUG && !isDebugEnabled()) {
			return;

		}
		this.frame.log(mMessage + System.lineSeparator(), mLevel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.leaguestats.logging.ILogger#setDebugEnabled(boolean)
	 */
	@Override
	public void setDebugEnabled(boolean mIsDebugEnabled) {
		this.isDebugEnabled = mIsDebugEnabled;

	}

}
