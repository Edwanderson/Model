package org.model.wrest.client;

import org.model.domain.TabModel;

public class TabModelTeste {
//listAll
	public static void main(String[] args) {
		TabModelClient client = new TabModelClient();
		client.listAll().forEach(a ->{
			System.out.println(a.toString());
		});
	}

//remover
//	public static void main(String[] args) {
//		TabModelClient client = new TabModelClient();
//		try {
//			System.out.println(client.remover("1"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//salvar
//	public static void main(String[] args) {
//		TabModelClient client = new TabModelClient();
//		try {
//			TabModel t = new TabModel();
//			t.setColumnmodel("Item 1");
//			client.salvar(t);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//atualizar
//	public static void main(String[] args) {
//		TabModelClient client = new TabModelClient();
//		try {
//			//find id
//			TabModel t = client.findId("3");
//			
//			//atualizar
//			t.setColumnmodel("TESTE 3");
//			client.salvar(t);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	


}
