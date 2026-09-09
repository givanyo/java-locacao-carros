package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import model.InfoCarro;
import model.Usuario;
import controller.ReservaCarroController;

public class TelaReservaCarro extends JPanel {
	private List<InfoCarro> carrosDisponiveis;
	private static final long serialVersionUID = 1L;
	private JTable tabelaLocacoes;
	private DefaultTableModel modeloTabela;
	private Usuario usuario;
	private ReservaCarroController controller;

	private JSpinner spinnerDataInicio;
	private JSpinner spinnerDataFim;
	private JButton btnLocar;

	private boolean dataInicioDefinida = false;
	private boolean dataFimDefinida = false;
	private boolean sendoExibida = false;

	public TelaReservaCarro(Usuario usuario) {
		setLayout(new BorderLayout(0, 0));
		this.usuario = usuario;
		this.modeloTabela = new DefaultTableModel(
				new Object[]{"Tipo", "Modelo", "Qtd. Pessoas", "Locação Diária (R$)"}, 0
			) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.controller = new ReservaCarroController(this.usuario, this);
		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		JPanel painelTitulo = new JPanel();
		add(painelTitulo, BorderLayout.NORTH);
		painelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitulo = new JLabel("Carros disponíveis para pré-reserva");
		painelTitulo.add(lblTitulo);

		JPanel painelReservas = new JPanel();
		add(painelReservas, BorderLayout.CENTER);
		GridBagLayout gblPainelReservas = new GridBagLayout();
		gblPainelReservas.columnWidths = new int[]{0, 0};
		gblPainelReservas.rowHeights = new int[]{0, 0, 0};
		gblPainelReservas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblPainelReservas.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		painelReservas.setLayout(gblPainelReservas);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 0);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 0;
		painelReservas.add(scrollPane, gbcScrollPane);

		tabelaLocacoes = new JTable();
		tabelaLocacoes.setModel(modeloTabela);
		scrollPane.setViewportView(tabelaLocacoes);

		JPanel painelCampos = new JPanel();
		GridBagConstraints gbcPainelCampos = new GridBagConstraints();
		gbcPainelCampos.fill = GridBagConstraints.HORIZONTAL;
		gbcPainelCampos.gridx = 0;
		gbcPainelCampos.gridy = 1;
		painelReservas.add(painelCampos, gbcPainelCampos);
		painelCampos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		spinnerDataInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinnerDataInicio.setEditor(new JSpinner.DateEditor(spinnerDataInicio, "dd/MM/yyyy"));

		spinnerDataFim = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinnerDataFim.setEditor(new JSpinner.DateEditor(spinnerDataFim, "dd/MM/yyyy"));

		painelCampos.add(new JLabel("Data de Início:"));
		painelCampos.add(spinnerDataInicio);
		painelCampos.add(new JLabel("Data de Fim:"));
		painelCampos.add(spinnerDataFim);

		JPanel painelBotoes = new JPanel();
		add(painelBotoes, BorderLayout.SOUTH);
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnLocar = new JButton("Locar Carro");
		painelBotoes.add(btnLocar);
	}

	private void configurarEventos() {
		spinnerDataInicio.addChangeListener(e -> {
			dataInicioDefinida = true;
			carregarCarrosDisponiveis();
		});

		spinnerDataFim.addChangeListener(e -> {
			dataFimDefinida = true;
			carregarCarrosDisponiveis();
		});

		btnLocar.addActionListener(e -> {

			int linhaSelecionada = tabelaLocacoes.getSelectedRow();

			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(this, "Selecione um carro para locar.");
				return;
			}

			InfoCarro carroSelecionado = getCarroSelecionado();
			LocalDate dataInicio = converterParaLocalDate(spinnerDataInicio);
			LocalDate dataFim = converterParaLocalDate(spinnerDataFim);

			controller.iniciarReserva(carroSelecionado, dataInicio, dataFim);
		});
	}

	private void carregarCarrosDisponiveis() {
		LocalDate dataInicio = converterParaLocalDate(spinnerDataInicio);
		LocalDate dataFim = converterParaLocalDate(spinnerDataFim);
		controller.setDataInicio(dataInicio);
		controller.setDataFim(dataFim);
		List<InfoCarro> carros = controller.buscarCarrosDisponiveis();

		if (carros != null) {
			preencherTabela(carros);
			return;
		}

		if (periodoDefinido()) {
			JOptionPane.showMessageDialog(this, "Não há nenhum carro disponível no período definido.");
		}

		preencherTabela(new ArrayList<>());
	}

	public void atualizar() {
		carregarCarrosDisponiveis();
	}

	public void preencherTabela(List<InfoCarro> carros) {
		this.carrosDisponiveis = carros;
		modeloTabela.setRowCount(0);
		for (InfoCarro c : carros) {
			modeloTabela.addRow(new Object[] {
				c.getTipoCarro(),
				c.getModeloCarro(),
				c.getPessoas(),
				c.getValorDiaria()
			});
		}
	}

	private boolean periodoDefinido() {
		return dataInicioDefinida && dataFimDefinida;
	}

	private LocalDate converterParaLocalDate(JSpinner spinner) {
		Date data = (Date) spinner.getValue();
		return new java.sql.Date(data.getTime()).toLocalDate();
	}

	private InfoCarro getCarroSelecionado() {
		int linhaSelecionada = tabelaLocacoes.getSelectedRow();
		return carrosDisponiveis.get(linhaSelecionada);
	}

	public JTable getTabelaLocacoes() {
		return tabelaLocacoes;
	}

	public boolean getSendoExibida() {
		return this.sendoExibida;
	}

	public void setSendoExibida(boolean sendoExibida) {
		this.sendoExibida = sendoExibida;
	}
}