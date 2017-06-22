package de.ativelox.leaguestats.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import de.ativelox.leaguestats.model.TierIcon;

/**
 * Contains all the TierIcons in {@link Assets#TIER_PATH} and
 * {@link Assets#BASE_PATH}.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Assets {

	/**
	 * The file path for the tray icon.
	 */
	private static final String TRAY_FILE_PATH = "res/tray/tray.png";

	/**
	 * The directory where fonts are stored.
	 */
	private static final String FONT_FILE_PATH = "res/fonts/";

	/**
	 * The name for the caviar dreams font.
	 */
	private static final String CAVIAR_FONT_NAME = "Caviar Dreams";

	/**
	 * Different fonts used throughout this application.
	 */
	public static Font NAME_FONT, STATS_FONT, SMALL_NAME_FONT;

	/**
	 * The image for the tray icon.
	 */
	public static Image TRAY_ICON;

	/**
	 * Every Bronze TierIcon
	 */
	public static TierIcon BRONZE_I, BRONZE_II, BRONZE_III, BRONZE_IV, BRONZE_V;

	/**
	 * Every TierIcon not associated with any division.
	 */
	public static TierIcon CHALLENGER, MASTER, PROVISIONAL;

	/**
	 * Every Diamond TierIcon.
	 */
	public static TierIcon DIAMOND_I, DIAMOND_II, DIAMOND_III, DIAMOND_IV, DIAMOND_V;

	/**
	 * Every Gold TierIcon.
	 */
	public static TierIcon GOLD_I, GOLD_II, GOLD_III, GOLD_IV, GOLD_V;

	/**
	 * The height of every icon.
	 */
	public static final int HEIGHT = 200;

	/**
	 * Every Platinum TierIcon.
	 */
	public static TierIcon PLATINUM_I, PLATINUM_II, PLATINUM_III, PLATINUM_IV, PLATINUM_V;

	/**
	 * Every Silver TierIcon.
	 */
	public static TierIcon SILVER_I, SILVER_II, SILVER_III, SILVER_IV, SILVER_V;

	/**
	 * The width of every icon.
	 */
	public static final int WIDTH = 200;

	/**
	 * The relative path for all the base icons
	 */
	private final static String BASE_PATH = "res/base-icons/";

	/**
	 * The file extension for all the images contained in the given file paths.
	 */
	private final static String FILE_EXTENSION = ".png";

	/**
	 * The relative path for all the tiers.
	 */
	private final static String TIER_PATH = "res/tier-icons/";

	/**
	 * The logger used for logging.
	 */
	private static ILogger logger;

	/**
	 * Initiates the assets by loading every image in the paths
	 * {@link Assets#TIER_PATH} and {@link Assets#BASE_PATH} and providing an
	 * appropriate description.
	 */
	public static void init() {
		logger = LoggerFactory.getLogger();

		logger.log("Loading local assets...", ELogLevel.INFO);

		initFonts();

		try {
			TRAY_ICON = ImageIO.read(new File(TRAY_FILE_PATH));

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

			PLATINUM_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_i" + FILE_EXTENSION)), "Plat. I");
			PLATINUM_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_ii" + FILE_EXTENSION)), "Plat. II");
			PLATINUM_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_iii" + FILE_EXTENSION)),
					"Plat. III");
			PLATINUM_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_iv" + FILE_EXTENSION)), "Plat. IV");
			PLATINUM_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "platinum_v" + FILE_EXTENSION)), "Plat. V");

			DIAMOND_I = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_i" + FILE_EXTENSION)), "Dia. I");
			DIAMOND_II = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_ii" + FILE_EXTENSION)), "Dia. II");
			DIAMOND_III = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_iii" + FILE_EXTENSION)), "Dia. III");
			DIAMOND_IV = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_iv" + FILE_EXTENSION)), "Dia. IV");
			DIAMOND_V = new TierIcon(ImageIO.read(new File(TIER_PATH + "diamond_v" + FILE_EXTENSION)), "Dia. V");

			PROVISIONAL = new TierIcon(ImageIO.read(new File(BASE_PATH + "provisional" + FILE_EXTENSION)), "Unranked");
			MASTER = new TierIcon(ImageIO.read(new File(BASE_PATH + "master" + FILE_EXTENSION)), "Master");
			CHALLENGER = new TierIcon(ImageIO.read(new File(BASE_PATH + "challenger" + FILE_EXTENSION)), "Challenger");

		} catch (IOException e) {
			logger.log("An error occured while trying to load in division images from the local drive...",
					ELogLevel.ERROR);

		}

	}

	/**
	 * Initializes the fonts for this application.
	 */
	private static void initFonts() {
		final String extension = ".ttf";
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
					new File(FONT_FILE_PATH + CAVIAR_FONT_NAME.replace(" ", "") + extension)));

		} catch (FontFormatException | IOException e) {
			logger.logError("An error occured while trying to register the font: " + CAVIAR_FONT_NAME + extension);

		}
		NAME_FONT = new Font(CAVIAR_FONT_NAME, Font.BOLD, 18);
		SMALL_NAME_FONT = new Font(CAVIAR_FONT_NAME, Font.PLAIN, 15);
		STATS_FONT = new Font(CAVIAR_FONT_NAME, Font.PLAIN, 18);
	}

	/**
	 * Utility class, no initialization needed
	 */
	private Assets() {

	}

}
