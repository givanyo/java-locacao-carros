package controller;

import javax.swing.JOptionPane;

import dao.CadastroDAO;
import view.TelaCadastro;

public class ControllerCadastro {
	private CadastroDAO cadastroDAO;
	private TelaCadastro telaCadastro;

	public ControllerCadastro(TelaCadastro telaCadastro) {
		this.telaCadastro = telaCadastro;
		this.cadastroDAO = new CadastroDAO();
	}

	public void cadastrar(String nomeUsuario, String email, String senha, String confirmarSenha,
			String nome, String telefone, String cnh, String cpf) {

		if (nomeUsuario == null || nomeUsuario.isBlank()) {
			JOptionPane.showMessageDialog(telaCadastro, "O nome de usuário é obrigatório.");
			return;
		}

		if (email == null || email.isBlank()) {
			JOptionPane.showMessageDialog(telaCadastro, "O e-mail é obrigatório.");
			return;
		}

		if (senha == null || senha.isBlank()) {
			JOptionPane.showMessageDialog(telaCadastro, "A senha é obrigatória.");
			return;
		}

		if (!senha.equals(confirmarSenha)) {
			JOptionPane.showMessageDialog(telaCadastro, "As senhas não coincidem.");
			return;
		}

		if (nome == null || nome.isBlank()) {
			JOptionPane.showMessageDialog(telaCadastro, "O nome completo é obrigatório.");
			return;
		}

		if (telefone == null || telefone.isBlank() || telefone.length() != 11) {
			JOptionPane.showMessageDialog(telaCadastro, "Telefone inválido (deve conter 11 dígitos, com DDD).");
			return;
		}

		if (cnh == null || cnh.isBlank() || cnh.length() != 11) {
			JOptionPane.showMessageDialog(telaCadastro, "CNH inválida (deve conter 11 dígitos).");
			return;
		}

		if (cpf == null || cpf.isBlank() || cpf.length() != 11) {
			JOptionPane.showMessageDialog(telaCadastro, "CPF inválido (deve conter 11 dígitos).");
			return;
		}

		int resultado = cadastroDAO.cadastrarCliente(nomeUsuario, email, senha, nome, telefone, cnh, cpf);

		switch (resultado) {
			case CadastroDAO.SUCESSO:
				JOptionPane.showMessageDialog(telaCadastro, "Cliente cadastrado com sucesso!");
				telaCadastro.limparCampos();
				break;
			case CadastroDAO.USUARIO_JA_EXISTE:
				JOptionPane.showMessageDialog(telaCadastro, "Já existe um cadastro com esse usuário, e-mail, CNH ou CPF.");
				break;
			case CadastroDAO.ERRO:
				JOptionPane.showMessageDialog(telaCadastro, "Erro ao cadastrar cliente.");
				break;
		}
	}
}