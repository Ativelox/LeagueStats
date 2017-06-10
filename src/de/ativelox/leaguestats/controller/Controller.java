package de.ativelox.leaguestats.controller;

import java.util.List;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.constants.EDivision;
import de.ativelox.leaguestats.constants.ERankedQueue;
import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.constants.ETier;
import de.ativelox.leaguestats.model.Game;
import de.ativelox.leaguestats.model.TierIcon;
import de.ativelox.leaguestats.settings.SettingsProvider;
import de.ativelox.leaguestats.util.ImageIDMapper;
import de.ativelox.leaguestats.util.RankedQueueIDMapper;
import de.ativelox.leaguestats.util.Utils;
import de.ativelox.leaguestats.view.Display;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.CurrentGame.Mastery;
import net.rithms.riot.dto.CurrentGame.Participant;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Stats.AggregatedStats;
import net.rithms.riot.dto.Stats.ChampionStats;
import net.rithms.riot.dto.Stats.RankedStats;

/**
 * The controller manages the communication between {@link Game} and
 * {@link Display}.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Controller {

	/**
	 * The api given.
	 */
	private final RiotApi api;

	/**
	 * The display for this game to display the stats on.
	 */
	private final Display display;

	/**
	 * The game for the current summoner name.
	 */
	private final Game game;

	/**
	 * Initializes a new {@link Controller}. Creates a new
	 * {@link Game#Game(net.rithms.riot.dto.Summoner.Summoner, RiotApi)} and a
	 * new {@link Display#Display(String)}.
	 * 
	 * @param mSummonerName
	 *            The summoner name of which to get the current ingame status.
	 * 
	 * @param mApi
	 *            The api to use throughout this application.
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 * 
	 */
	public Controller(final String mSummonerName, final RiotApi mApi) throws RiotApiException {
		this.api = mApi;
		this.game = new Game(mApi.getSummonerByName(mSummonerName), mApi);
		this.display = new Display(LeagueStats.TITLE);

	}

	/**
	 * Applies all the data fetched to {@link Controller#display} and calls
	 * {@link Display#init()} to show the UI.
	 * 
	 * @see Controller#applyData(ETeamAffiliation)
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	public void applyData() throws RiotApiException {
		applyData(ETeamAffiliation.BLUE);
		applyData(ETeamAffiliation.RED);

		this.display.init();
	}

	/**
	 * Fetches all the data needed from {@link Controller#game}
	 * 
	 * @see Game#fetchData()
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	public void fetchData() throws RiotApiException {
		this.game.fetchData();

	}

	/**
	 * Applies all the data fetched to {@link Controller#display} for a given
	 * {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to apply the data.
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	private void applyData(final ETeamAffiliation mTeam) throws RiotApiException {

		final List<Participant> participants = this.game.getParticipants(mTeam);

		for (int i = 0; i < participants.size(); i++) {
			final Participant currentParticipant = participants.get(i);

			this.display.setSummonerName(mTeam, i + 1, currentParticipant.getSummonerName());
			this.display.setChampionLoadingArt(mTeam, i + 1,
					ImageIDMapper.CHAMPIONS.get(Long.valueOf(currentParticipant.getChampionId())));

			// gets the keystone of the current participant
			for (Mastery m : currentParticipant.getMasteries()) {
				if (Utils.isKeystone(m.getMasteryId())) {
					this.display.setKeystoneImage(mTeam, i + 1,
							ImageIDMapper.MASTERIES.get(Long.valueOf(m.getMasteryId())));
				}
			}

			// here goes the code which is GET heavy for the API, so the rate
			// limits doesn't get exceeded.
			if (SettingsProvider.IS_DEMO) {
				continue;

			}

			final RankedStats stats = this.api.getRankedStats(currentParticipant.getSummonerId());

			// gets the K/D/A and the Winrate of the current participant
			for (final ChampionStats champStats : stats.getChampions()) {
				if (champStats.getId() == currentParticipant.getChampionId()) {
					final AggregatedStats aggStats = champStats.getStats();

					final long sum = aggStats.getTotalSessionsPlayed();
					final long wins = aggStats.getTotalSessionsWon();
					final long losses = aggStats.getTotalSessionsLost();

					if (sum <= 0) {
						this.display.setWinrate(mTeam, i + 1, "0W/0L/0%");
						this.display.setKDA(mTeam, i + 1, "0/0/0");
						break;
					}

					final float avgKills = (float) aggStats.getTotalChampionKills() / sum;
					final float avgDeaths = (float) aggStats.getTotalDeathsPerSession() / sum;
					final float avgAssists = (float) aggStats.getTotalAssists() / sum;

					final String winrate = Math.round(((float) wins / sum) * 100f) + "%";
					this.display.setWinrate(mTeam, i + 1, wins + "W/" + losses + "L/" + winrate);
					this.display.setKDA(mTeam, i + 1, String.format("%.1f/%.1f/%.1f", Float.valueOf(avgKills),
							Float.valueOf(avgDeaths), Float.valueOf(avgAssists)));

					break;
				}
			}

			final List<League> currentLeague = this.api.getLeagueEntryBySummoner(currentParticipant.getSummonerId());

			// gets the division/tier of the current participant
			for (final League league : currentLeague) {
				final ERankedQueue currentQueue = RankedQueueIDMapper.QUEUE
						.get(Integer.valueOf(this.game.getQueueID()));

				if (league.getQueue().equals(currentQueue.toString())) {
					this.display.setDivisionTierImage(mTeam, i + 1,
							TierIcon.getTierIcon(ETier.valueOf(league.getTier()),
									EDivision.valueOf(league.getEntries().get(0).getDivision())));

				}
			}

			// do not exceed the rate limit if wanted to get all the data.
			if (!SettingsProvider.IS_DEMO) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
