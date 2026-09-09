package dao;

import model.InfoCarro;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import util.Conexao;

public class CarroDAO {
	private Usuario cliente;

	public CarroDAO(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<InfoCarro> consultarCarros(LocalDate dataInicio, LocalDate dataFim) {
		List<InfoCarro> carros = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL selecionar_carros_disponiveis(?, ?)";

		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
			stmt.setDate(2, java.sql.Date.valueOf(dataFim));
			rs = stmt.executeQuery();

			while (rs.next()) {
				InfoCarro carro = new InfoCarro();
				carro.setIdCarro(rs.getInt("id_carro"));
				carro.setTipoCarro(rs.getString("tipo_carro"));
				carro.setModeloCarro(rs.getString("modelo"));
				carro.setPessoas(rs.getInt("pessoas"));
				carro.setValorDiaria(rs.getFloat("valor_diaria"));
				carros.add(carro);
			}
			return carros;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();}
			}
		}
	public boolean adicionarCarro(int idCategoria, String modelo, String placa) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql = "INSERT INTO carro (id_categoria, modelo, placa) VALUES (?, ?, ?)";

	    try {
	        conn = Conexao.conectar();
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idCategoria);
	        stmt.setString(2, modelo);
	        stmt.setString(3, placa);

	        int linhasAfetadas = stmt.executeUpdate();
	        return linhasAfetadas > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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