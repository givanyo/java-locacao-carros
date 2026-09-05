package controller;
import view.TelaPrincipal;
import view.TelaLogin;
import model.Usuario;

import javax.swing.JOptionPane;

import dao.LoginDAO;
public class LoginController {
	private TelaPrincipal telaPrincipal;
	private TelaLogin telaLogin;
	private Usuario login;
	private LoginDAO dao;
	
	public LoginController(TelaPrincipal telaPrincipal, TelaLogin telaLogin) {
		this.telaPrincipal = telaPrincipal;
		this.telaLogin = telaLogin;
		this.login = new Usuario();
		this.dao = new LoginDAO(login);
	}
	public void consultar() {
		String tentativaLogin = telaLogin.getTxtLogin();
		String senha = telaLogin.getTxtSenha();
		login.setLogin(tentativaLogin);
		login.setSenha(senha);
		
		Usuario usuario = dao.consultar();
		if(usuario == null) {
			System.out.println("Usuário não encontrado");
		     JOptionPane.showMessageDialog(
	                    telaLogin,
	                    "As informações de login estão incorretas ou o usuário não existe.",
	                    "Usuário não encontrado",
	                    JOptionPane.WARNING_MESSAGE
	             );
			return;
		}
		
		System.out.println("O usuário existe: " + "id: " + usuario.getIdUsuario() + ", é adm: " + usuario.getAdm());
		
		// telaPrincipal.trocarTela(tela de reservas, usuario);
	}
	
	
}
