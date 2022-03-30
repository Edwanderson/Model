package org.model.web.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {
	
	//Msg Sucesso
	public static void addInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(
		FacesMessage.SEVERITY_INFO, "", mensagem);		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}
	
	public static void addMsgSucessfull() {
		FacesMessage facesMessage = new FacesMessage(
		FacesMessage.SEVERITY_INFO, "", "Sucessfull!");		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}

	//Msg Erro
	public static void addErro(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(
		FacesMessage.SEVERITY_ERROR, "", mensagem);		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}	
	public static void addMsgErro() {
		FacesMessage facesMessage = new FacesMessage(
		FacesMessage.SEVERITY_ERROR, "", "Error when trying to finish");		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}
	
	public static void addMsgAlert(String titulo, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(
		FacesMessage.SEVERITY_WARN, titulo, mensagem);		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}

	public static String getParam(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, String> parametros = externalContext.getRequestParameterMap();
		String valor = parametros.get(nome);
		return valor;
	}
}
