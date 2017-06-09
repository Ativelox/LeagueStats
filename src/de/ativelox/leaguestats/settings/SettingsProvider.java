package de.ativelox.leaguestats.settings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class SettingsProvider {

	public static final String UNKNOWN_KEY_VALUE = "";

	private static final String KEY_IDENTIFIER_API = "API_KEY";

	/*
	 * NOTE: this is only for demo purposes, since this is not needed in the
	 * final product. This is solely used so this application won't exceed the
	 * rate limit set by RIOT Games.
	 */
	public static boolean IS_DEMO = true;

	private final Settings settings;

	private final Map<String, String> settingsStore;

	/**
	 * 
	 */
	public SettingsProvider() {
		this.settingsStore = new HashMap<>();
		this.settings = new Settings();
	}

	public final Map<String, String> getAllSettings() {
		return this.settingsStore;
	}

	public final String getApiKey() {
		final String value = this.getSetting(KEY_IDENTIFIER_API);

		if (value != null) {
			return value;

		}
		return null;

	}

	public final String getSetting(final String mKey) {
		String value = this.settingsStore.get(mKey);

		if (value == null) {
			value = UNKNOWN_KEY_VALUE;

		}
		return value;

	}

	public final void initialize() {
		this.settings.loadSettings(this);

	}

	public final void saveSettings() {
		this.settings.saveSettings(this);

	}

	public final void setApiKey(final String mApiKey) {
		if (mApiKey != null) {
			final String key = KEY_IDENTIFIER_API;
			this.setSetting(key, mApiKey);

		}
	}

	public final void setSetting(final String mKey, final String mValue) {
		this.settingsStore.put(mKey, mValue);

	}
}
