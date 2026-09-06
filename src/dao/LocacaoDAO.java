package dao;
import util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.InfoLocacao;
import model.Usuario;

public class LocacaoDAO {
	private Usuario cliente;
	private InfoLocacao locacaoSelecionada;

	public LocacaoDAO(Usuario cliente) {
		this.cliente = cliente;
	}

	public void setLocacaoSelecionada(InfoLocacao locacaoSelecionada) {
		this.locacaoSelecionada = locacaoSelecionada;
	}

	public List<InfoLocacao> consultarLocacoes() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL selecionar_info_locacoes(?)";
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdUsuario());
			rs = stmt.executeQuery();
			List<InfoLocacao> locacoesBanco = consultarInfoLocacoes(rs);
			return locacoesBanco;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao consultar locações: " + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private List<InfoLocacao> consultarInfoLocacoes(ResultSet rs) {
		List<InfoLocacao> locacoesBanco = new ArrayList<>();
		try {
			while (rs.next()) {
				InfoLocacao infoLocacao = new InfoLocacao();

				infoLocacao.setIdLocacao(rs.getInt("id"));
				infoLocacao.setTipoCarro(rs.getString("tipo_carro"));
				infoLocacao.setModeloCarro(rs.getString("modelo"));

				Date dataInicio = rs.getDate("data_inicio");
				infoLocacao.setDataInicio(dataInicio != null ? dataInicio.toLocalDate() : null);

				Date dataFim = rs.getDate("data_fim");
				infoLocacao.setDataFim(dataFim != null ? dataFim.toLocalDate() : null);

				locacoesBanco.add(infoLocacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locacoesBanco;
	}

	public void encerrarLocacao() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "CALL cancelar_reserva(?)";
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, locacaoSelecionada.getIdLocacao());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encerrar locação: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}