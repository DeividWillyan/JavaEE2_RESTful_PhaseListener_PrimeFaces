package br.com.projeto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.dao.CidadeDAO;
import br.com.projeto.dao.EstadoDAO;
import br.com.projeto.dao.PessoaDAO;
import br.com.projeto.domain.Cidade;
import br.com.projeto.domain.Estado;
import br.com.projeto.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean()
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PostConstruct
	public List<Pessoa> listar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Não foi possivel listar as pessoas.");
			ex.printStackTrace();
		}
		return pessoas;
	}

	public void novo() {
		pessoa = new Pessoa();
		// this.listar();

		EstadoDAO estadoDAO = new EstadoDAO();
		estados = estadoDAO.listar();

		cidades = new ArrayList<Cidade>();

	}

	public void salvar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.merge(pessoa);

			novo();

			Messages.addGlobalInfo("Pessoa Salva com Sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Não foi possivel salvar a Pessoa");
			ex.printStackTrace();
		}

	}

	public void editar(ActionEvent actionEvent) {
		try {
			pessoa = (Pessoa) actionEvent.getComponent().getAttributes().get("pessoaSelecionada");
			cidades = new CidadeDAO().listar();
			Messages.addGlobalInfo("Pessoa editada com sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro no momento da edição da pessoa");
			ex.printStackTrace();
		}
	}

	public void exluir(ActionEvent actionEvent) {
		try {
			pessoa = (Pessoa) actionEvent.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO dao = new PessoaDAO();
			dao.excluir(pessoa);

			Messages.addGlobalInfo("Pessoa Excluida com sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro no momento da exclusão da pessoa");
			ex.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			}else{
				cidades = new ArrayList<>();
			}

		} catch (RuntimeException ex) {
			Messages.addGlobalError("OCorreu um erro ao tentar filtras as cidades");
			ex.printStackTrace();
		}

	}

}
