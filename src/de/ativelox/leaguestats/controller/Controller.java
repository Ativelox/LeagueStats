package de.ativelox.leaguestats.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.ativelox.leaguestats.LeagueStats;
import de.ativelox.leaguestats.constants.EDivision;
import de.ativelox.leaguestats.constants.ERankedQueue;
import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.constants.ETier;
import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import de.ativelox.leaguestats.model.Game;
import de.ativelox.leaguestats.model.TierIcon;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.util.ImageIDMapper;
import de.ativelox.leaguestats.util.Utils;
import de.ativelox.leaguestats.view.Display;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.dto.CurrentGame.Mastery;
import net.rithms.riot.dto.CurrentGame.Participant;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Stats.AggregatedStats;
import net.rithms.riot.dto.Stats.ChampionStats;
import net.rithms.riot.dto.Stats.RankedStats;
import net.rithms.riot.dto.Summoner.Summoner;

/**
 * The controller manages the communication between {@link Game} and
 * {@link Display}.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Controller {

	/**
	 * The name of the process at which to start the game.
	 */
	private static final String PROCESS_NAME = "League of Legends.exe";

	/**
	 * The delay to wait for checking whether the process has started or not.
	 */
	private static final int CHECK_GAME = 5000;

	/**
	 * The delay to wait if fetching game data did not succeed.
	 */
	private static final int FETCH_DELAY = 2000;

	/**
	 * The api given.
	 */
	private final RiotApi api;

	/**
	 * The display for this game to display the stats on.
	 */
	private final Display display;

	/**
	 * The name of the summoner to get the current game from.
	 */
	private Summoner summoner;

	/**
	 * The game for the current summoner name.
	 */
	private Game game;

	/**
	 * The logger used for logging
	 */
	private final ILogger logger;

	/**
	 * Determining whether a game has been found or not.
	 */
	private boolean foundGame;

	/**
	 * Determines whether the game got found by explicitly searching for it.
	 */
	private boolean forcedGameFind;

	/**
	 * Determining whether to stop this controller or not.
	 */
	private boolean stop;

	/**
	 * A list containing all the summoner IDs for the current team.
	 */
	private List<Long> teamSummonerIds;

	/**
	 * The id of the platform on which this client is run.
	 */
	private PlatformId platformID;

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
	 */
	public Controller(final String mSummonerName, final RiotApi mApi) {
		this.summoner = new Summoner();

		try {
			this.summoner = mApi.getSummonerByName(mSummonerName);
		} catch (RiotApiException e) {
			this.logger.logError("An error occured while trying to get the summoner for the following name: "
					+ mSummonerName + ", using dummy...");

		}

		this.forcedGameFind = false;
		this.teamSummonerIds = new LinkedList<>();
		this.api = mApi;
		this.stop = false;
		this.foundGame = false;
		this.logger = LoggerFactory.getLogger();
		this.display = new Display(LeagueStats.TITLE);
		this.display.init();

	}

	/**
	 * Applies all the data fetched to {@link Controller#display} and calls
	 * {@link Display#init()} to show the UI.
	 * 
	 * @see Controller#applyData(ETeamAffiliation)
	 */
	public void applyData() {
		if (this.stop) {
			return;

		}

		this.logger.log("Applying data to the UI...", ELogLevel.INFO);
		applyData(ETeamAffiliation.BLUE);
		applyData(ETeamAffiliation.RED);

		this.logger.log("Starting UI...", ELogLevel.INFO);
		this.display.setVisible(true);
	}

	/**
	 * Waits for the League of Legends process to start
	 * {@link Controller#PROCESS_NAME} and is then able to fetch data for the
	 * given summoner name. Checks the Tasklist every
	 * {@link Controller#CHECK_GAME}
	 * 
	 */
	public void waitForGame() {
		while (!this.foundGame && !this.stop) {
			this.logger.log("Waiting for a game to start...", ELogLevel.INFO);

			try {
				Process process = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");

				try (final BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
					while (true) {
						String line = input.readLine();

						if (line == null) {
							Thread.sleep(CHECK_GAME);
							break;

						}

						if (line.contains(PROCESS_NAME)) {
							this.foundGame = true;
							break;

						}
					}
				} catch (InterruptedException e) {
					this.logger.log("Controller got interrupted while sleeping...", ELogLevel.ERROR);

				}
			} catch (IOException e) {
				this.logger.log("An IO/Exception occured while trying to read from the task list...", ELogLevel.ERROR);

			}
		}

		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			this.logger.log("Controller got interrupted while sleeping...", ELogLevel.ERROR);

		}
	}

	/**
	 * Waits for the League of Legends process to close
	 * {@link Controller#PROCESS_NAME} and is then able to restart its cycle.
	 * Checks the tasklist every {@link Controller#CHECK_WHILE_GAME}
	 * milliseconds.
	 * 
	 */
	public void waitUntilGameFinishes() {

		this.foundGame = false;
		boolean gameRunning = true;

		while (this.foundGame && gameRunning && !this.stop) {
			this.logger.log("Waiting for the game to finish...", ELogLevel.INFO);

			try {
				Process process = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");

				gameRunning = false;

				try (final BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
					while (true) {
						String line = input.readLine();

						if (line == null) {
							Thread.sleep(CHECK_GAME);
							break;

						}

						if (line.contains(PROCESS_NAME)) {
							gameRunning = true;
							Thread.sleep(CHECK_GAME);
							break;

						}
					}
				} catch (InterruptedException e) {
					this.logger.log("Controller got interrupted while sleeping...", ELogLevel.ERROR);

				}
			} catch (IOException e) {
				this.logger.log("An IO/Exception occured while trying to read from the task list...", ELogLevel.ERROR);

			}
		}
	}

	/**
	 * Fetches all the data needed from {@link Controller#game}
	 * 
	 * @see Game#fetchData()
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	public void fetchData() {
		this.game = new Game(this.summoner, this.api);
		this.logger.log("Trying to fetch data from the current game...", ELogLevel.INFO);

		try {
			if (this.stop) {
				return;

			}
			this.game.fetchData(this.platformID);

		} catch (final RiotApiException e) {
			this.logger.logError("An error occured while trying to fetch data from the current game. Retrying in: "
					+ FETCH_DELAY + "ms");

			try {
				Thread.sleep(FETCH_DELAY);

			} catch (InterruptedException e1) {
				this.logger.logError("Controller got interrupted while sleep...");

			}
			this.fetchData();

		}
	}

	/**
	 * Stops this controller.
	 */
	public void stop() {
		this.logger.logInfo("Shutting down...");
		this.display.dispose();
		this.stop = true;

	}

	/**
	 * Starts this controllers cycle. Calls itself repeatedly until
	 * {@link Controller#stop} is <tt>false</tt>.
	 * 
	 * @param mPlatformID
	 *            The ID of the platform on which this processes client is
	 *            running.
	 * 
	 * 
	 * @see Controller#waitForGame()
	 * @see Controller#fetchData()
	 * @see Controller#applyData()
	 * @see Controller#waitUntilGameFinishes()
	 */
	public void start(final PlatformId mPlatformID) {
		this.platformID = mPlatformID;
		
		while (!this.stop) {

			this.logger.logInfo("Starting cycle...");

			if (this.forcedGameFind) {
				this.display.setVisible(true);

			} else {
				this.display.setVisible(false);

			}

			this.display.repaint();

			this.waitForGame();
			this.fetchData();
			this.applyData();
			this.waitUntilGameFinishes();

		}

	}

	/**
	 * Makes the controller to instantly find a game for the given summoner.
	 */
	public void forceGameFinding() {
		this.foundGame = true;
		this.forcedGameFind = true;
	}

	/**
	 * Applies all the data fetched to {@link Controller#display} for a given
	 * {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team for which to apply the data.
	 */
	private void applyData(final ETeamAffiliation mTeam) {
		this.teamSummonerIds = new LinkedList<>();

		final List<Participant> participants = this.game.getParticipants(mTeam);

		for (int i = 0; i < participants.size(); i++) {
			final Participant currentParticipant = participants.get(i);

			this.teamSummonerIds.add(Long.valueOf(currentParticipant.getSummonerId()));

			boolean isPlayer = false;
			if (currentParticipant.getSummonerId() == this.summoner.getId()) {
				isPlayer = true;

			}

			this.display.setSummonerName(mTeam, i + 1, currentParticipant.getSummonerName(), isPlayer);
			this.display.setChampionLoadingArt(mTeam, i + 1,
					ImageIDMapper.CHAMPIONS.get(Long.valueOf(currentParticipant.getChampionId())));

			// gets the keystone of the current participant
			for (Mastery m : currentParticipant.getMasteries()) {
				if (Utils.isKeystone(m.getMasteryId())) {
					this.display.setKeystoneImage(mTeam, i + 1,
							ImageIDMapper.MASTERIES.get(Long.valueOf(m.getMasteryId())));
				}
			}
			RankedStats stats = null;

			try {
				stats = this.api.getRankedStats(currentParticipant.getSummonerId());

			} catch (final RiotApiException e) {
				this.logger.logError("Error while trying to get the ranked stats for the following summoner: "
						+ currentParticipant.getSummonerName());

			}

			if (stats == null) {
				continue;

			}

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
		}

		Map<String, List<League>> leagueMapping = null;
		try {
			leagueMapping = this.api.getLeagueEntryBySummoners(this.teamSummonerIds.get(0).longValue(),
					this.teamSummonerIds.get(1).longValue(), this.teamSummonerIds.get(2).longValue(),
					this.teamSummonerIds.get(3).longValue(), this.teamSummonerIds.get(4).longValue());

		} catch (final RiotApiException e) {
			this.logger.logError("Couldn't get the division/tier data for the following team: " + mTeam.toString());
			this.logger.logInfo("Trying to get the division/tier data separately...");

			for (int i = 0; i < participants.size(); i++) {
				Participant currentParticipant = participants.get(i);

				List<League> league = null;

				try {
					league = this.api.getLeagueEntryBySummoner(currentParticipant.getSummonerId());

				} catch (RiotApiException e1) {
					this.logger.logError("Couldn't get the division/tier data for the following summoner: "
							+ currentParticipant.getSummonerName());

				}

				this.display.setFlexDivisionTierImage(mTeam, i + 1, Assets.PROVISIONAL);
				this.display.setSoloDivisionTierImage(mTeam, i + 1, Assets.PROVISIONAL);

				if (league == null) {
					continue;

				}

				for (final League leagueEntry : league) {
					if (leagueEntry.getQueue().equals(ERankedQueue.RANKED_SOLO_5x5.toString())) {
						this.display.setSoloDivisionTierImage(mTeam, i + 1,
								TierIcon.getTierIcon(ETier.valueOf(leagueEntry.getTier()),
										EDivision.valueOf(leagueEntry.getEntries().get(0).getDivision())));

					}

					if (leagueEntry.getQueue().equals(ERankedQueue.RANKED_FLEX_SR.toString())) {
						this.display.setFlexDivisionTierImage(mTeam, i + 1,
								TierIcon.getTierIcon(ETier.valueOf(leagueEntry.getTier()),
										EDivision.valueOf(leagueEntry.getEntries().get(0).getDivision())));

					}
				}
			}
		}

		if (leagueMapping == null) {
			return;

		}

		for (final Entry<String, List<League>> leagueByID : leagueMapping.entrySet()) {
			setDivisionTierIcon(mTeam, leagueByID.getKey(), leagueByID.getValue());

		}
	}

	/**
	 * Sets the division tier icon for a given team by their ID and their name.
	 * 
	 * @param mTeam
	 *            The team affiliation of this summoner.
	 * 
	 * @param mSummonerID
	 *            The id of this summoner as string.
	 * 
	 * @param mLeague
	 *            The league object of this summoner.
	 */
	private void setDivisionTierIcon(final ETeamAffiliation mTeam, final String mSummonerID,
			final List<League> mLeague) {

		int playerNumber = 0;

		if (mSummonerID.equals(this.teamSummonerIds.get(0).toString())) {
			playerNumber = 1;

		} else if (mSummonerID.equals(this.teamSummonerIds.get(1).toString())) {
			playerNumber = 2;

		} else if (mSummonerID.equals(this.teamSummonerIds.get(2).toString())) {
			playerNumber = 3;

		} else if (mSummonerID.equals(this.teamSummonerIds.get(3).toString())) {
			playerNumber = 4;

		} else if (mSummonerID.equals(this.teamSummonerIds.get(4).toString())) {
			playerNumber = 5;

		}

		this.display.setFlexDivisionTierImage(mTeam, playerNumber, Assets.PROVISIONAL);
		this.display.setSoloDivisionTierImage(mTeam, playerNumber, Assets.PROVISIONAL);

		for (final League league : mLeague) {
			if (league.getQueue().equals(ERankedQueue.RANKED_SOLO_5x5.toString())) {
				this.display.setSoloDivisionTierImage(mTeam, playerNumber, TierIcon.getTierIcon(
						ETier.valueOf(league.getTier()), EDivision.valueOf(league.getEntries().get(0).getDivision())));

			}

			if (league.getQueue().equals(ERankedQueue.RANKED_FLEX_SR.toString())) {
				this.display.setFlexDivisionTierImage(mTeam, playerNumber, TierIcon.getTierIcon(
						ETier.valueOf(league.getTier()), EDivision.valueOf(league.getEntries().get(0).getDivision())));

			}
		}
	}
}
