package org.model.wrest.server;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.model.domain.TabModel;
import org.model.wbusiness.TabModelBO;

@Path("tabmodelapi")
public class TabModelAPI {

	@Inject
	private TabModelBO tabModelBO;

	/**
	 * API example, TabModel class
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listAll")
	public List<TabModel> listAll() throws Exception {
		return tabModelBO.listAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findColumnModel/{value}")
	public TabModel findColumnModel(@PathParam("value") String value) throws Exception {
		return tabModelBO.findColumnModel(value);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findId/{id}")
	public TabModel findId(@PathParam("id") String id) throws Exception {
		return tabModelBO.findId(new Long(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("salvar/{t}")
	public void salvar(TabModel t) throws Exception {
		tabModelBO.salvar(t);
	}

	@DELETE
	@Path("remover/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String remover(@PathParam("id") String id) throws Exception {
		tabModelBO.remover(tabModelBO.findId(new Long(id)));
		return "Removido com sucesso!";
	}

}
