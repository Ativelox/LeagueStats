package de.ativelox.leaguestats.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * A {@link JTextPane} which provides
 * {@link ColorableTextPane#appendText(String, Color)} to append given text in a
 * given color.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class ColorableTextPane extends JTextPane {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new {@link JTextPane} which provides
	 * {@link ColorableTextPane#appendText(String, Color)} to append given text
	 * in a given color.
	 */
	public ColorableTextPane() {
		super(new DefaultStyledDocument());

		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFont(new Font("Arial", Font.BOLD, 15));
		this.setFocusable(false);

	}

	/**
	 * Appends the given text in the given color to the content written on this
	 * textpane.
	 * 
	 * @param mText
	 *            The text to append.
	 * 
	 * @param mColor
	 *            The color to write the text in.
	 */
	public void appendText(final String mText, final Color mColor) {
		DefaultStyledDocument s = (DefaultStyledDocument) this.getStyledDocument();
		StyleContext context = new StyleContext();
		Style style = context.addStyle("test", null);
		StyleConstants.setForeground(style, mColor);

		try {
			s.insertString(s.getLength(), mText, style);

		} catch (BadLocationException e) {
			e.printStackTrace();

		}
	}
}
