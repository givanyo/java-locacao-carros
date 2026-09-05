package app;

import javax.swing.SwingUtilities;

import view.TelaPrincipal;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);
                tela.trocarTela(TelaPrincipal.TELALOGIN);
            }
        });
	}
}
