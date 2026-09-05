package model;

import java.time.LocalDate;

public class PreReserva {
	private int idPreReserva;
	private int idCliente;
	private int idCarro;
	private LocalDate previsaoInicio;
	private int duracaoDias;
	
	public int getIdPreReserva() {
		return idPreReserva;
	}
	public void setIdPreReserva(int idPreReserva) {
		this.idPreReserva = idPreReserva;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}
	public LocalDate getPrevisaoInicio() {
		return previsaoInicio;
	}
	public void setPrevisaoInicio(LocalDate previsaoInicio) {
		this.previsaoInicio = previsaoInicio;
	}
	public int getDuracaoDias() {
		return duracaoDias;
	}
	public void setDuracaoDias(int duracaoDias) {
		this.duracaoDias = duracaoDias;
	}
}
