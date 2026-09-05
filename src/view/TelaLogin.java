package view;
import controller.LoginController;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JPanel {
	private static final long serialVersionUID = 1L;
	private TelaPrincipal telaPrincipal;
	private LoginController controller;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnLogin;

	public TelaLogin(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		this.controller = new LoginController(telaPrincipal, this);
		setBorder(null);
		setLayout(new BorderLayout(0, 64));
		criarComponentes();
		configurarEventos();
	}

	private void criarComponentes() {
		JPanel painelTitulo = new JPanel();
		add(painelTitulo, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Sistema de Locação de Carros");
		painelTitulo.add(lblNewLabel);

		JPanel painelInputsBtn = new JPanel();
		painelInputsBtn.setBorder(new EmptyBorder(0, 64, 0, 64));
		add(painelInputsBtn, BorderLayout.CENTER);

		GridBagLayout gblPainelInputsBtn = new GridBagLayout();
		gblPainelInputsBtn.columnWidths = new int[]{0, 0};
		gblPainelInputsBtn.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gblPainelInputsBtn.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblPainelInputsBtn.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		painelInputsBtn.setLayout(gblPainelInputsBtn);

		JLabel lblLogin = new JLabel("Nome de Usuário/Email:");
		GridBagConstraints gbcLblLogin = new GridBagConstraints();
		gbcLblLogin.insets = new Insets(0, 0, 5, 0);
		gbcLblLogin.gridx = 0;
		gbcLblLogin.gridy = 0;
		painelInputsBtn.add(lblLogin, gbcLblLogin);

		txtLogin = new JTextField();
		GridBagConstraints gbcTxtLogin = new GridBagConstraints();
		gbcTxtLogin.insets = new Insets(0, 0, 5, 0);
		gbcTxtLogin.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtLogin.gridx = 0;
		gbcTxtLogin.gridy = 1;
		painelInputsBtn.add(txtLogin, gbcTxtLogin);
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		GridBagConstraints gbcLblSenha = new GridBagConstraints();
		gbcLblSenha.insets = new Insets(0, 0, 5, 0);
		gbcLblSenha.gridx = 0;
		gbcLblSenha.gridy = 3;
		painelInputsBtn.add(lblSenha, gbcLblSenha);

		txtSenha = new JPasswordField();
		GridBagConstraints gbcTxtSenha = new GridBagConstraints();
		gbcTxtSenha.insets = new Insets(0, 0, 5, 0);
		gbcTxtSenha.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtSenha.gridx = 0;
		gbcTxtSenha.gridy = 4;
		painelInputsBtn.add(txtSenha, gbcTxtSenha);
		txtSenha.setColumns(10);

		btnLogin = new JButton("Login");
		GridBagConstraints gbcBtnLogin = new GridBagConstraints();
		gbcBtnLogin.gridx = 0;
		gbcBtnLogin.gridy = 6;
		painelInputsBtn.add(btnLogin, gbcBtnLogin);
	}

	private void configurarEventos() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.consultar();
			}
		});
	}

	public String getTxtLogin() {
		return txtLogin.getText().trim();
	}

	public String getTxtSenha() {
		char[] senhaChars = txtSenha.getPassword();
		String senha = new String(senhaChars).trim();
		return senha;
	}
}