package de.ativelox.leaguestats;

import de.ativelox.leaguestats.controller.Controller;
import de.ativelox.leaguestats.settings.SettingsProvider;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ImageIDMapper;
import de.ativelox.leaguestats.util.RankedQueueIDMapper;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;

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
	 * Initializes this {@link LeagueStats} object. Furthermore calls
	 * {@link ImageIDMapper#init(RiotApi)}, {@link Assets#init()},
	 * {@link RankedQueueIDMapper#init()} so every data is accessible before
	 * applying assets to the interface. Also creates a new
	 * {@link Controller#Controller(String, RiotApi)} with the settings provided
	 * by the config.ini.
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 * 
	 * @see Controller#fetchData()
	 * @see Controller#applyData()
	 */
	public static void init() throws RiotApiException {
		final SettingsProvider provider = new SettingsProvider();
		provider.initialize();

		final RiotApi api = new RiotApi(provider.getApiKey());
		api.setRegion(provider.getRegion());

		ImageIDMapper.init(api);
		Assets.init();
		RankedQueueIDMapper.init();

		final Controller controller = new Controller(provider.getSummonerName(), api);
		controller.fetchData();
		controller.applyData();
	}

	/**
	 * The main method. Calls {@link LeagueStats#init()}.
	 * 
	 * @param args
	 *            The command-line arguments (not supported).
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	public static void main(String[] args) throws RiotApiException {
		LeagueStats.init();

	}

	/**
	 * No initialization needed.
	 */
	private LeagueStats() {

	}
}
