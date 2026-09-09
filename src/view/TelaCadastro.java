package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerCadastro;

public class TelaCadastro extends JPanel {
	private static final long serialVersionUID = 1L;

	private ControllerCadastro controller;

	private JTextField campoNomeUsuario;
	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JPasswordField campoConfirmarSenha;
	private JTextField campoNome;
	private JTextField campoTelefone;
	private JTextField campoCnh;
	private JTextField campoCpf;
	private JButton botaoCadastrar;

	public TelaCadastro() {
		this.controller = new ControllerCadastro(this);

		setLayout(new BorderLayout());

		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		JPanel painelFormulario = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		campoNomeUsuario = new JTextField(15);
		campoEmail = new JTextField(15);
		campoSenha = new JPasswordField(15);
		campoConfirmarSenha = new JPasswordField(15);
		campoNome = new JTextField(15);
		campoTelefone = new JTextField(11);
		campoCnh = new JTextField(11);
		campoCpf = new JTextField(11);

		int linha = 0;
		linha = adicionarCampo(painelFormulario, gbc, linha, "Nome de usuário:", campoNomeUsuario);
		linha = adicionarCampo(painelFormulario, gbc, linha, "E-mail:", campoEmail);
		linha = adicionarCampo(painelFormulario, gbc, linha, "Senha:", campoSenha);
		linha = adicionarCampo(painelFormulario, gbc, linha, "Confirmar senha:", campoConfirmarSenha);
		linha = adicionarCampo(painelFormulario, gbc, linha, "Nome completo:", campoNome);
		linha = adicionarCampo(painelFormulario, gbc, linha, "Telefone (DDD+número):", campoTelefone);
		linha = adicionarCampo(painelFormulario, gbc, linha, "CNH:", campoCnh);
		linha = adicionarCampo(painelFormulario, gbc, linha, "CPF:", campoCpf);

		botaoCadastrar = new JButton("Cadastrar");

		JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelBotao.add(botaoCadastrar);

		add(painelFormulario, BorderLayout.NORTH);
		add(painelBotao, BorderLayout.SOUTH);
	}

	private int adicionarCampo(JPanel painel, GridBagConstraints gbc, int linha, String rotulo, JTextField campo) {
		gbc.gridx = 0;
		gbc.gridy = linha;
		painel.add(new JLabel(rotulo), gbc);

		gbc.gridx = 1;
		painel.add(campo, gbc);

		return linha + 1;
	}

	private void configurarEventos() {
		botaoCadastrar.addActionListener(e -> cadastrar());
	}

	private void cadastrar() {
		String nomeUsuario = campoNomeUsuario.getText().trim();
		String email = campoEmail.getText().trim();
		String senha = new String(campoSenha.getPassword());
		String confirmarSenha = new String(campoConfirmarSenha.getPassword());
		String nome = campoNome.getText().trim();
		String telefone = campoTelefone.getText().trim();
		String cnh = campoCnh.getText().trim();
		String cpf = campoCpf.getText().trim();

		controller.cadastrar(nomeUsuario, email, senha, confirmarSenha, nome, telefone, cnh, cpf);
	}

	public void limparCampos() {
		campoNomeUsuario.setText("");
		campoEmail.setText("");
		campoSenha.setText("");
		campoConfirmarSenha.setText("");
		campoNome.setText("");
		campoTelefone.setText("");
		campoCnh.setText("");
		campoCpf.setText("");
	}
}