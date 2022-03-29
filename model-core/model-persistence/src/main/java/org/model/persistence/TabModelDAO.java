package org.model.persistence;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.model.domain.TabModel;
import org.model.persistence.util.CRUDUtil;

public class TabModelDAO  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private CRUDUtil<TabModel> crudUtil = new CRUDUtil<TabModel>(em, null);

	@PostConstruct
	void init() {
		this.crudUtil = new CRUDUtil<TabModel>(this.em, TabModel.class);
	}
	
	public CRUDUtil<TabModel> crudUtil(){
		return crudUtil;
	}
	
	/**
	 *customization 
	 */
	@SuppressWarnings("unchecked")
	public List<TabModel> ListAll() {
		try {
			return 
			(List<TabModel>) em.createNamedQuery("TabModel.listAll")
					.getResultList();
		} catch (NoResultException e) {
			e.getStackTrace();
			return null;
		}
	}
	public TabModel findColumnModel(String value) {
		try {
			return 
			(TabModel) em.createNamedQuery("TabModel.findColumnModel")
					.getSingleResult();
		} catch (NoResultException e) {
			e.getStackTrace();
			return null;
		}
	}
	
}
