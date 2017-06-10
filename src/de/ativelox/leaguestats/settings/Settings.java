package de.ativelox.leaguestats.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JOptionPane;

import net.rithms.riot.constant.Region;

/**
 * Provides methods to {@link Settings#saveSettings(SettingsProvider)} and
 * {@link Settings#loadSettings(SettingsProvider)} for a given
 * {@link SettingsProvider} which has all the values.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Settings {

	/**
	 * The header-comment of the properties file.
	 */
	private static final String FILE_COMMENT = "Configuration settings for LeagueStats.";

	/**
	 * The relative filepath for the properties file.
	 */
	private static final String FILEPATH = "config.ini";

	/**
	 * The properties for these settings.
	 */
	private final Properties properties;

	/**
	 * Provides methods to {@link Settings#saveSettings(SettingsProvider)} and
	 * {@link Settings#loadSettings(SettingsProvider)} for a given
	 * {@link SettingsProvider} which has all the values.
	 */
	public Settings() {
		this.properties = new Properties();

	}

	/**
	 * Loads the Settings of {@link Settings#FILEPATH} and loads them in the
	 * given settingsProvider. If the file does not exists this method will ask
	 * for specific user-input.
	 * 
	 * @param mProvider
	 *            The provider into which to load the settings.
	 */
	public void loadSettings(final SettingsProvider mProvider) {

		try (final FileInputStream fis = new FileInputStream(FILEPATH)) {
			try {
				this.properties.load(fis);

			} catch (final FileNotFoundException e) {

				try (final FileInputStream anotherFis = new FileInputStream(FILEPATH)) {
					this.properties.load(anotherFis);

				}
			}

			for (final Entry<Object, Object> entry : this.properties.entrySet()) {
				mProvider.setSetting((String) entry.getKey(), (String) entry.getValue());
			}

		} catch (final IOException e) {
			mProvider.setApiKey(JOptionPane.showInputDialog("Enter your API-Key."));
			mProvider.setRegion(Region.valueOf((String) JOptionPane.showInputDialog(null, "Select your region.",
					"RegionSelection", JOptionPane.INFORMATION_MESSAGE, null,
					new Object[] { Region.EUW.toString().toUpperCase(), Region.EUNE.toString().toUpperCase(),
							Region.BR.toString().toUpperCase(), Region.KR.toString().toUpperCase(),
							Region.LAN.toString().toUpperCase(), Region.LAS.toString().toUpperCase(),
							Region.NA.toString().toUpperCase(), Region.OCE.toString().toUpperCase(),
							Region.RU.toString().toUpperCase(), Region.TR.toString().toUpperCase() },
					Region.EUW.toString().toUpperCase())));
			mProvider.setSummonerName(JOptionPane.showInputDialog("Enter your Summonername."));
			this.saveSettings(mProvider);
			this.loadSettings(mProvider);

		}
	}

	/**
	 * Gets all the values given by the settings provider and saves them to
	 * {@link Settings#FILEPATH}.
	 * 
	 * @param mProvider
	 *            The settings provider of which to get the settings to be
	 *            saved.
	 */
	public void saveSettings(final SettingsProvider mProvider) {

		for (final Entry<String, String> entry : mProvider.getAllSettings().entrySet()) {
			this.properties.put(entry.getKey(), entry.getValue());

		}

		try (final FileOutputStream target = new FileOutputStream(new File(FILEPATH))) {
			this.properties.store(target, FILE_COMMENT);

		} catch (final IOException e) {
			e.printStackTrace();

		}
	}

}
