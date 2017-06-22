package de.ativelox.leaguestats.util;

import de.ativelox.leaguestats.constants.KeystoneID;

import javax.swing.JOptionPane;

import de.ativelox.leaguestats.constants.ETeamAffiliation;
import de.ativelox.leaguestats.exceptions.InvalidTeamIdException;
import net.rithms.riot.constant.Region;

/**
 * Provides Utility methods used throughout this method.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Utils {

	/**
	 * Gets the {@link ETeamAffiliation} for a given teamID.
	 * 
	 * @param mTeamID
	 *            The teamID of which to get the TeamAffiliation from.
	 * 
	 * @return The TeamAffiliation for the given teamID.
	 * 
	 * @throws InvalidTeamIdException
	 *             if the given teamID wasn't valid.
	 */
	public static ETeamAffiliation getTeamAffiliationByID(final long mTeamID) {
		if (mTeamID == 100) {
			return ETeamAffiliation.BLUE;

		} else if (mTeamID == 200) {
			return ETeamAffiliation.RED;

		}
		throw new InvalidTeamIdException();

	}

	/**
	 * Returns whether the given masteryID is a keystone or not.
	 * 
	 * @param mMasteryID
	 *            The masteryID of which to determine the keystone status.
	 * 
	 * @return <tt>true</tt> if the given masteryID was a keystone,
	 *         <tt>false</tt> otherwise.
	 */
	public static boolean isKeystone(final long mMasteryID) {

		if (mMasteryID == KeystoneID.COURAGE_OF_THE_COLOSSUS || mMasteryID == KeystoneID.DEATHFIRE_TOUCH
				|| mMasteryID == KeystoneID.FERVOR_OF_BATTLE || mMasteryID == KeystoneID.GRASP_OF_THE_UNDYING
				|| mMasteryID == KeystoneID.STONEBORN_PACT || mMasteryID == KeystoneID.STORMRAIDERS_SURGE
				|| mMasteryID == KeystoneID.THUNDERLORDS_DECREE || mMasteryID == KeystoneID.WARLORDS_BLOODLUST
				|| mMasteryID == KeystoneID.WINDSPEAKERS_BLESSING) {
			return true;

		}
		return false;

	}

	/**
	 * Opens a new set api key popup and returns the value inputted.
	 * 
	 * @return the mentioned value.
	 */
	public static String setApiKeyPopup() {
		return JOptionPane.showInputDialog("Enter your API-Key.");

	}

	/**
	 * Opens a new set summoner name popup and returns the value inputted.
	 * 
	 * @return the mentioned value.
	 */
	public static String setSummonerNamePopup() {
		return JOptionPane.showInputDialog("Enter your Summonername.");
	}

	/**
	 * Opens a new set region popup and retuuns the value selected.
	 * 
	 * @return the mentioned value.
	 */
	public static Region setRegionPopup() {
		return Region.valueOf((String) JOptionPane.showInputDialog(null, "Select your region.", "RegionSelection",
				JOptionPane.INFORMATION_MESSAGE, null,
				new Object[] { Region.EUW.toString().toUpperCase(), Region.EUNE.toString().toUpperCase(),
						Region.BR.toString().toUpperCase(), Region.KR.toString().toUpperCase(),
						Region.LAN.toString().toUpperCase(), Region.LAS.toString().toUpperCase(),
						Region.NA.toString().toUpperCase(), Region.OCE.toString().toUpperCase(),
						Region.RU.toString().toUpperCase(), Region.TR.toString().toUpperCase() },
				Region.EUW.toString().toUpperCase()));
	}

	/**
	 * Utility class, not initialization needed.
	 */
	private Utils() {

	}

}
