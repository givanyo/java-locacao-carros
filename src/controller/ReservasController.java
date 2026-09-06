package controller;
import model.Usuario;
import view.TelaReservas;
import dao.ReservasDAO;
public class ReservasController {
	Usuario cliente;
	ReservasDAO dao;
	TelaReservas telaReservas;
	public ReservasController(Usuario cliente, TelaReservas telaReservas) {
		this.cliente = cliente;
		this.telaReservas = telaReservas;
		this.dao = new ReservasDAO(cliente);
		consultarReservas();
	}
	
	private void consultarReservas() {
		telaReservas.preencherTabela(dao.consultarReservas());
	}
}	
