package de.ativelox.leaguestats.exceptions;

/**
 * Thrown when the system tray isn't supported.
 * 
 * @see java.lang.IllegalStateException
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class UnsupportedSystemTrayException extends IllegalStateException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Redirects the Exception to
	 * {@link IllegalStateException#IllegalStateException()}.
	 */
	public UnsupportedSystemTrayException() {
		super();

	}
}