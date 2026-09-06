package dao;
import model.InfoReserva;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Conexao;

public class ReservasDAO {
	private Usuario cliente;
	private InfoReserva reservaSelecionada;
	public ReservasDAO(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public List<InfoReserva> consultarReservas() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL selecionar_info_reservas(?)";
        try {
        	conn = Conexao.conectar();
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, cliente.getIdUsuario());
        	rs = stmt.executeQuery();
        	List<InfoReserva>reservasBanco = consultarInfoReservas(stmt, rs);
        	return reservasBanco;
        } catch(Exception e) {
        	throw new RuntimeException("Erro ao consultar reservas: " + e.getMessage());
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
	
	private List<InfoReserva> consultarInfoReservas(PreparedStatement stmt, ResultSet rs) {
		List<InfoReserva> reservasBanco = new ArrayList<>();
		try {			
			while(rs.next()) {
				InfoReserva infoReserva = new InfoReserva();
				
				infoReserva.setIdReserva(rs.getInt("id"));
				infoReserva.setGrupoCarro(rs.getString("grupo"));
				infoReserva.setModeloCarro(rs.getString("modelo"));
				
				infoReserva.setDuracaoDias(rs.getInt("duracao_dias"));
				
				infoReserva.setValorSinal(rs.getFloat("sinal"));
				infoReserva.setRestante(rs.getFloat("valor_restante"));
				infoReserva.setValorTotal(rs.getFloat("valor_total"));
				
				Date dataPagSinal = rs.getDate("data_pag_sinal");
				infoReserva.setDataPagSinal(dataPagSinal != null ? dataPagSinal.toLocalDate() : null);

				Date dataPagRestante = rs.getDate("data_pag_restante");
				infoReserva.setDataPagRestante(dataPagRestante != null ? dataPagRestante.toLocalDate() : null);
				
				reservasBanco.add(infoReserva);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservasBanco;
	}
	
	public void pagar(String valor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL pagar_" + valor + "(?)";
        try {
        	conn = Conexao.conectar();
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, reservaSelecionada.getIdReserva());
        	rs = stmt.executeQuery();
        } catch(Exception e) {
        	throw new RuntimeException("Erro ao consultar reservas: " + e.getMessage());
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
	
	public void efetivarLocacao() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "CALL gerar_locacao(?)";
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reservaSelecionada.getIdReserva());
			rs = stmt.executeQuery();
		} catch(Exception e) {
			throw new RuntimeException("Erro ao iniciar locação: " + e.getMessage());
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
	
	public void setReservaSelecionada(InfoReserva reservaSelecionada) {
		this.reservaSelecionada = reservaSelecionada;
	}
}
