package de.ativelox.leaguestats.model;

import java.awt.Image;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ChampionContainer {

	private final String championName;

	private final Image championImage;

	/**
	 * 
	 */
	public ChampionContainer(final String mChampionName, final Image mChampionImage) {
		this.championName = mChampionName;
		this.championImage = mChampionImage;

	}

	public String getName() {
		return this.championName;

	}

	public Image getImage() {
		return this.championImage;
	}

}
