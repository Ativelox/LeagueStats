package de.ativelox.leaguestats;

import de.ativelox.leaguestats.controller.Controller;
import de.ativelox.leaguestats.settings.SettingsProvider;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ImageIDMapper;
import de.ativelox.leaguestats.util.RankedQueueIDMapper;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class LeagueStats {

	public static final String TITLE = "LeagueStats";

	public static void main(String[] args) throws RiotApiException {
		LeagueStats.init();
	}

	/**
	 * @throws RiotApiException
	 * 
	 */
	public LeagueStats() {

	}

	public static void init() throws RiotApiException {
		final SettingsProvider provider = new SettingsProvider();
		provider.initialize();

		final RiotApi api = new RiotApi(provider.getApiKey());
		api.setRegion(provider.getRegion());

		ImageIDMapper.init(api);
		Assets.init();
		RankedQueueIDMapper.init();

		Controller controller = new Controller(provider.getSummonerName(), api);
		controller.fetchData();
		controller.applyData();
	}
}
