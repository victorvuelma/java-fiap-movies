package br.com.fiap.apmd.filmes.components;

import javax.swing.JButton;

import br.com.fiap.apmd.filmes.factory.ColorFactory;
import br.com.fiap.apmd.filmes.factory.FontFactory;

public class ButtonComponent extends JButton {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4411861189100738834L;

	public ButtonComponent(String text) {
		super(text);
		
		init();
	}
	
	private void init() {
		this.setFont(FontFactory.getBold(12));
		this.setForeground(ColorFactory.getTextColor());
	}
	
}
