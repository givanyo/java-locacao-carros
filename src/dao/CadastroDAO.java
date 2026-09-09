package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import util.Conexao;

public class CadastroDAO {

	public static final int SUCESSO = 0;
	public static final int USUARIO_JA_EXISTE = 1;
	public static final int ERRO = 2;

	public int cadastrarCliente(String nomeUsuario, String email, String senha,
			String nome, String telefone, String cnh, String cpf) {

		Connection conn = null;
		CallableStatement stmt = null;
		String sql = "CALL cadastrar_cliente(?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = Conexao.conectar();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, nomeUsuario);
			stmt.setString(2, email);
			stmt.setString(3, senha);
			stmt.setString(4, nome);
			stmt.setString(5, telefone);
			stmt.setString(6, cnh);
			stmt.setString(7, cpf);

			stmt.execute();
			return SUCESSO;
		} catch (SQLIntegrityConstraintViolationException e) {
			return USUARIO_JA_EXISTE;
		} catch (SQLException e) {
			e.printStackTrace();
			return ERRO;
		} finally {
			try {
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