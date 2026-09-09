package view;

import javax.swing.*;
import java.awt.BorderLayout;

import model.Usuario;

public class TelaCliente extends JPanel {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private JTabbedPane abasCliente;

	private TelaReservas telaReservas;
	private TelaLocacao telaLocacao;
	private TelaReservaCarro telaReservaCarro;
	
	public TelaCliente(Usuario usuario) {
		this.usuario = usuario;
		setLayout(new BorderLayout(0, 0));

		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		abasCliente = new JTabbedPane();
		add(abasCliente, BorderLayout.CENTER);

		telaReservas = new TelaReservas(usuario);
		abasCliente.addTab("Minhas Reservas", telaReservas);

		telaLocacao = new TelaLocacao(usuario);
		abasCliente.addTab("Minhas Locações", telaLocacao);
		
		telaReservaCarro = new TelaReservaCarro(usuario);
		abasCliente.addTab("Pré-reservar", telaReservaCarro);
	}

	private void configurarEventos() {
		abasCliente.addChangeListener(e -> {
			telaReservaCarro.setSendoExibida(false);
			if (abasCliente.getSelectedComponent() == telaReservas) {
				telaReservas.atualizar();
			}
 			if (abasCliente.getSelectedComponent() == telaLocacao) {
				telaLocacao.atualizar();
			}
			if(abasCliente.getSelectedComponent() == telaReservaCarro) {
				telaReservaCarro.atualizar();
				telaReservaCarro.setSendoExibida(true);
			}
		});
	}
}
