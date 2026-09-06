package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.List;

import model.InfoReserva;
import model.Usuario;
import controller.ReservasController;

public class TelaReservas extends JPanel {
	private JTable tabelaReservas;
	private DefaultTableModel modeloTabela;
	private Usuario usuario;
	private ReservasController controller;

	public TelaReservas(Usuario usuario) {
		setLayout(new BorderLayout(0, 0));
		this.usuario = usuario;
		this.modeloTabela = new DefaultTableModel(
				new Object[]{"Grupo", "Modelo", "Duração (Dias)", "Sinal", "Restante", "Total", "Status"}, 0
			) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.controller = new ReservasController(this.usuario, this);
		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		JPanel painelTitulo = new JPanel();
		add(painelTitulo, BorderLayout.NORTH);
		painelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitulo = new JLabel("Suas Reservas");
		painelTitulo.add(lblTitulo);

		JPanel painelReservas = new JPanel();
		add(painelReservas, BorderLayout.CENTER);
		GridBagLayout gblPainelReservas = new GridBagLayout();
		gblPainelReservas.columnWidths = new int[]{0, 0};
		gblPainelReservas.rowHeights = new int[]{0, 0, 0};
		gblPainelReservas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblPainelReservas.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		painelReservas.setLayout(gblPainelReservas);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 0);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 0;
		painelReservas.add(scrollPane, gbcScrollPane);

		tabelaReservas = new JTable();
		tabelaReservas.setModel(modeloTabela);
		scrollPane.setViewportView(tabelaReservas);
		
		JPanel painelBotoes = new JPanel();
		add(painelBotoes, BorderLayout.SOUTH);
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnPagarSinal = new JButton("Pagar Sinal");
		painelBotoes.add(btnPagarSinal);
		
		JButton btnNewButton_1 = new JButton("Pagar Restante");
		painelBotoes.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Iniciar Locação");
		painelBotoes.add(btnNewButton);
	}

	private void configurarEventos() {
	}

	public void preencherTabela(List<InfoReserva> reservas) {
		modeloTabela.setRowCount(0);

		for (InfoReserva r : reservas) {
			modeloTabela.addRow(new Object[] {
				r.getGrupoCarro(),
				r.getModeloCarro(),
				r.getDuracaoDias(),
				r.getValorSinal(),
				r.getRestante(),
				r.getValorTotal(),
				r.getStatusPagamento()
			});
		}
	}

	public JTable getTabelaReservas() {
		return tabelaReservas;
	}
}