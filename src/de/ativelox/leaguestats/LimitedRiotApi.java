package de.ativelox.leaguestats;

import java.util.List;

import de.ativelox.leaguestats.timer.RateLimitTimer;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Stats.RankedStats;
import net.rithms.riot.dto.Summoner.Summoner;

/**
 * Modified version for {@link RiotApi} which checks for some GET-requests if
 * any more can even be issued, since there is a rate limit at 10 requests per
 * 10 seconds.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class LimitedRiotApi extends RiotApi {

	/**
	 * The timer used to check if GET-requests can be issued or not.
	 */
	private final RateLimitTimer timer;

	/**
	 * The delay when the GET-request got declined to re-issue the same request.
	 */
	private static final int DELAY = 500;

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi()
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer) {
		super();
		mTimer.getRequest();
		this.timer = mTimer;

	}

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi(Region)
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer, final Region mRegion) {
		super(mRegion);
		mTimer.getRequest();
		this.timer = mTimer;
	}

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi(String)
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer, final String mKey) {
		super(mKey);
		mTimer.getRequest();
		this.timer = mTimer;
	}

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi(String, Region)
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer, final String mKey, final Region mRegion) {
		super(mKey, mRegion);
		mTimer.getRequest();
		this.timer = mTimer;
	}

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi(String, String)
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer, final String mKey, final String mTournamentKey) {
		super(mKey, mTournamentKey);
		mTimer.getRequest();
		this.timer = mTimer;
	}

	/**
	 * Creates a new RiotApi which blocks GET-requests if no more can be sent
	 * until the rate limit gets reset.
	 * 
	 * @param mTimer
	 *            The timer to use to check if GET-requests can be issued or
	 *            not.
	 * 
	 * @see RiotApi#RiotApi(String, String, Region)
	 */
	public LimitedRiotApi(final RateLimitTimer mTimer, final String mKey, final String mTournamentKey,
			final Region mRegion) {
		super(mKey, mTournamentKey, mRegion);
		mTimer.getRequest();
		this.timer = mTimer;
	}

	@Override
	public Summoner getSummonerByName(final String summonerName) throws RiotApiException {
		while(this.timer.isBlocked()){
			try {
				Thread.sleep(DELAY);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		this.timer.getRequest();
		return super.getSummonerByName(summonerName);

	}

	@Override
	public RankedStats getRankedStats(long summonerId) throws RiotApiException {
		while(this.timer.isBlocked()){
			try {
				Thread.sleep(DELAY);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		this.timer.getRequest();
		return super.getRankedStats(summonerId);
	}

	@Override
	public List<League> getLeagueEntryBySummoner(long summonerId) throws RiotApiException {
		while(this.timer.isBlocked()){
			try {
				Thread.sleep(DELAY);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		this.timer.getRequest();
		return super.getLeagueEntryBySummoner(summonerId);

	}

	@Override
	public CurrentGameInfo getCurrentGameInfo(PlatformId platformId, long summonerId) throws RiotApiException {
		while(this.timer.isBlocked()){
			try {
				Thread.sleep(DELAY);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		this.timer.getRequest();
		return super.getCurrentGameInfo(platformId, summonerId);
	}

}
