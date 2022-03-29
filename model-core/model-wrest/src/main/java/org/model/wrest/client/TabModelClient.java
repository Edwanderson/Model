package org.model.wrest.client;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.model.domain.TabModel;
import org.model.domain.util.DateDeserializer;
import org.model.domain.util.DateSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Named
public class TabModelClient implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String HOSTNAME = "http://localhost:8080/"; // teste
	private final String API = "model-wrest/rest/";//
	private final String PATH = "tabmodelapi/";

	public List<TabModel> listAll() {
		Gson gson = new Gson();
		Client c = Client.create();
		// GET example
		WebResource wr = c.resource(HOSTNAME + API + PATH + "listAll/");
		String json = wr.get(String.class);
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
		List<TabModel> t = gson.fromJson(json, new TypeToken<List<TabModel>>() {
		}.getType());
		return t;
	}

	public TabModel findColumnModel(String value) {
		Gson gson = new Gson();
		Client c = Client.create();
		// GET example
		WebResource wr = c.resource(HOSTNAME + API + PATH + "findColumnModel/" + value);
		String json = wr.get(String.class);
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
		TabModel t = gson.fromJson(json, new TypeToken<TabModel>() {
		}.getType());
		return t;
	}

	public TabModel findId(String id) {
		Gson gson = new Gson();
		Client c = Client.create();
		// GET example
		WebResource wr = c.resource(HOSTNAME + API + PATH + "findId/" + id);
		String json = wr.get(String.class);
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
		TabModel t = gson.fromJson(json, new TypeToken<TabModel>() {
		}.getType());
		return t;
	}

	public void salvar(TabModel objeto) {
		Gson gson = new Gson();
		Client c = Client.create();
		WebResource wr = c.resource(HOSTNAME + API + PATH + "salvar/").path("objeto");
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
		wr.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,
				gson.toJson(objeto));
	}

	public String remover(String id) throws Exception {
		Client c = Client.create();
		WebResource wr = c.resource(HOSTNAME + API + PATH + "remover/" + id);
		String s = wr.accept(MediaType.APPLICATION_XML).delete(String.class);
		return s;
	}

}
