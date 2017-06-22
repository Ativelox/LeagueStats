package de.ativelox.leaguestats;

import java.awt.AWTException;

import de.ativelox.leaguestats.controller.Controller;
import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import de.ativelox.leaguestats.logging.LoggerUtil;
import de.ativelox.leaguestats.settings.SettingsProvider;
import de.ativelox.leaguestats.timer.RateLimitTimer;
import de.ativelox.leaguestats.tray.TrayManager;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ImageIDMapper;
import de.ativelox.leaguestats.util.RankedQueueIDMapper;
import de.ativelox.leaguestats.view.logging.DebugFrame;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;

/**
 * The main class of this Project. Creates a new
 * {@link Controller#Controller(String, RiotApi)} which handles further
 * requests.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 * @see LeagueStats#init()
 */
public final class LeagueStats {

	/**
	 * The title of this Frame.
	 */
	public static final String TITLE = "LeagueStats";

	/**
	 * The main method. Calls {@link LeagueStats#LeagueStats()}.
	 * 
	 * @param args
	 *            The command-line arguments (not supported).
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new LeagueStats();

	}

	/**
	 * The api used for GET-requests.
	 */
	private RiotApi api;

	/**
	 * The controller which enables communication between logic and display.
	 */
	private Controller controller;

	/**
	 * The debug frame onto which logs get printed.
	 */
	private DebugFrame debugFrame;

	/**
	 * The system tray used for this application.
	 */
	private TrayManager trayManager;

	/**
	 * The settings provider used to load/save settings.
	 */
	private SettingsProvider provider;

	/**
	 * The logger used for logging.
	 */
	private ILogger logger;

	/**
	 * The timer used to check if any more GET-requests can be issued or not.
	 */
	private RateLimitTimer timer;

	/**
	 * Initializes this {@link LeagueStats} object. Furthermore calls
	 * {@link ImageIDMapper#init(RiotApi)}, {@link Assets#init()},
	 * {@link RankedQueueIDMapper#init()} so every data is accessible before
	 * applying assets to the interface. Also creates a new
	 * {@link Controller#Controller(String, RiotApi)} with the settings provided
	 * by the config.ini.
	 * 
	 * @see Controller#fetchData(PlatformId)
	 * @see Controller#applyData()
	 */
	public LeagueStats() {
		this.init();

	}

	/**
	 * Initializes this object by readying all the assets needed and creating a
	 * new {@link Controller#Controller(String, RiotApi)} which serves as
	 * communication between the {@link Game} and the {@link Display}.
	 * 
	 * @see Controller#fetchData(PlatformId)
	 * @see Controller#applyData()
	 */
	public void init() {
		this.debugFrame = new DebugFrame();
		LoggerFactory.setDebugFrame(this.debugFrame);
		this.logger = LoggerFactory.getLogger();

		this.logger.log("Starting Application...", ELogLevel.INFO);

		this.provider = new SettingsProvider();
		this.provider.initialize();

		this.timer = new RateLimitTimer();

		this.api = new LimitedRiotApi(this.timer, this.provider.getApiKey());
		this.api.setRegion(this.provider.getRegion());

		try {
			ImageIDMapper.init(this.api);
			Assets.init();
			RankedQueueIDMapper.init();

			this.trayManager = new TrayManager(this, Assets.TRAY_ICON);
			this.trayManager.addTrayIcon();

			this.controller = new Controller(this.provider.getSummonerName(), this.api);
			this.controller.start(PlatformId.valueOf(this.provider.getRegion().toString().toUpperCase()));

		} catch (RiotApiException | AWTException e) {
			this.logger.logError(LoggerUtil.getStackTrace(e));

		}
	}

	/**
	 * Stops this application by stopping every thread in a safe way.
	 */
	public void stop() {
		this.controller.stop();
		this.debugFrame.dispose();
		this.trayManager.removeTrayIcon();
		this.timer.cancel();

	}

	/**
	 * Gets the frame used for debugging.
	 * 
	 * @return The frame mentioned.
	 */
	public DebugFrame getDebugFrame() {
		return this.debugFrame;

	}

	/**
	 * Gets the controller of this application.
	 * 
	 * @return The controller mentioned.
	 */
	public Controller getController() {
		return this.controller;

	}

	/**
	 * Removes the tray icon associated with this application.
	 * 
	 */
	public void removeTrayIcon() {
		this.trayManager.removeTrayIcon();
	}

	/**
	 * Returns the settings provider used for this application.
	 * 
	 * @return The settings provider mentioned.
	 */
	public SettingsProvider getSettingsProvider() {
		return this.provider;

	}
}
