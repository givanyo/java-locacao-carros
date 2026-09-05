package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Usuario;
import util.Conexao;
public class LoginDAO {
	private Usuario tentativaLogin;
	
	public LoginDAO(Usuario tentativaLogin) {
		this.tentativaLogin = tentativaLogin;
	}
	
	public Usuario consultar() {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        String sql = "SELECT id, adm FROM usuario WHERE (nome_usuario = ? OR email = ?) AND senha = ? LIMIT 1";
	        try {
	        	conn = Conexao.conectar();
	        	stmt = conn.prepareStatement(sql);
	        	stmt.setString(1, tentativaLogin.getLogin());
	        	stmt.setString(2, tentativaLogin.getLogin());
	        	stmt.setString(3, tentativaLogin.getSenha());
	        	rs = stmt.executeQuery();
	        	if(rs.next()) {
	        		int id = rs.getInt("id");
	        		boolean adm = rs.getBoolean("adm");
	        		return new Usuario(id, adm);
	        	}
	        	else {
	        		return null;
	        	}
	        } catch(Exception e) {
	        	throw new RuntimeException("Erro ao consultar usuários: " + e.getMessage());
	        } finally { 
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			
	}
}