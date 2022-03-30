package org.model.wbusiness;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.model.domain.TabModel;
import org.model.persistence.TabModelDAO;

@Named
public class TabModelBO implements Serializable {
	private static final long serialVersionUID = 1821506831300771273L;

	@Inject
	private TabModelDAO tabModelDAO;
	
	/**
	 * BO example, TabModel class
	 * */
	public List<TabModel> listAll() throws Exception{
		return tabModelDAO.ListAll();
	}
	public TabModel findColumnModel(String value) {
		return tabModelDAO.findColumnModel(value);
	}
	public TabModel findId(Long id) throws Exception{
		return tabModelDAO.crudUtil().buscaPorId(id);
	}
	public void salvar(TabModel t) throws Exception{
		// set to Uppercase
		t.setColumnmodel(t.getColumnmodel().toUpperCase());
		
		if (t.getId() == null) {
			tabModelDAO.crudUtil().adiciona(t);
		}else {
			tabModelDAO.crudUtil().atualiza(t);
		}
	}
	public void remover(TabModel t) throws Exception{
		tabModelDAO.crudUtil().remove(t);
	}	
	
	
	
}
