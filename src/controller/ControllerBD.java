//package controller;
//
//import java.util.Date;
//import java.util.HashMap;
//
//import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;
//
//import model.Atividade;
//import model.NodeAtividade;
//
//public class ControllerBD {
//	
//	
//	@SuppressWarnings("deprecation")
//	public static HashMap<String, NodeAtividade> getAtividadesData() throws AtividadeException{
//		HashMap<String, NodeAtividade> mapaTemp = new HashMap<String, NodeAtividade>();
//	
//		Atividade atv1 = new Atividade("LES", 120, new Date(2014, 5, 29) );
//		NodeAtividade node1 = new NodeAtividade(atv1);
//		mapaTemp.put(atv1.getTitulo(), node1);
//		
//		Atividade atv2 = new Atividade("LES", 90, new Date(2014, 5, 28) );
//		NodeAtividade node2 = new NodeAtividade(atv2);
//		node1.setNext(node2);
//		
//		Atividade atv3 = new Atividade("LES", 60, new Date(2014, 5, 27) );
//		NodeAtividade node3 = new NodeAtividade(atv3);
//		node2.setNext(node3);
//		
//		Atividade atv4 = new Atividade("Academia", 150, new Date(2014, 5, 27) );
//		NodeAtividade node4 = new NodeAtividade(atv4);
//		mapaTemp.put(atv4.getTitulo(), node4);
//		
//		Atividade atv5 = new Atividade("Academia", 120, new Date(2014, 5, 26) );
//		NodeAtividade node5 = new NodeAtividade(atv5);
//		node4.setNext(node5);
//		
//		Atividade atv6 = new Atividade("Namorada", 60, new Date(2014, 5, 25) );
//		NodeAtividade node6 = new NodeAtividade(atv4);
//		mapaTemp.put(atv6.getTitulo(), node6);
//		
//		Atividade atv7 = new Atividade("Namorada", 120, new Date(2014, 5, 24) );
//		NodeAtividade node7 = new NodeAtividade(atv7);
//		node6.setNext(node7);
//		
//		return mapaTemp;
//		
//	}
//
//}
