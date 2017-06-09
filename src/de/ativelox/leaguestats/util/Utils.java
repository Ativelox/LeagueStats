package de.ativelox.leaguestats.util;

import de.ativelox.leaguestats.constants.KeystoneID;
import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.exceptions.InvalidTeamIdException;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Utils {

	public static ETeamAffiliation getTeamAffiliationByID(final long mTeamID) {
		if (mTeamID == 100) {
			return ETeamAffiliation.BLUE;

		} else if (mTeamID == 200) {
			return ETeamAffiliation.RED;

		}
		throw new InvalidTeamIdException();

	}

	public static boolean isKeystone(final long mKeystoneID) {

		if (mKeystoneID == KeystoneID.COURAGE_OF_THE_COLOSSUS || mKeystoneID == KeystoneID.DEATHFIRE_TOUCH
				|| mKeystoneID == KeystoneID.FERVOR_OF_BATTLE || mKeystoneID == KeystoneID.GRASP_OF_THE_UNDYING
				|| mKeystoneID == KeystoneID.STONEBORN_PACT || mKeystoneID == KeystoneID.STORMRAIDERS_SURGE
				|| mKeystoneID == KeystoneID.THUNDERLORDS_DECREE || mKeystoneID == KeystoneID.WARLORDS_BLOODLUST
				|| mKeystoneID == KeystoneID.WINDSPEAKERS_BLESSING) {
			return true;

		}
		return false;

	}

	/**
	 * 
	 */
	private Utils() {
		// TODO Auto-generated constructor stub
	}

}
