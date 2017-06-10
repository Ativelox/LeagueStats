package de.ativelox.leaguestats.exceptions;

/**
 * An Exception which is thrown when the given URL isn't any specified by Riot.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class InvalidURLException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An Exception which is thrown when the given URL isn't any specified by
	 * Riot.
	 */
	public InvalidURLException() {
		super();

	}

	/**
	 * An Exception which is thrown when the given URL isn't any specified by
	 * Riot.
	 * 
	 * @param mError
	 *            The error message to print.
	 */
	public InvalidURLException(final String mError) {
		super(mError);

	}

}
