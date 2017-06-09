package de.ativelox.leaguestats.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.ativelox.leaguestats.model.TierIcon;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class Assets {

	public static final int HEIGHT = 200;
	public static final int WIDTH = 200;

	private final static String TIER_PATH = "res/tier-icons/";
	private final static String BASE_PATH = "res/base-icons/";
	private final static String FILE_EXTENSION = ".png";

	public static TierIcon BRONZE_I, BRONZE_II, BRONZE_III, BRONZE_IV, BRONZE_V;
	public static TierIcon SILVER_I, SILVER_II, SILVER_III, SILVER_IV, SILVER_V;
	public static TierIcon GOLD_I, GOLD_II, GOLD_III, GOLD_IV, GOLD_V;
	public static TierIcon PLATINUM_I, PLATINUM_II, PLATINUM_III, PLATINUM_IV, PLATINUM_V;
	public static TierIcon DIAMOND_I, DIAMOND_II, DIAMOND_III, DIAMOND_IV, DIAMOND_V;
	public static TierIcon CHALLENGER, MASTER, PROVISIONAL;

	public static void init() {
		try {
			BRONZE_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "bronze_i" + FILE_EXTENSION)), "Bronze I");
			BRONZE_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "bronze_ii" + FILE_EXTENSION)), "Bronze II");
			BRONZE_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "bronze_iii" + FILE_EXTENSION)), "Bronze III");
			BRONZE_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "bronze_iv" + FILE_EXTENSION)), "Bronze IV");
			BRONZE_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "bronze_v" + FILE_EXTENSION)), "Bronze V");

			SILVER_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "silver_i" + FILE_EXTENSION)), "Silver I");
			SILVER_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "silver_ii" + FILE_EXTENSION)), "Silver II");
			SILVER_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "silver_iii" + FILE_EXTENSION)), "Silver III");
			SILVER_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "silver_iv" + FILE_EXTENSION)), "Silver IV");
			SILVER_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "silver_v" + FILE_EXTENSION)), "Silver V");

			GOLD_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "gold_i" + FILE_EXTENSION)), "Gold I");
			GOLD_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "gold_ii" + FILE_EXTENSION)), "Gold II");
			GOLD_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "gold_iii" + FILE_EXTENSION)), "Gold III");
			GOLD_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "gold_iv" + FILE_EXTENSION)), "Gold IV");
			GOLD_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "gold_v" + FILE_EXTENSION)), "Gold V");

			PLATINUM_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_i" + FILE_EXTENSION)), "Platinum I");
			PLATINUM_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_ii" + FILE_EXTENSION)),
					"Platinum II");
			PLATINUM_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_iii" + FILE_EXTENSION)),
					"Platinum III");
			PLATINUM_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_iv" + FILE_EXTENSION)),
					"Platinum IV");
			PLATINUM_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_v" + FILE_EXTENSION)), "Platinum V");

			DIAMOND_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_i" + FILE_EXTENSION)), "Diamond I");
			DIAMOND_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_ii" + FILE_EXTENSION)), "Diamond II");
			DIAMOND_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_iii" + FILE_EXTENSION)),
					"Diamond III");
			DIAMOND_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_iv" + FILE_EXTENSION)), "Diamond IV");
			DIAMOND_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_v" + FILE_EXTENSION)), "Diamond V");

			PROVISIONAL = new TierIcon(ImageIO.read(new File(BASE_PATH + "provisional" + FILE_EXTENSION)), "Unranked");
			MASTER = new TierIcon(ImageIO.read(new File(BASE_PATH + "master" + FILE_EXTENSION)), "Master");
			CHALLENGER = new TierIcon(ImageIO.read(new File(BASE_PATH + "challenger" + FILE_EXTENSION)), "Challenger");

		} catch (IOException e) {
			// TODO: error handling
			// TODO: Remove Debug Print!
			System.out.println("An error occured while trying to load in tier images.");

		}

	}

	/**
	 * 
	 */
	private Assets() {

	}

}
