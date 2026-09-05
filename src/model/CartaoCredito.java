package model;

public class CartaoCredito {
	private int idCartaoCredito;
	private int idCliente;
	private String nomeCartao;
	private String numeroCartao;
	private float limite;
	
	public int getIdCartaoCredito() {
		return idCartaoCredito;
	}
	public void setIdCartaoCredito(int idCartaoCredito) {
		this.idCartaoCredito = idCartaoCredito;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public float getLimite() {
		return limite;
	}
	public void setLimite(float limite) {
		this.limite = limite;
	}
}
