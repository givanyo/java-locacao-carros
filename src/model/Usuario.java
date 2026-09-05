package model;

public class Usuario {
	private int idUsuario;
	private String senha;
	private String login;
	private boolean adm;
	
	public Usuario() {
		
	}
	public Usuario(int idUsuario, boolean adm) {
		this.idUsuario = idUsuario;
		this.adm = adm;
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean getAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
