package br.com.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.projeto.dao.PessoaDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Pessoa;
import br.com.projeto.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> carregarPessoas() {
		try {

			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar();

			Messages.addGlobalInfo("Pessoa Carregada com Sucesso!");
		} catch (RuntimeException ex) {

			Messages.addFlashGlobalError("Falha no momento de carregar pessoas!");
			ex.printStackTrace();
		}
		return pessoas;

	}

	public List<Usuario> carregarUsuarios() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.listar();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Falha no carregamento dos usuarios.");
			ex.printStackTrace();
		}
		return usuarios;

	}

	public void novo() {

		carregarPessoas();
		carregarUsuarios();
	}

	public void salvar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(usuario);
			novo();

			Messages.addGlobalInfo("Pessoa Salva com Sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Não foi possivel salvar a Pessoa");
			ex.printStackTrace();
		}

	}

}
