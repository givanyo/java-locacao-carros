package controller;
import model.InfoReserva;
import model.Usuario;
import view.TelaReservas;

import javax.swing.JOptionPane;

import dao.ReservasDAO;
public class ReservasController {
	private Usuario cliente;
	private ReservasDAO dao;
	private TelaReservas telaReservas;
	private InfoReserva reservaSelecionada;
	public ReservasController(Usuario cliente, TelaReservas telaReservas) {
		this.cliente = cliente;
		this.telaReservas = telaReservas;
		this.dao = new ReservasDAO(cliente);
		consultarReservas();
	}
	
	private void consultarReservas() {
		telaReservas.preencherTabela(dao.consultarReservas());
	}
	
	public void pagar(String valor) {
		dao.setReservaSelecionada(this.reservaSelecionada);
		dao.pagar(valor);
		consultarReservas();
	}
	
	public void efetivarLocacao() {
		dao.setReservaSelecionada(this.reservaSelecionada);
		dao.efetivarLocacao();
		consultarReservas();
		JOptionPane.showMessageDialog(telaReservas, "A sua reserva foi efetivada e já está disponível na aba Locações.");
	}
	
	public void setReservaSelecionada(InfoReserva reservaSelecionada) {
		this.reservaSelecionada = reservaSelecionada;
	}
}	
