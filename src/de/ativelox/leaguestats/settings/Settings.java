package de.ativelox.leaguestats.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class Settings {

	private static final String FILE_COMMENT = "Configuration settings for LeagueStats.";

	private static final String FILEPATH = "config.ini";

	private final Properties properties;

	/**
	 * 
	 */
	public Settings() {
		this.properties = new Properties();

	}

	public final void loadSettings(final SettingsProvider mProvider) {

		try (final FileInputStream fis = new FileInputStream(FILEPATH)) {
			try {
				this.properties.load(fis);

			} catch (final FileNotFoundException e) {
				this.saveSettings(mProvider);

				try (final FileInputStream anotherFis = new FileInputStream(FILEPATH)) {
					this.properties.load(anotherFis);
				}
			}

			for (final Entry<Object, Object> entry : this.properties.entrySet()) {
				mProvider.setSetting((String) entry.getKey(), (String) entry.getValue());
			}

		} catch (final IOException e) {
			e.printStackTrace();

		}
	}

	public final void saveSettings(final SettingsProvider mProvider) {

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
