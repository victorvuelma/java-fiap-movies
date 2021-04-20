package br.com.fiap.apmd.filmes.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.fiap.apmd.filmes.components.ChooseOptionComponent;
import br.com.fiap.apmd.filmes.components.StarRater;

public class CancelButtonListener implements ActionListener {

	private JTextField titleField;
	private JTextArea synopsisArea;
	
	private JComboBox<String> genreCombo;
	
	private ChooseOptionComponent watchPlaceChoose;
	private JCheckBox watchedCheck;
	
	private StarRater starRater;
	
	public CancelButtonListener(JTextField titleField, JTextArea synopsisArea,  JComboBox<String> genreCombo,  ChooseOptionComponent watchPlaceChoose, JCheckBox watchedCheck, StarRater starRater) {
		this.titleField = titleField;
		this.synopsisArea = synopsisArea;
		this.genreCombo = genreCombo;
		this.watchPlaceChoose = watchPlaceChoose;
		this.watchedCheck = watchedCheck;
		this.starRater = starRater;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.titleField.setText(null);
		this.synopsisArea.setText(null);
		
		this.genreCombo.setSelectedItem(null);
		this.watchedCheck.setSelected(false);
		
		this.watchPlaceChoose.clearSelection();
				
		this.starRater.setRating(0);
		this.starRater.setSelection(0);		
	}
	
}
