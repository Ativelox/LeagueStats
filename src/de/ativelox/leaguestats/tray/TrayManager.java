package de.ativelox.leaguestats.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.exceptions.UnsupportedSystemTrayException;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import de.ativelox.leaguestats.tray.listener.ExitListener;
import de.ativelox.leaguestats.tray.listener.FindMatchListener;
import de.ativelox.leaguestats.tray.listener.ResetCacheListener;
import de.ativelox.leaguestats.tray.listener.SetApiKeyListener;
import de.ativelox.leaguestats.tray.listener.SetRegionListener;
import de.ativelox.leaguestats.tray.listener.SetSummonerNameListener;
import de.ativelox.leaguestats.tray.listener.ToggleDebugWindowListener;

/**
 * Handles the tray icon for this application.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class TrayManager {

	private static final String FORCE_CACHE_UPDATE_NAME = "Force Cache Update";
	private static final String SET_API_KEY_NAME = "Set API Key";
	private static final String SET_SUMMONER_NAME = "Set SummonerName";
	private static final String SET_REGION_NAME = "Set Region";
	private static final String TOGGLE_DEBUG_WINDOW_NAME = "Toggle Debug Window";
	private static final String FIND_MATCH_NAME = "Find Match";
	private static final String EXIT = "Exit";

	private static final String TRAY_NAME = LeagueStats.TITLE;

	private final ILogger logger;

	private final LeagueStats leagueStats;

	private SystemTray systemTray;

	private TrayIcon trayIcon;

	private final Image trayIconImage;

	/**
	 * Creates a new Tray manager object, which is able to
	 * {@link TrayManager#initialize()} itself, after that someone needs to call
	 * {@link TrayManager#addTrayIcon()} to add the Icon to the Systems tray or
	 * {@link TrayManager#removeTrayIcon()} to remove it accordingly.
	 * 
	 * @param mParent
	 *            The parent service to use for context menu callbacks
	 * 
	 * @param mTrayIconImage
	 *            The image of the try icon which is managed by this object
	 */
	public TrayManager(final LeagueStats mParent, final Image mTrayIconImage) {
		this.leagueStats = mParent;
		this.trayIconImage = mTrayIconImage;
		this.logger = LoggerFactory.getLogger();

		this.initialize();
	}

	/**
	 * Adds the tray icon of the service to the systems tray.
	 *
	 * @throws AWTException
	 *             If the desktop tray is missing
	 */
	public final void addTrayIcon() throws AWTException {
		this.logger.logInfo("Adding Tray Icon to the System Tray...");

		this.systemTray.add(this.trayIcon);
	}

	/**
	 * Removes the tray icon of the service from the systems tray.
	 */
	public final void removeTrayIcon() {
		this.logger.logInfo("Removing Tray Icon from the System Tray...");

		this.systemTray.remove(this.trayIcon);
	}

	/**
	 * Initializes this tray manager object and creates the tray icon and its
	 * context menu.
	 * 
	 * @throws UnsupportedSystemTrayException
	 *             If the current platform does not support a system tray.
	 */
	private final void initialize() throws UnsupportedSystemTrayException {
		this.logger.logInfo("Initializing TrayManager.");

		if (!SystemTray.isSupported()) {
			throw new UnsupportedSystemTrayException();

		}

		this.systemTray = SystemTray.getSystemTray();

		this.trayIcon = new TrayIcon(this.trayIconImage, TRAY_NAME);
		this.trayIcon.setImageAutoSize(true);

		final MenuItem forceCacheUpdate = new MenuItem(FORCE_CACHE_UPDATE_NAME);
		final MenuItem toggleDebugWindow = new MenuItem(TOGGLE_DEBUG_WINDOW_NAME);
		final MenuItem setApiKey = new MenuItem(SET_API_KEY_NAME);
		final MenuItem setRegion = new MenuItem(SET_REGION_NAME);
		final MenuItem setSummonername = new MenuItem(SET_SUMMONER_NAME);
		final MenuItem exit = new MenuItem(EXIT);
		final MenuItem findMatch = new MenuItem(FIND_MATCH_NAME);

		final PopupMenu popup = new PopupMenu();
		popup.add(forceCacheUpdate);
		popup.add(toggleDebugWindow);
		popup.addSeparator();
		popup.add(setApiKey);
		popup.add(setRegion);
		popup.add(setSummonername);
		popup.addSeparator();
		popup.add(findMatch);
		popup.add(exit);
		this.trayIcon.setPopupMenu(popup);
		
		exit.addActionListener(new ExitListener(this.leagueStats));
		toggleDebugWindow.addActionListener(new ToggleDebugWindowListener(this.leagueStats));
		setApiKey.addActionListener(new SetApiKeyListener(this.leagueStats));
		setRegion.addActionListener(new SetRegionListener(this.leagueStats));
		setSummonername.addActionListener(new SetSummonerNameListener(this.leagueStats));
		forceCacheUpdate.addActionListener(new ResetCacheListener(this.leagueStats));
		findMatch.addActionListener(new FindMatchListener(this.leagueStats));

	}
}
