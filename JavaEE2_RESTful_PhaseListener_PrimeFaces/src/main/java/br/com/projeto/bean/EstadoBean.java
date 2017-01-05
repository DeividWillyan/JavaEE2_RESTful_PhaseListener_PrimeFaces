package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.dao.EstadoDAO;
import br.com.projeto.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean()
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			dao.merge(estado);

			novo();
			estados = dao.listar();

			Messages.addGlobalInfo("Estado salvo com sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao salvar estado.");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {

		try {
			EstadoDAO dao = new EstadoDAO();
			estados = dao.listar();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao listar estado.");
			ex.printStackTrace();
		}

	}

	public void excluir(ActionEvent actionEvent) {
		try {
			estado = (Estado) actionEvent.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDAO dao = new EstadoDAO();
			dao.excluir(estado);
			estados = dao.listar();

			Messages.addGlobalInfo("Estado excluido com sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao excluir o estado.");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			estado = (Estado) actionEvent.getComponent().getAttributes().get("estadoSelecionado");
			Messages.addGlobalInfo("Estado Editado com sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao editar Cidade");
			ex.printStackTrace();
		}

	}

}
