package model;

import java.time.LocalDate;

public class Transacao {
	private int id;
	private int idPreReserva;
	private float sinal;
	private float valorRestante;
	private float valorTotal;
	private LocalDate dataPagSinal;
	private LocalDate dataPagRestante;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPreReserva() {
		return idPreReserva;
	}
	public void setIdPreReserva(int idPreReserva) {
		this.idPreReserva = idPreReserva;
	}
	public float getSinal() {
		return sinal;
	}
	public void setSinal(float sinal) {
		this.sinal = sinal;
	}
	public float getValorRestante() {
		return valorRestante;
	}
	public void setValorRestante(float valorRestante) {
		this.valorRestante = valorRestante;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getDataPagSinal() {
		return dataPagSinal;
	}
	public void setDataPagSinal(LocalDate dataPagSinal) {
		this.dataPagSinal = dataPagSinal;
	}
	public LocalDate getDataPagRestante() {
		return dataPagRestante;
	}
	public void setDataPagRestante(LocalDate dataPagRestante) {
		this.dataPagRestante = dataPagRestante;
	}
}
