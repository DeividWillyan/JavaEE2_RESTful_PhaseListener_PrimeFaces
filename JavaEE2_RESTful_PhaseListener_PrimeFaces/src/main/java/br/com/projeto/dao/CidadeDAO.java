package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Cidade;
import br.com.projeto.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = session.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("Nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
	}

}
