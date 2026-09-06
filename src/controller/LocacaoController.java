package controller;
import model.InfoLocacao;
import model.Usuario;
import view.TelaLocacao;
import javax.swing.JOptionPane;
import dao.LocacaoDAO;
public class LocacaoController {
	private Usuario cliente;
	private LocacaoDAO dao;
	private TelaLocacao telaLocacao;
	private InfoLocacao locacaoSelecionada;
	public LocacaoController(Usuario cliente, TelaLocacao telaLocacao) {
		this.cliente = cliente;
		this.telaLocacao = telaLocacao;
		this.dao = new LocacaoDAO(cliente);
		consultarLocacoes();
	}

	private void consultarLocacoes() {
		telaLocacao.preencherTabela(dao.consultarLocacoes());
	}

	public void encerrarLocacao() {
		dao.setLocacaoSelecionada(this.locacaoSelecionada);
		dao.encerrarLocacao();
		consultarLocacoes();
		JOptionPane.showMessageDialog(telaLocacao, "A locação foi encerrada com sucesso.");
	}
	
	public void atualizarLocacoes() {
	    consultarLocacoes();
	}
	
	public void setLocacaoSelecionada(InfoLocacao locacaoSelecionada) {
		this.locacaoSelecionada = locacaoSelecionada;
	}
}