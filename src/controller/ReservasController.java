package controller;
import model.Usuario;
import view.TelaPrincipal;
import dao.ReservasDAO;
public class ReservasController {
	Usuario cliente;
	TelaPrincipal telaPrincipal;
	ReservasDAO dao;
	public ReservasController(Usuario cliente, TelaPrincipal telaPrincipal) {
		this.cliente = cliente;
		this.telaPrincipal = telaPrincipal;
		this.dao = new ReservasDAO(cliente);
	}
	
	private void consultarReservas() {
		dao.consultarReservas();
	}
}	
