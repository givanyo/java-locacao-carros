package controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import dao.CarroDAO;
import dao.ReservasDAO;
import model.InfoCarro;
import model.Usuario;
import view.TelaReservaCarro;

public class ReservaCarroController {
	private Usuario cliente;
	private CarroDAO carroDAO;
	private ReservasDAO reservasDAO;
	private TelaReservaCarro telaReservaCarro;
	private LocalDate dataInicio;
	private LocalDate dataFim;

	public ReservaCarroController(Usuario cliente, TelaReservaCarro telaReservaCarro) {
		this.cliente = cliente;
		this.telaReservaCarro = telaReservaCarro;
		this.carroDAO = new CarroDAO(cliente);
		this.reservasDAO = new ReservasDAO(cliente);
	}

	public void iniciarReserva(InfoCarro carroSelecionado, LocalDate dataInicio, LocalDate dataFim) {
		if (dataInicio.isBefore(LocalDate.now())) {
			JOptionPane.showMessageDialog(telaReservaCarro, "A data de início não pode ser no passado.");
			return;
		}

		if (!dataFim.isAfter(dataInicio)) {
			JOptionPane.showMessageDialog(telaReservaCarro, "A data de fim deve ser após a data de início.");
			return;
		}

		reservasDAO.setCarroSelecionado(carroSelecionado);
		reservasDAO.criarPreReserva(dataInicio, dataFim);

		JOptionPane.showMessageDialog(telaReservaCarro, "Pré-reserva criada com sucesso!");
		telaReservaCarro.atualizar();
	}

	public List<InfoCarro> buscarCarrosDisponiveis() {
		if (dataInicio.isAfter(dataFim) && telaReservaCarro.getSendoExibida()) {
			JOptionPane.showMessageDialog(telaReservaCarro, "A data de fim da locação deve ser após a data de início.");
			return null;
		}
		return carroDAO.consultarCarros(dataInicio, dataFim);
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
}