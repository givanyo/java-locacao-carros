package view;
import javax.swing.*;

import model.Usuario;
public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel telaExibida;
	private String tituloExibido;
	public static final String[] TELALOGIN = {"telaLogin", "Login"};
	public static final String[] TELACLIENTE = {"telaCliente", "Sistema de Locação de Carros (CLIENTE)"};
	public static final String[] TELARESERVAS = {"telaReservas", "Suas reservas"};
	public static final String[] TELAADMIN = {"telaAdmin", "Sistema de Locação de Carros (ADMIN)"};
	
	public TelaPrincipal() {
		setTitle("Carregando");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void trocarTela(String[] telaInfo) {
		if (telaInfo[0].equals(TELALOGIN[0])) {
			this.telaExibida = new TelaLogin(this);
			this.tituloExibido = TELALOGIN[1];
			atualizar();
			setSize(600, 400);
			return;
		}
	}
	
	public void trocarTela(Usuario usuario) {
		if(usuario.getAdm() == true) {
			this.telaExibida = new TelaAdmin(usuario);
			this.tituloExibido = TELAADMIN[1];
		} else {
			this.telaExibida = new TelaCliente(usuario);
			this.tituloExibido = TELACLIENTE[1];
		}
		atualizar();
		setSize(800, 480);
		return;
	}
	
	private void atualizar() {
		getContentPane().removeAll();
		getContentPane().add(telaExibida);
		setTitle(tituloExibido);
		revalidate();
		repaint();
	}
}
