package de.ativelox.leaguestats.exceptions;

/**
 * Exception which is thrown if the given
 * {@link de.ativelox.leaguestats.constants.EImageStyle} wasn't supported by
 * Riot.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class UnsupportedImageStyleException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception which is thrown if the given
	 * {@link de.ativelox.leaguestats.constants.EImageStyle} wasn't supported by
	 * Riot.
	 */
	public UnsupportedImageStyleException() {
		super();
	}

	/**
	 * Exception which is thrown if the given
	 * {@link de.ativelox.leaguestats.constants.EImageStyle} wasn't supported by
	 * Riot.
	 * 
	 * @param mError
	 *            The error message to be printed.
	 */
	public UnsupportedImageStyleException(final String mError) {
		super(mError);

	}

}
