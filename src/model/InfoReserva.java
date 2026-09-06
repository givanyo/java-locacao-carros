package model;

import java.time.LocalDate;

public class InfoReserva {
	private int idReserva;
	private String grupoCarro;
	private String modeloCarro;
	private int duracaoDias;
	private float valorSinal;
	private float restante;
	private float valorTotal;
	private LocalDate dataPagSinal;
	private LocalDate dataPagRestante;
	public String getStatusPagamento() {
		
		if (dataPagSinal == null && dataPagRestante == null) {
			return "A pagar";
		}
		if (dataPagSinal != null && dataPagRestante == null) {
			return "Sinal Pago";
		}
		
		if (dataPagSinal == null && dataPagRestante != null) {
			return "Sinal Pendente";
		}
		
		return "Pago";
		
	}
	public String getGrupoCarro() {
		return grupoCarro;
	}
	public void setGrupoCarro(String grupoCarro) {
		this.grupoCarro = grupoCarro;
	}
	public String getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public int getDuracaoDias() {
		return duracaoDias;
	}
	public void setDuracaoDias(int duracaoDias) {
		this.duracaoDias = duracaoDias;
	}
	public float getValorSinal() {
		return valorSinal;
	}
	public void setValorSinal(float valorSinal) {
		this.valorSinal = valorSinal;
	}
	public float getRestante() {
		return restante;
	}
	public void setRestante(float restante) {
		this.restante = restante;
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
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
}
