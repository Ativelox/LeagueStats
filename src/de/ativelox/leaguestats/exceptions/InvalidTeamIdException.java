package de.ativelox.leaguestats.exceptions;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class InvalidTeamIdException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidTeamIdException() {
		this("Invalid Team ID. The Team ID is either 100 or 200. See the Riot APIs Documentation for more information.");
	}

	public InvalidTeamIdException(final String mError) {
		super(mError);

	}

}
