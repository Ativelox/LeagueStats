package de.ativelox.leaguestats.exceptions;

/**
 * Exception which is thrown when the given Team ID was invalid.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class InvalidTeamIdException extends IllegalArgumentException {

	/**
	 * The default error message which is used when the constructor is called
	 * without details.
	 */
	private final static String DEFAULT_ERROR_MESSAGE = "Invalid Team ID. The Team ID is either 100 or 200. "
			+ "See the Riot APIs Documentation for more information.";

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception which is thrown when the given Team ID was invalid. Calls
	 * {@link InvalidTeamIdException#InvalidTeamIdException(String)} with
	 * {@link InvalidTeamIdException#DEFAULT_ERROR_MESSAGE}.
	 */
	public InvalidTeamIdException() {
		this(DEFAULT_ERROR_MESSAGE);
	}

	/**
	 * Exception which is thrown when the given Team ID was invalid.
	 * 
	 * @param mError
	 *            The error message to print.
	 */
	public InvalidTeamIdException(final String mError) {
		super(mError);

	}

}
