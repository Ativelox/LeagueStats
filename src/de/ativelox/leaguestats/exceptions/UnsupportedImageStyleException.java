package de.ativelox.leaguestats.exceptions;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class UnsupportedImageStyleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UnsupportedImageStyleException() {
		super();
	}
	
	public UnsupportedImageStyleException(final String mError){
		super(mError);
		
	}

}
