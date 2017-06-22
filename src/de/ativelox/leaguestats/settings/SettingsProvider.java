package de.ativelox.leaguestats.settings;

import java.util.HashMap;
import java.util.Map;

import net.rithms.riot.constant.Region;

/**
 * The settings provider which holds all the settings needed throughout this
 * project.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class SettingsProvider {

	/**
	 * The unknown key value.
	 */
	public static final String UNKNOWN_KEY_VALUE = "";

	/**
	 * The key used to store the api key.
	 */
	private static final String KEY_IDENTIFIER_API = "API_KEY";

	/**
	 * The key used to store the region.
	 */
	private static final String KEY_IDENTIFIER_REGION = "REGION";

	/**
	 * The key used to store the summoner name.
	 */
	private static final String KEY_IDENTIFIER_SUMMONER_NAME = "SUMMONER_NAME";

	/**
	 * The settings used to load from or save to.
	 */
	private final Settings settings;

	/**
	 * The internal map used to handle the settings.
	 */
	private final Map<String, String> settingsStore;

	/**
	 * The settings provider which holds all the settings needed throughout this
	 * project.
	 */
	public SettingsProvider() {
		this.settingsStore = new HashMap<>();
		this.settings = new Settings();
	}

	/**
	 * Returns {@link SettingsProvider#settingsStore} which holds all the
	 * settings.
	 * 
	 * @return the Map mentioned.
	 */
	public Map<String, String> getAllSettings() {
		return this.settingsStore;
	}

	/**
	 * Gets the api key saved in the file.
	 * 
	 * @return the api key.
	 */
	public String getApiKey() {
		final String value = this.getSetting(KEY_IDENTIFIER_API);

		if (value != null) {
			return value;

		}
		return null;

	}

	/**
	 * Gets the region.
	 * 
	 * @return The region mentioned.
	 */
	public Region getRegion() {
		return Region.valueOf(this.getSetting(KEY_IDENTIFIER_REGION).toUpperCase());

	}

	/**
	 * Gets a setting from {@link SettingsProvider#settingsStore}.
	 * 
	 * @param mKey
	 *            The key which to lookup.
	 * 
	 * @return The value corresponding to the key given.
	 */
	public String getSetting(final String mKey) {
		String value = this.settingsStore.get(mKey);

		if (value == null) {
			value = UNKNOWN_KEY_VALUE;

		}
		return value;

	}

	/**
	 * Gets the summoner name.
	 * 
	 * @return The summoner name mentioned.
	 */
	public String getSummonerName() {
		return this.getSetting(KEY_IDENTIFIER_SUMMONER_NAME);

	}

	/**
	 * Initializes this settings provider by loading all the settings from the
	 * settings object.
	 * 
	 * @see Settings#loadSettings(SettingsProvider)
	 */
	public void initialize() {
		this.settings.loadSettings(this);

	}

	/**
	 * Saves all the settings in {@link SettingsProvider#settingsStore}.
	 * 
	 * @see Settings#saveSettings(SettingsProvider)
	 */
	public final void saveSettings() {
		this.settings.saveSettings(this);

	}

	/**
	 * Sets the api key.
	 * 
	 * @param mApiKey
	 *            The api key to set.
	 */
	public void setApiKey(final String mApiKey) {
		if (mApiKey != null) {
			final String key = KEY_IDENTIFIER_API;
			this.setSetting(key, mApiKey);

		}
	}

	/**
	 * Sets the region.
	 * 
	 * @param mRegion
	 *            The region to set.
	 */
	public void setRegion(final Region mRegion) {
		if (mRegion != null) {
			final String key = KEY_IDENTIFIER_REGION;
			this.setSetting(key, mRegion.toString());

		}

	}

	/**
	 * Sets a setting to {@link SettingsProvider#settingsStore}.
	 * 
	 * @param mKey
	 *            The key which to set
	 * 
	 * @param mValue
	 *            The corresponding value to set
	 */
	public final void setSetting(final String mKey, final String mValue) {
		this.settingsStore.put(mKey, mValue);

	}

	/**
	 * Sets the summoner name.
	 * 
	 * @param mSummonerName
	 *            The summoner name to set.
	 */
	public void setSummonerName(final String mSummonerName) {
		if (mSummonerName != null) {
			final String key = KEY_IDENTIFIER_SUMMONER_NAME;
			this.setSetting(key, mSummonerName);
		}
	}
}
