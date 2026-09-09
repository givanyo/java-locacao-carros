package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.CarroDAO;
import model.Categoria;
import model.InfoCarro;
import model.Usuario;
import view.TelaAdminCarro;

public class ControllerAdminCarro {
	private Usuario admin;
	private CarroDAO carroDAO;
	private TelaAdminCarro telaAdminCarro;

	public ControllerAdminCarro(Usuario admin, TelaAdminCarro telaAdminCarro) {
		this.admin = admin;
		this.telaAdminCarro = telaAdminCarro;
		this.carroDAO = new CarroDAO(admin);
	}

	public List<Categoria> buscarCategorias() {
		List<Categoria> categorias = carroDAO.consultarCategorias();
		if (categorias == null) {
			JOptionPane.showMessageDialog(telaAdminCarro, "Erro ao consultar categorias.");
		}
		return categorias;
	}

	public List<InfoCarro> buscarTodosCarros() {
		List<InfoCarro> carros = carroDAO.consultarTodosCarros();
		if (carros == null) {
			JOptionPane.showMessageDialog(telaAdminCarro, "Erro ao consultar carros.");
		}
		return carros;
	}

	public void cadastrarCarro(int idCategoria, String modelo, String placa) {
		if (modelo == null || modelo.isBlank()) {
			JOptionPane.showMessageDialog(telaAdminCarro, "O modelo do carro é obrigatório.");
			return;
		}

		if (placa == null || placa.isBlank()) {
			JOptionPane.showMessageDialog(telaAdminCarro, "A placa do carro é obrigatória.");
			return;
		}

		boolean carroInserido = carroDAO.adicionarCarro(idCategoria, modelo, placa);

		if (carroInserido == false) {
			JOptionPane.showMessageDialog(telaAdminCarro, "Erro ao cadastrar carro.");
			return;
		}

		JOptionPane.showMessageDialog(telaAdminCarro, "Carro cadastrado com sucesso!");
		telaAdminCarro.atualizar();
	}
}