package de.ativelox.leaguestats.model;

import java.awt.Image;

import de.ativelox.leaguestats.constants.ETier;
import de.ativelox.leaguestats.util.Assets;
import de.ativelox.leaguestats.constants.EDivision;

/**
 * Provides a Wrapper for {@link Image} and {@link String}, where every
 * {@link TierIcon} has a Tier and a Division represented by its Image and a
 * description of its Tier and Division.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 * @see ETier
 * @see EDivision
 */
public final class TierIcon {

	/**
	 * Gets the TierIcon by providing a {@link ETier} and a {@link EDivision}.
	 * Every image is explicitly defined by its Tier and Division.
	 * 
	 * @param mTier
	 *            The Tier of the TierIcon to get.
	 * @param mDivision
	 *            The Division of the TierIcon to get.
	 * 
	 * @return The TierIcon corresponding to the given Tier and Division.
	 */
	public static TierIcon getTierIcon(final ETier mTier, final EDivision mDivision) {
		if (mTier == ETier.BRONZE) {
			if (mDivision == EDivision.I) {
				return Assets.BRONZE_I;

			} else if (mDivision == EDivision.II) {
				return Assets.BRONZE_II;

			} else if (mDivision == EDivision.III) {
				return Assets.BRONZE_III;

			} else if (mDivision == EDivision.IV) {
				return Assets.BRONZE_IV;

			} else if (mDivision == EDivision.V) {
				return Assets.BRONZE_V;

			}

		} else if (mTier == ETier.SILVER) {
			if (mDivision == EDivision.I) {
				return Assets.SILVER_I;

			} else if (mDivision == EDivision.II) {
				return Assets.SILVER_II;

			} else if (mDivision == EDivision.III) {
				return Assets.SILVER_III;

			} else if (mDivision == EDivision.IV) {
				return Assets.SILVER_IV;

			} else if (mDivision == EDivision.V) {
				return Assets.SILVER_V;

			}
		} else if (mTier == ETier.GOLD) {
			if (mDivision == EDivision.I) {
				return Assets.GOLD_I;

			} else if (mDivision == EDivision.II) {
				return Assets.GOLD_II;

			} else if (mDivision == EDivision.III) {
				return Assets.GOLD_III;

			} else if (mDivision == EDivision.IV) {
				return Assets.GOLD_IV;

			} else if (mDivision == EDivision.V) {
				return Assets.GOLD_V;

			}
		} else if (mTier == ETier.PLATINUM) {
			if (mDivision == EDivision.I) {
				return Assets.PLATINUM_I;

			} else if (mDivision == EDivision.II) {
				return Assets.PLATINUM_II;

			} else if (mDivision == EDivision.III) {
				return Assets.PLATINUM_III;

			} else if (mDivision == EDivision.IV) {
				return Assets.PLATINUM_IV;

			} else if (mDivision == EDivision.V) {
				return Assets.PLATINUM_V;

			}
		} else if (mTier == ETier.DIAMOND) {
			if (mDivision == EDivision.I) {
				return Assets.DIAMOND_I;

			} else if (mDivision == EDivision.II) {
				return Assets.DIAMOND_II;

			} else if (mDivision == EDivision.III) {
				return Assets.DIAMOND_III;

			} else if (mDivision == EDivision.IV) {
				return Assets.DIAMOND_IV;

			} else if (mDivision == EDivision.V) {
				return Assets.DIAMOND_V;

			}
		} else if (mTier == ETier.MASTER) {
			return Assets.MASTER;

		} else if (mTier == ETier.CHALLENGER) {
			return Assets.CHALLENGER;

		}
		return Assets.PROVISIONAL;

	}

	/**
	 * The description of this TierIcon.
	 */
	final String description;

	/**
	 * The image of this TierIcon.
	 */
	final Image icon;

	/**
	 * Creates a new TierIcon which conveniently wraps an Image and a String.
	 * 
	 * @param mIcon
	 *            The image for this TierIcon
	 *
	 * @param mDescription
	 *            The description for this TierIcon
	 */
	public TierIcon(final Image mIcon, final String mDescription) {
		this.icon = mIcon;
		this.description = mDescription;

	}

	/**
	 * Gets the description for this TierIcon.
	 * 
	 * @return The description mentioned.
	 */
	public String getDescription() {
		return this.description;

	}

	/**
	 * Gets the Icon for this TierIcon.
	 * 
	 * @return The Icon mentioned.
	 */
	public Image getIcon() {
		return this.icon;

	}

}
