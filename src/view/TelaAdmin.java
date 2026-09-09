package view;

import java.awt.BorderLayout;

import javax.swing.*;

import model.Usuario;

public class TelaAdmin extends JPanel{
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private JTabbedPane abasAdmin;
	
	public TelaAdmin(Usuario usuario) {
		this.usuario = usuario;
		setLayout(new BorderLayout(0, 0));

		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		abasAdmin = new JTabbedPane();
		add(abasAdmin, BorderLayout.CENTER);
		abasAdmin.add(new TelaAdminCarro(usuario), "Gerenciar carros");
	}

	private void configurarEventos() {
		abasAdmin.addChangeListener(e -> {
		});
	}
}
