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
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ColorableTextPane extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ColorableTextPane() {
		super(new DefaultStyledDocument());
		
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFont(new Font("Arial", Font.BOLD, 15));
		this.setFocusable(false);

	}

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
