package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LocacaoController;
import model.InfoLocacao;
import model.Usuario;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.List;

public class TelaLocacao extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tabelaLocacoes;
	private DefaultTableModel modeloTabela;

	private JButton btnEncerrarLocacao;
	private Usuario usuario;
	private List<InfoLocacao> locacoesAtuais;
	private LocacaoController controller;

	public TelaLocacao(Usuario usuario) {
		this.modeloTabela = new DefaultTableModel(
				new Object[]{"Tipo", "Modelo", "Início da Locação", "Fim da Locação"}, 0
			) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		setLayout(new BorderLayout(0, 0));
		this.usuario = usuario;
		this.controller  = new LocacaoController(this.usuario, this);
		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		JPanel painelTitulo = new JPanel();
		add(painelTitulo, BorderLayout.NORTH);
		painelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitulo = new JLabel("Suas locações de carros");
		painelTitulo.add(lblTitulo);

		JPanel painelLocacoes = new JPanel();
		add(painelLocacoes, BorderLayout.CENTER);
		GridBagLayout gblPainelLocacoes = new GridBagLayout();
		gblPainelLocacoes.columnWidths = new int[]{0, 0};
		gblPainelLocacoes.rowHeights = new int[]{0, 0};
		gblPainelLocacoes.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblPainelLocacoes.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		painelLocacoes.setLayout(gblPainelLocacoes);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 0);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 0;
		painelLocacoes.add(scrollPane, gbcScrollPane);

		tabelaLocacoes = new JTable();
		tabelaLocacoes.setModel(modeloTabela);
		scrollPane.setViewportView(tabelaLocacoes);

		JPanel painelBotoes = new JPanel();
		add(painelBotoes, BorderLayout.SOUTH);
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnEncerrarLocacao = new JButton("Encerrar Locação");
		painelBotoes.add(btnEncerrarLocacao);
	}

	private void configurarEventos() {
		btnEncerrarLocacao.addActionListener(e -> {
			int linhaSelecionada = tabelaLocacoes.getSelectedRow();

			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(this, "Selecione uma locação primeiro.");
				return;
			}

			InfoLocacao locacaoSelecionada = getLocacaoSelecionada();

			int confirmacao = JOptionPane.showConfirmDialog(
					this,
					"Deseja realmente encerrar esta locação?",
					"Confirmar encerramento",
					JOptionPane.YES_NO_OPTION
				);

			if (confirmacao != JOptionPane.YES_OPTION) {
				return;
			}

			controller.setLocacaoSelecionada(locacaoSelecionada);
			controller.encerrarLocacao();
		});
	}

	public void preencherTabela(List<InfoLocacao> locacoes) {
		this.locacoesAtuais = locacoes;
		modeloTabela.setRowCount(0);
		for (InfoLocacao l : locacoes) {
			modeloTabela.addRow(new Object[] {
				l.getTipoCarro(),
				l.getModeloCarro(),
				l.getDataInicio(),
				l.getDataFim()
			});
		}
	}

	private InfoLocacao getLocacaoSelecionada() {
		int linhaSelecionada = tabelaLocacoes.getSelectedRow();
		return locacoesAtuais.get(linhaSelecionada);
	}
	
	public void atualizar() {
	    controller.atualizarLocacoes();
	}

	public JTable getTabelaLocacoes() {
		return tabelaLocacoes;
	}
}