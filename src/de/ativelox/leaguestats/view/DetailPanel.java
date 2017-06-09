package de.ativelox.leaguestats.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JPanel;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class DetailPanel extends JPanel {

	public final static int V_GAP = 3;
	public final static int H_GAP = 5;

	private final WinratePanel winratePanel;
	private final KDAPanel kdaPanel;
	private final KeystonePanel keystonePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DetailPanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, H_GAP, V_GAP));
		
		this.setPreferredSize(new Dimension(this.getWidth(), Display.WINDOW_HEIGHT / 20));

		this.winratePanel = new WinratePanel();
		this.kdaPanel = new KDAPanel();
		this.keystonePanel = new KeystonePanel();
		
		this.add(this.winratePanel);
		this.add(this.kdaPanel);
		this.add(this.keystonePanel);	

	}
	
	public void setWinrate(final String mWinrate){
		this.winratePanel.setWinrate(mWinrate);
		
	}
	
	public void setKDA(final String mKDA){
		this.kdaPanel.setKDA(mKDA);
		
	}
	
	public void setKeystoneImage(final Image mKeystone){
		this.keystonePanel.setKeystoneImage(mKeystone);
		
	}
}
