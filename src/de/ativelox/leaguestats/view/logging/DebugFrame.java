package de.ativelox.leaguestats.view.logging;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.util.ScreenEssentials;
import de.ativelox.leaguestats.view.ColorableTextPane;
import de.ativelox.leaguestats.view.Display;

/**
 * The DebugFrame used for the logger to log its messages onto.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class DebugFrame extends JFrame {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The textpane used to write logs onto.
	 */
	private final ColorableTextPane textPane;

	/**
	 * Creates a new DebugFrame used for the logger to log its messages onto.
	 */
	public DebugFrame() {
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(Display.WINDOW_WIDTH / 3, Display.WINDOW_HEIGHT);
		this.setLayout(new GridLayout(2, 5));
		this.setLocation(Display.WINDOW_POSITION);
		this.setResizable(false);

		this.textPane = new ColorableTextPane();
		this.textPane.setFont(new Font("Arial", Font.BOLD, 15));
		this.setPreferredSize(new Dimension(Display.WINDOW_WIDTH / 3, Display.WINDOW_HEIGHT));
		DefaultCaret caret = (DefaultCaret) this.textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		final JScrollPane sp = new JScrollPane(this.textPane);
		sp.getViewport().setBackground(Color.GRAY);
		sp.getViewport().setFocusable(false);
		sp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.add(sp);

		this.setVisible(true);
	}

	/**
	 * Logs the given message onto this frame with an appropriate color
	 * corresponding to the LogLevel given.
	 * 
	 * @param mLog
	 *            The message to log.
	 *            
	 * @param mLogLevel
	 *            The log level of the message.
	 */
	public void log(final String mLog, final ELogLevel mLogLevel) {
		final String logMessage = "[" + mLogLevel.toString() + "]:\t" + mLog;

		if (mLogLevel == ELogLevel.ERROR) {
			this.textPane.appendText(logMessage, ScreenEssentials.LOG_ERROR_RED);

		} else if (mLogLevel == ELogLevel.INFO) {
			this.textPane.appendText(logMessage, Color.LIGHT_GRAY);

		} else if (mLogLevel == ELogLevel.DEBUG) {
			this.textPane.appendText(logMessage, Color.WHITE);

		}
	}
}
