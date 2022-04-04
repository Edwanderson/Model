package org.model.myapplicationmodel.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.service.ServiceFinder;

import org.model.myapplicationmodel.domain.TabModel;
import org.model.myapplicationmodel.util.AndroidServiceIteratorProvider;
import org.model.myapplicationmodel.util.DateDeserializer;
import org.model.myapplicationmodel.util.DateSerializer;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

public class ModelRestClient {

    private Gson gson = new Gson();

    // model url root
    private final String HOSTNAME = "http://192.168.2.55:8080/"; // teste
    private final String API = "model-wrest/rest/";//
    private final String PATH = "tabmodelapi/";


    public List<TabModel> listAll() {
        ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

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
        ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

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
        ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

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
        ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

        Gson gson = new Gson();
        Client c = Client.create();
        WebResource wr = c.resource(HOSTNAME + API + PATH + "salvar/").path("objeto");
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
        wr.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,
                gson.toJson(objeto));
    }

    public String remover(String id) throws Exception {
        ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

        Client c = Client.create();
        WebResource wr = c.resource(HOSTNAME + API + PATH + "remover/" + id);
        String s = wr.accept(MediaType.APPLICATION_XML).delete(String.class);
        return s;
    }

}
