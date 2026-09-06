package view;
import javax.swing.*;

import model.Usuario;
public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel telaExibida;
	private String tituloExibido;
	public static final String[] TELALOGIN = {"telaLogin", "Login"};
	public static final String[] TELARESERVAS = {"telaReservas", "Suas reservas"};
	
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
			return;
		}
	}
	
	public void trocarTelaReservas(Usuario usuario) {
		this.telaExibida = new TelaReservas(usuario);
		this.tituloExibido = "Reservas";
		atualizar();
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
