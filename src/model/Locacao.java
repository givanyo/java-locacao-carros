package model;

import java.time.LocalDate;

public class Locacao {
	private int idLocacao;
	private int idPreReserva;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String status;
	
	public int getIdLocacao() {
		return idLocacao;
	}
	public void setIdLocacao(int idLocacao) {
		this.idLocacao = idLocacao;
	}
	public int getIdPreReserva() {
		return idPreReserva;
	}
	public void setIdPreReserva(int idPreReserva) {
		this.idPreReserva = idPreReserva;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
