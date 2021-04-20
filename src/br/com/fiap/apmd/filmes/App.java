package br.com.fiap.apmd.filmes;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.fiap.apmd.filmes.components.ButtonComponent;
import br.com.fiap.apmd.filmes.components.ChooseOptionComponent;
import br.com.fiap.apmd.filmes.components.GridPanel;
import br.com.fiap.apmd.filmes.components.ImageComponent;
import br.com.fiap.apmd.filmes.components.LabelComponent;
import br.com.fiap.apmd.filmes.components.StarRater;
import br.com.fiap.apmd.filmes.listener.CancelButtonListener;
import br.com.fiap.apmd.filmes.listener.SaveButtonListener;

public class App {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fiap Movies");

		JTabbedPane tabsPane = new JTabbedPane();

		JPanel registerPanel = new GridPanel(1, 3, 16, 0);

		// IMAGE PANEL
		JPanel imagePanel = new JPanel();
		imagePanel.add(new ImageComponent("images/Maze_runner.jpg"));
		registerPanel.add(imagePanel);

		// FORM PANEL
		GridLayout formGrid = new GridLayout(7, 1);
		formGrid.setVgap(8);
		JPanel formPanel = new JPanel(formGrid);

		JLabel titleLabel = new LabelComponent("Titulo");
		JTextField titleField = new JTextField();
		formPanel.add(titleLabel);
		formPanel.add(titleField);

		JLabel synopsisLabel = new LabelComponent("Sinopse");
		JTextArea synopsisField = new JTextArea(3, 100);
		formPanel.add(synopsisLabel);
		formPanel.add(synopsisField);

		String[] filmGenres = { "Ação", "Comédia", "Romance", "Terror" };
		JLabel genreLabel = new LabelComponent("Genero");
		JComboBox<String> genreCombo = new JComboBox<String>(filmGenres);
		formPanel.add(genreLabel);
		formPanel.add(genreCombo);
				
		JPanel formButtonPanel = new GridPanel(1, 2, 8, 0);

		JButton saveButton = new ButtonComponent("Salvar");
		formButtonPanel.add(saveButton);

		JButton abortButton = new ButtonComponent("Cancelar");
		formButtonPanel.add(abortButton);

		formPanel.add(formButtonPanel);

		registerPanel.add(formPanel);

		// INFO PANEL
		JPanel infoPanel = new GridPanel(5, 1, 0, 8);

		JLabel watchLabel = new LabelComponent("Onde assistir");
		infoPanel.add(watchLabel);

		String[] watchOptions = { "Netflix", "Prime Video", "Pirate Bay" };
		ChooseOptionComponent watchOptionChoose = new ChooseOptionComponent(watchOptions);
		infoPanel.add(watchOptionChoose);

		JCheckBox watchedCheck = new JCheckBox("Assistido");
		infoPanel.add(watchedCheck);

		JLabel ratingLabel = new LabelComponent("Avaliação");
		StarRater ratingStars = new StarRater();
		infoPanel.add(ratingLabel);
		infoPanel.add(ratingStars);

		registerPanel.add(infoPanel);

		tabsPane.add("Cadastro", registerPanel);
		tabsPane.add("Lista", new JPanel());
		
		saveButton.addActionListener(new SaveButtonListener(titleField, synopsisField, genreCombo, watchOptionChoose, watchedCheck, ratingStars));
		abortButton.addActionListener(new CancelButtonListener(titleField, synopsisField, genreCombo, watchOptionChoose, watchedCheck, ratingStars));

		frame.add(tabsPane);

		frame.setSize(600, 330);
		frame.setVisible(true);
	}

}
