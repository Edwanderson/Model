package org.model.persistence.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class CRUDUtil<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Class<T> classe;
	private EntityManager em;

	public CRUDUtil(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}
	
	@Transacional
	public void adiciona(T t) {
		try {	
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}catch(Exception ex){ 
			em.clear();
			em.close();			
			throw ex; 
		}
	}
	
	@Transacional
	public void remove(T t) {
		try {
			em.getTransaction().begin();
			em.remove(em.merge(t));	
			em.getTransaction().commit();
		}catch(Exception ex){ 
			em.clear();
			em.close();			
			throw ex; 
		}
	}

	@Transacional
	public void atualiza(T t) {
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		}catch(Exception ex){ 
			em.clear();
			em.close();			
			throw ex; 
		}
	}
	
	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
		return lista;
	}

}
