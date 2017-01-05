package br.com.projeto.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.domain.Cidade;
import br.com.projeto.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {

		EstadoDAO dao = new EstadoDAO();
		System.out.println(dao.listar().size());

		for (Estado e : dao.listar())
			System.out.println(e.getNome() + " " + e.getSigla());
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		EstadoDAO dao = new EstadoDAO();
		Estado e = dao.buscar(codigo);

		System.out.println(e.getSigla() + " - " + e.getNome());
	}
	
	@Test
	public void lista() {
		
	Long estadoCodigo = 8L;
		
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		 for(Cidade e : cidadeDAO.buscarPorEstado(estadoCodigo))	{
			 System.out.println(e.getNome());
			 System.out.println(e.getCodigo());
			 System.out.println(e.getEstado());
			 System.out.println();
		 }
			 
	}
	

}
