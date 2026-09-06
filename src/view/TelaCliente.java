package view;

import javax.swing.*;
import java.awt.BorderLayout;

import model.Usuario;

public class TelaCliente extends JPanel {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private JTabbedPane abasCliente;

	private TelaReservas telaReservas;

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

	}

	private void configurarEventos() {
	}
}