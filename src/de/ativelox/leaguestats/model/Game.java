package de.ativelox.leaguestats.model;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.ativelox.leaguestats.constants.EImageStyle;
import de.ativelox.leaguestats.constants.EImageType;
import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.exceptions.InvalidURLException;
import de.ativelox.leaguestats.exceptions.UnsupportedImageStyleException;
import de.ativelox.leaguestats.util.DDragon;
import de.ativelox.leaguestats.util.Utils;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.dto.CurrentGame.BannedChampion;
import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;
import net.rithms.riot.dto.CurrentGame.Participant;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Summoner.Summoner;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class Game {

	private final RiotApi api;
	private final Summoner summoner;

	private long id;
	private String gameMode;
	private String gameType;
	private int queueID;
	private List<BannedChampion> bannedChampions;
	private List<Participant> participants;

	/**
	 * 
	 */
	public Game(final Summoner mSummoner, final RiotApi mApi) {
		this.api = mApi;
		this.summoner = mSummoner;

	}

	public void fetchData() throws RiotApiException {
		final CurrentGameInfo currentGame = this.api.getCurrentGameInfo(PlatformId.EUW, this.summoner.getId());

		this.participants = currentGame.getParticipants();
		this.bannedChampions = currentGame.getBannedChampions();
		this.id = currentGame.getGameId();
		this.gameMode = currentGame.getGameMode();
		this.gameType = currentGame.getGameType();
		this.queueID = (int) currentGame.getGameQueueConfigId();

	}

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
	 * This method should not be used, since its extremely GET heavy. Calls 6
	 * GET requests, since there are 6 champs banned.
	 * 
	 * @param mTeam
	 * @return
	 * @throws RiotApiException
	 * 
	 */
	@Deprecated
	public Map<ChampionContainer, Integer> getBannedChampions(final ETeamAffiliation mTeam) throws RiotApiException {

		final Map<ChampionContainer, Integer> teamBannedChamps = new HashMap<>();

		for (final BannedChampion bannedChampion : this.bannedChampions) {
			final ETeamAffiliation currentAffiliation = Utils.getTeamAffiliationByID(bannedChampion.getTeamId());

			if (!(mTeam == currentAffiliation)) {
				continue;

			}
			final int championID = (int) bannedChampion.getChampionId();
			final Champion champion = this.api.getDataChampion(championID);

			Image image = null;

			try {
				image = DDragon.getImage(EImageType.CHAMPION, EImageStyle.SQUARE, champion.getName());

			} catch (UnsupportedImageStyleException | InvalidURLException | IOException e) {
				// TODO: error handling, set default picture if an error
				// occured.
				// TODO: Remove Debug Print!
				System.out.println("Couldn't fetch image for the following champion: " + champion.getName());

			}
			teamBannedChamps.put(new ChampionContainer(champion.getName(), image),
					Integer.valueOf(bannedChampion.getPickTurn()));

		}
		return teamBannedChamps;

	}

	public String getMode() {
		return this.gameMode;

	}

	public String getType() {
		return this.gameType;

	}

	public long getID() {
		return this.id;

	}

	public int getQueueID() {
		return this.queueID;

	}

}
