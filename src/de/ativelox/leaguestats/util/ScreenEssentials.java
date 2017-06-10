package de.ativelox.leaguestats.util;

import java.awt.Color;
import java.awt.Toolkit;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ScreenEssentials {
	

	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 255, 255);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color HIGH_ALPHA_BLACK = new Color(24, 24, 24, 180);
	public static final Color LOW_ALPHA_BLACK = new Color(24, 24, 24, 80);

	/**
	 * 
	 */
	private ScreenEssentials() {
		// TODO Auto-generated constructor stub
	}

}
