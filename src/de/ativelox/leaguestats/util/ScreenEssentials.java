package de.ativelox.leaguestats.util;

import java.awt.Color;
import java.awt.Toolkit;

/**
 * Provides constants to use for displaying frames and components.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class ScreenEssentials {

	/**
	 * The Color blue used throughout this project.
	 */
	public static final Color BLUE = new Color(0, 255, 255);

	/**
	 * The Color green used throughout this project.
	 */
	public static final Color GREEN = new Color(0, 255, 0);
	
	/**
	 * The Color gold used throughout this project.
	 */
	public static final Color GOLD = new Color(218, 165, 32);

	/**
	 * A black color with a high alpha value, being not very transparent.
	 */
	public static final Color HIGH_ALPHA_BLACK = new Color(24, 24, 24, 180);

	/**
	 * A black color with a low alpha value, being very transparent.
	 */
	public static final Color LOW_ALPHA_BLACK = new Color(24, 24, 24, 80);

	/**
	 * The Color red used throughout this project.
	 */
	public static final Color RED = new Color(255, 0, 0);
	
	/**
	 * The Color red used while logging errors.
	 */
	public static final Color LOG_ERROR_RED = new Color(180, 0, 0);

	/**
	 * The screen height of the primary monitor.
	 */
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * The screen width of the primary monitor.
	 */
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

	/**
	 * Utility class, no initialization needed.
	 */
	private ScreenEssentials() {

	}

}
