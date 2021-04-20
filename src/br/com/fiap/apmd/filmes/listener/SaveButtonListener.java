package br.com.fiap.apmd.filmes.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.fiap.apmd.filmes.components.ChooseOptionComponent;
import br.com.fiap.apmd.filmes.components.StarRater;
import br.com.fiap.apmd.filmes.model.Filme;

public class SaveButtonListener implements ActionListener {

	private JTextField titleField;
	private JTextArea synopsisArea;
	
	private JComboBox<String> genreCombo;
	
	private ChooseOptionComponent watchPlaceChoose;
	private JCheckBox watchedCheck;
	
	private StarRater starRater;
	
	public SaveButtonListener(JTextField titleField, JTextArea synopsisArea,  JComboBox<String> genreCombo,  ChooseOptionComponent watchPlaceChoose, JCheckBox watchedCheck, StarRater starRater) {
		this.titleField = titleField;
		this.synopsisArea = synopsisArea;
		this.genreCombo = genreCombo;
		this.watchPlaceChoose = watchPlaceChoose;
		this.watchedCheck = watchedCheck;
		this.starRater = starRater;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Filme filme = new Filme();	
		filme.setTitulo(titleField.getText());
		filme.setSinopse(synopsisArea.getText());
		filme.setGenero((String) genreCombo.getSelectedItem());
		filme.setAssistir(watchPlaceChoose.getValue());
		filme.setAssistido(watchedCheck.isSelected());
		filme.setAvaliacao(starRater.getSelection());
	
		JOptionPane.showMessageDialog(null, 
				"Título: " + filme.getTitulo() 
			+ "\nSinopse: " + filme.getSinopse()
			+ "\nGenero: " + filme.getGenero()
			+ "\nOnde assistir: " + filme.getAssistir() 
			+ "\nAssistido: " + filme.isAssistido()
			+ "\nAvaliação: " + filme.getAvaliacao()
		);
	}
	
}
