package model;

public class InfoCarro {
	private int idCarro;
	private String tipoCarro;
	private String modeloCarro;
	private int pessoas;
	private float valorDiaria;
	private String placa;
	
	public int getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}
	public String getTipoCarro() {
		return tipoCarro;
	}
	public void setTipoCarro(String tipoCarro) {
		this.tipoCarro = tipoCarro;
	}
	public String getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public int getPessoas() {
		return pessoas;
	}
	public void setPessoas(int pessoas) {
		this.pessoas = pessoas;
	}
	public float getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(float valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
