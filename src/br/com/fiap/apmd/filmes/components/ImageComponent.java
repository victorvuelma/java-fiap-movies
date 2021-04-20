package br.com.fiap.apmd.filmes.components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageComponent extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 198116529612389071L;

	private String filePath;
	
	public ImageComponent(String filePath) {
		super();
		
		this.filePath = filePath;
		
		init();
	}
	
	private void init() {
		Image image = new ImageIcon(this.filePath).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(image));
		setSize(200, 250);
	}
	
}
