package org.model.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.model.domain.TabModel;
import org.model.wbusiness.TabModelBO;
import org.model.web.util.FacesUtil;
import javax.faces.event.ActionEvent;

@Named
@ViewScoped
public class TabModelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * var
	 */
	private TabModel tabModel;
	private List<TabModel> tabModellist;
	private List<TabModel> tabModellistFilter;

	/**
	 * getters and setters
	 */
	public TabModel getTabModel() {
		if (tabModel == null) {
			tabModel = new TabModel();
		}
		return tabModel;
	}

	public void setTabModel(TabModel tabModel) {
		this.tabModel = tabModel;
	}

	public List<TabModel> getTabModellist() {
		return tabModellist;
	}

	public void setTabModellist(List<TabModel> tabModellist) {
		this.tabModellist = tabModellist;
	}

	public List<TabModel> getTabModellistFilter() {
		return tabModellistFilter;
	}

	public void setTabModellistFilter(List<TabModel> tabModellistFilter) {
		this.tabModellistFilter = tabModellistFilter;
	}

	/**
	 * inject
	 */
	@Inject
	private TabModelBO tabModelBO;

	/**
	 * method
	 */
	public void listAll() {
		try {
			// list
			tabModellist = tabModelBO.listAll();
		} catch (Exception e) {
			tabModellist = new ArrayList<TabModel>();
		}
	}

	public void save(ActionEvent event) {
		// test
		if (tabModel.getColumnmodel().length() == 0) {
			FacesUtil.addMsgErro();
			return;
		}
		// save
		try {
			tabModelBO.salvar(tabModel);
			FacesUtil.addMsgSucessfull();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tabModel = new TabModel();
			FacesUtil.addMsgErro();
		}
	}

	public void selectItem(TabModel t) {
		this.tabModel = t;
	}
	
	public void newItem(TabModel t) {
		this.tabModel = new TabModel();
	}

	public void delete(TabModel t) {
		try {
			tabModelBO.remover(t);
			FacesUtil.addMsgSucessfull();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesUtil.addMsgErro();
		}
	}
	
	public String refreshPage() {
		return "/pages/model.xhtml?faces-redirect=true";
	}

}
