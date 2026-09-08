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

public class ReservaCarroDAO {
	private Usuario cliente;
	private InfoCarro carroSelecionado;

	public ReservaCarroDAO(Usuario cliente) {
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

	public void criarPreReserva(LocalDate dataInicio, LocalDate dataFim) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL criar_pre_reserva(?, ?, ?, ?)";

		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdUsuario());
			stmt.setInt(2, carroSelecionado.getIdCarro());
			stmt.setDate(3, java.sql.Date.valueOf(dataInicio));
			stmt.setDate(4, java.sql.Date.valueOf(dataFim));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public void setCarroSelecionado(InfoCarro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

}