package br.com.fiap.apmd.filmes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.apmd.filmes.components.ButtonComponent;
import br.com.fiap.apmd.filmes.components.ChooseOptionComponent;
import br.com.fiap.apmd.filmes.components.GridPanel;
import br.com.fiap.apmd.filmes.components.ImageComponent;
import br.com.fiap.apmd.filmes.components.LabelComponent;
import br.com.fiap.apmd.filmes.components.StarRater;
import br.com.fiap.apmd.filmes.database.dao.FilmeDAO;
import br.com.fiap.apmd.filmes.listener.CancelButtonListener;
import br.com.fiap.apmd.filmes.listener.SaveButtonListener;
import br.com.fiap.apmd.filmes.model.Filme;

public class App extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1659278897985887518L;

	public static void main(String[] args) {
		App app = new App();
		app.init();
	}

	private JTabbedPane tabsPane;

	private JTextField titleField;
	private JTextArea synopsisField;
	private JComboBox<String> genreCombo;

	private StarRater ratingStars;
	private ChooseOptionComponent watchOptionChoose;
	private JCheckBox watchedCheck;

	private JPanel formButtonPanel;
	private JButton saveButton;
	private JButton updateButton;

	private DefaultTableModel appTableModel = new DefaultTableModel();
	private JTable table = new JTable(appTableModel);
	private FilmeDAO dao = new FilmeDAO();

	private Filme filmeAlterar;

	public App() {
		super("Fiap Movies");
	}

	public void init() {
		tabsPane = new JTabbedPane();

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
		titleField = new JTextField();
		formPanel.add(titleLabel);
		formPanel.add(titleField);

		JLabel synopsisLabel = new LabelComponent("Sinopse");
		synopsisField = new JTextArea(3, 100);
		formPanel.add(synopsisLabel);
		formPanel.add(synopsisField);

		String[] filmGenres = { "Ação", "Comédia", "Romance", "Terror" };
		JLabel genreLabel = new LabelComponent("Genero");
		genreCombo = new JComboBox<String>(filmGenres);
		formPanel.add(genreLabel);
		formPanel.add(genreCombo);

		formButtonPanel = new GridPanel(1, 2, 8, 0);

		saveButton = new ButtonComponent("Salvar");

		formButtonPanel.add(saveButton);

		updateButton = new ButtonComponent("Salvar");
		updateButton.addActionListener(this);

		JButton abortButton = new ButtonComponent("Cancelar");
		formButtonPanel.add(abortButton);

		formPanel.add(formButtonPanel);

		registerPanel.add(formPanel);

		// INFO PANEL
		JPanel infoPanel = new GridPanel(5, 1, 0, 8);

		JLabel watchLabel = new LabelComponent("Onde assistir");
		infoPanel.add(watchLabel);

		String[] watchOptions = { "Netflix", "Prime Video", "Pirate Bay" };
		watchOptionChoose = new ChooseOptionComponent(watchOptions);
		infoPanel.add(watchOptionChoose);

		watchedCheck = new JCheckBox("Assistido");
		infoPanel.add(watchedCheck);

		JLabel ratingLabel = new LabelComponent("Avaliação");
		ratingStars = new StarRater();
		infoPanel.add(ratingLabel);
		infoPanel.add(ratingStars);

		registerPanel.add(infoPanel);

		saveButton.addActionListener(new SaveButtonListener(titleField, synopsisField, genreCombo, watchOptionChoose,
				watchedCheck, ratingStars));
		abortButton.addActionListener(new CancelButtonListener(titleField, synopsisField, genreCombo, watchOptionChoose,
				watchedCheck, ratingStars));

		tabsPane.add("Cadastro", registerPanel);

		JPanel listPanel = new JPanel(new BorderLayout());
		JScrollPane listScroll = new JScrollPane(this.table);

		initTable();
		loadTable();

		listPanel.add(listScroll);

		JPanel listButtonsPanel = new GridPanel(1, 3, 8, 8);

		JButton listDeleteButton = new ButtonComponent("Apagar");
		listDeleteButton.addActionListener(this);
		listButtonsPanel.add(listDeleteButton);

		JButton listEditButton = new ButtonComponent("Alterar");
		listEditButton.addActionListener(this);
		listButtonsPanel.add(listEditButton);

		JButton listRefreshButton = new ButtonComponent("Atualizar");
		listRefreshButton.addActionListener(this);
		listButtonsPanel.add(listRefreshButton);

		listPanel.add(listButtonsPanel, BorderLayout.SOUTH);

		tabsPane.add("Lista", listPanel);

		this.add(tabsPane);

		this.setSize(600, 330);
		this.setVisible(true);
	}

	private void initTable() {
		appTableModel.addColumn("Id");
		appTableModel.addColumn("Titulo");
		appTableModel.addColumn("Sinopse");
		appTableModel.addColumn("Genero");
		appTableModel.addColumn("Onde Assistir");
		appTableModel.addColumn("Assistido");
		appTableModel.addColumn("Avaliação");
	}

	private void loadTable() {
		appTableModel.setRowCount(0);

		List<Filme> filmes = dao.buscarTodos();
		for (Filme filme : filmes) {
			String[] linha = { filme.getId() + "", filme.getTitulo(), filme.getSinopse(), filme.getGenero(),
					filme.getAssistir(), filme.isAssistido() ? "Sim" : "Não", filme.getAvaliacao() + "", };
			appTableModel.addRow(linha);
		}
	}

	private void apagar() {
		int linha = table.getSelectedRow();
		String id = table.getValueAt(linha, 0).toString();
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que quer apagar o filme selecionado?");
		if (resposta == JOptionPane.YES_OPTION) {
			Filme filme = dao.buscarPorId(Long.valueOf(id));
			dao.apagar(filme);
			loadTable();
		}
	}

	private void alterar() {
		int linha = table.getSelectedRow();
		String id = table.getValueAt(linha, 0).toString();
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que quer alterar o filme selecionado?");
		if (resposta == JOptionPane.YES_OPTION) {
			filmeAlterar = dao.buscarPorId(Long.valueOf(id));

			titleField.setText(filmeAlterar.getTitulo());
			synopsisField.setText(filmeAlterar.getSinopse());
			genreCombo.setSelectedItem(filmeAlterar.getGenero());

			watchOptionChoose.setValue(filmeAlterar.getAssistir());
			watchedCheck.setSelected(filmeAlterar.isAssistido());
			ratingStars.setRating(filmeAlterar.getAvaliacao());

			formButtonPanel.remove(saveButton);
			formButtonPanel.add(updateButton);

			tabsPane.setSelectedIndex(0);
		}
	}

	private void salvarAlteracao() {
		formButtonPanel.remove(updateButton);
		formButtonPanel.add(saveButton);

		filmeAlterar.setTitulo(titleField.getText());
		filmeAlterar.setSinopse(synopsisField.getText());
		filmeAlterar.setGenero((String) genreCombo.getSelectedItem());

		filmeAlterar.setAssistir(watchOptionChoose.getValue());
		filmeAlterar.setAssistido(watchedCheck.isSelected());
		filmeAlterar.setAvaliacao(ratingStars.getSelection());

		dao.alterar(filmeAlterar);

		titleField.setText(null);
		synopsisField.setText(null);

		genreCombo.setSelectedItem(null);
		watchedCheck.setSelected(false);

		watchOptionChoose.clearSelection();

		ratingStars.setRating(0);
		ratingStars.setSelection(0);

		tabsPane.setSelectedIndex(1);

		loadTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton source = (JButton) e.getSource();

			if (source.getText().equals("Apagar")) {
				apagar();
			} else if (source.getText().equals("Atualizar")) {
				loadTable();
			} else if (source.getText().equals("Alterar")) {
				alterar();
			} else if (source.getText().equals("Salvar")) {
				salvarAlteracao();
			}
		}
	}

}
