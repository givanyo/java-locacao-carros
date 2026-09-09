package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import controller.ControllerAdminCarro;
import model.Categoria;
import model.InfoCarro;
import model.Usuario;

public class TelaAdminCarro extends JPanel {
	private static final long serialVersionUID = 1L;

	private ControllerAdminCarro controller;

	private JComboBox<Categoria> comboCategoria;
	private JTextField campoModelo;
	private JTextField campoPlaca;
	private JButton botaoCadastrar;

	private JTable tabelaCarros;
	private DefaultTableModel modeloTabela;

	public TelaAdminCarro(Usuario admin) {
		this.controller = new ControllerAdminCarro(admin, this);

		setLayout(new BorderLayout());

		criarComponentes();
		configurarEventos();

		carregarCategorias();
		atualizar();
	}

	private void criarComponentes() {
		JPanel painelCadastro = new JPanel(new GridLayout(2, 1));

		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		comboCategoria = new JComboBox<>();
		campoModelo = new JTextField(15);
		campoPlaca = new JTextField(8);
		botaoCadastrar = new JButton("Cadastrar Carro");

		linha1.add(new JLabel("Categoria:"));
		linha1.add(comboCategoria);
		linha1.add(new JLabel("Modelo:"));
		linha1.add(campoModelo);
		linha1.add(new JLabel("Placa:"));
		linha1.add(campoPlaca);

		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linha2.add(botaoCadastrar);

		painelCadastro.add(linha1);
		painelCadastro.add(linha2);

		String[] colunas = {"ID", "Categoria", "Modelo", "Placa", "Pessoas", "Valor Diária"};
		modeloTabela = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaCarros = new JTable(modeloTabela);

		JPanel painelTabela = new JPanel(new BorderLayout());
		painelTabela.add(new JScrollPane(tabelaCarros), BorderLayout.CENTER);

		add(painelCadastro, BorderLayout.NORTH);
		add(painelTabela, BorderLayout.CENTER);
	}

	private void configurarEventos() {
		botaoCadastrar.addActionListener(e -> cadastrarCarro());
	}

	private void carregarCategorias() {
		List<Categoria> categorias = controller.buscarCategorias();
		comboCategoria.removeAllItems();
		if (categorias != null) {
			for (Categoria categoria : categorias) {
				comboCategoria.addItem(categoria);
			}
		}
	}

	private void cadastrarCarro() {
		Categoria categoriaSelecionada = (Categoria) comboCategoria.getSelectedItem();
		if (categoriaSelecionada == null) {
			return;
		}

		String modelo = campoModelo.getText().trim();
		String placa = campoPlaca.getText().trim();

		controller.cadastrarCarro(categoriaSelecionada.getIdCategoria(), modelo, placa);

		campoModelo.setText("");
		campoPlaca.setText("");
	}

	public void atualizar() {
		modeloTabela.setRowCount(0);

		List<InfoCarro> carros = controller.buscarTodosCarros();
		if (carros == null) {
			return;
		}

		for (InfoCarro carro : carros) {
			modeloTabela.addRow(new Object[] {
				carro.getIdCarro(),
				carro.getTipoCarro(),
				carro.getModeloCarro(),
				carro.getPlaca(),
				carro.getPessoas(),
				carro.getValorDiaria()
			});
		}
	}
}