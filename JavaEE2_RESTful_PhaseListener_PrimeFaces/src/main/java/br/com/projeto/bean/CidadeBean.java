package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.dao.CidadeDAO;
import br.com.projeto.dao.EstadoDAO;
import br.com.projeto.domain.Cidade;
import br.com.projeto.domain.Estado;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void novo() {
		cidade = new Cidade();
		try {
			EstadoDAO dao = new EstadoDAO();
			estados = dao.listar();
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao Listar estados.");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO dao = new CidadeDAO();
			cidades = dao.listar();
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tenta listar as cidades.");
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO dao = new CidadeDAO();
			dao.merge(cidade);

			cidade = new Cidade();
			EstadoDAO dao2 = new EstadoDAO();
			estados = dao2.listar();
			cidades = dao.listar();

			Messages.addGlobalInfo("Cidade  Salva com sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tenta salvar as cidades.");
			ex.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {

		try {
			cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeDAO dao = new CidadeDAO();
			dao.excluir(cidade);
			cidades = dao.listar();

			Messages.addGlobalInfo("Cidade excluida com sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao excluir a cidade.");
			ex.printStackTrace();
		}

	}

	public void editar(ActionEvent actionEvent) {
		try {
			cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidadeSelecionada");

			EstadoDAO dao = new EstadoDAO();
			estados = dao.listar();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao editar a cidade.");
			ex.printStackTrace();
		}
	}

}
