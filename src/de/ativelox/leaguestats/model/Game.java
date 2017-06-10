package de.ativelox.leaguestats.model;

import java.util.LinkedList;
import java.util.List;

import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.util.Utils;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;
import net.rithms.riot.dto.CurrentGame.Participant;
import net.rithms.riot.dto.Summoner.Summoner;

/**
 * The current game for the given summoner. Is able to {@link Game#fetchData()}
 * from {@link Game#api} and provides GETTERs for every needed information.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Game {

	/**
	 * The api used throughout for GET-requests.
	 */
	private final RiotApi api;

	/**
	 * The mode of this game.
	 */
	private String gameMode;

	/**
	 * The type of this game.
	 */
	private String gameType;

	/**
	 * The id of this game.
	 */
	private long id;

	/**
	 * A List containing all the participants of this game, e.g. all the
	 * summoners participating in this game.
	 */
	private List<Participant> participants;

	/**
	 * The queue id of this game.
	 */
	private int queueID;

	/**
	 * The summoner participating in the current match.
	 */
	private final Summoner summoner;

	/**
	 * Creates a new Game for a given summoner. Is able to
	 * {@link Game#fetchData()} from {@link Game#api} and provides GETTERs for
	 * every needed information.
	 * 
	 * @param mSummoner
	 *            The summoner of which to get the data of the current game
	 *            from.
	 * 
	 * @param mApi
	 *            The api used for GET-requests.
	 */
	public Game(final Summoner mSummoner, final RiotApi mApi) {
		this.api = mApi;
		this.summoner = mSummoner;

	}

	/**
	 * Fetches all the data from Riots API provided for the current game.
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 * 
	 * @see RiotApi#getCurrentGameInfo(PlatformId, long)
	 */
	public void fetchData() throws RiotApiException {
		final CurrentGameInfo currentGame = this.api.getCurrentGameInfo(PlatformId.EUW, this.summoner.getId());

		this.participants = currentGame.getParticipants();
		this.id = currentGame.getGameId();
		this.gameMode = currentGame.getGameMode();
		this.gameType = currentGame.getGameType();
		this.queueID = (int) currentGame.getGameQueueConfigId();

	}

	/**
	 * Gets the ID of this game.
	 * 
	 * @return the ID mentioned.
	 */
	public long getID() {
		return this.id;

	}

	/**
	 * Gets the mode of this game.
	 * 
	 * @return the mode mentioned.
	 */
	public String getMode() {
		return this.gameMode;

	}

	/**
	 * Gets all the participants of this game for a given
	 * {@link ETeamAffiliation}.
	 * 
	 * @param mTeam
	 *            The Team of which to get the participants from.
	 * 
	 * @return A list containing all the participants for the given Team.
	 */
	public List<Participant> getParticipants(final ETeamAffiliation mTeam) {
		final List<Participant> resultParticipants = new LinkedList<>();

		for (final Participant participant : this.participants) {
			final ETeamAffiliation teamAffiliation = Utils.getTeamAffiliationByID(participant.getTeamId());

			if (!(teamAffiliation == mTeam)) {
				continue;

			}
			resultParticipants.add(participant);

		}
		return resultParticipants;

	}

	/**
	 * Gets the queue ID of this game.
	 * 
	 * @return the queue ID mentioned.
	 */
	public int getQueueID() {
		return this.queueID;

	}

	/**
	 * Gets the type of this game.
	 * 
	 * @return the type mentioned.
	 */
	public String getType() {
		return this.gameType;

	}

}
