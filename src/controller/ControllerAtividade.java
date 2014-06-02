package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import model.Atividade;
import model.NodeAtividade;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

//Refactoring 1: esta classe deverá ser singleton.
public class ControllerAtividade {

	private HashMap<String, NodeAtividade> mapaKeyAtividades;

	public ControllerAtividade() {
		mapaKeyAtividades = carregaMapaKeyAtv();

	}

	// Refactoring 2: Carregar do BD ao iniciar o app.
	private HashMap<String, NodeAtividade> carregaMapaKeyAtv() {
		HashMap<String, NodeAtividade> mapaTemp = new HashMap<String, NodeAtividade>();
		try {
			// Pegando do BD simulado
			mapaTemp = ControllerBD.getAtividadesData();
		} catch (AtividadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// carregar atividades do BD e salvar em mapaTemp

		return mapaTemp;
	}

	// o autocompletar precisa disso
	public Set<String> getKeys() {
		return mapaKeyAtividades.keySet();
	}

	// falta adaptar um insertion sort para inserir ordenado
	public void add(Atividade atv) {
		//se nao houver atividade do mesmo titulo cadastrado
		if (!getKeys().contains(atv.getTitulo())) {
			mapaKeyAtividades.put(atv.getTitulo(), new NodeAtividade(atv));
		} else {
			NodeAtividade node = mapaKeyAtividades.get(atv.getTitulo());
			NodeAtividade newNode = new NodeAtividade(atv);
			NodeAtividade anterior = null;
			
			//se a atividade for mais recente que a que mais recente já adicionada antes
			if (newNode.getAtividade().getData()
					.after(node.getAtividade().getData())) {
				mapaKeyAtividades.put(atv.getTitulo(), newNode);
				newNode.setNext(node);
			} else {

				while (node != null && newNode.getAtividade().getData()
						.before(node.getAtividade().getData())) {
					anterior = node;
					node = node.getNext();
				}
				
				newNode.setNext(node);
				anterior.setNext(newNode);
			}
		}
	}

	public void remove(Atividade atv) {
		// node inicial
		NodeAtividade node = mapaKeyAtividades.get(atv.getTitulo());
		NodeAtividade anterior = null;

		while (node != null) {

			if (node.equals(atv)) {

				// caso 1, ele eh o primeiro da lista (o mais recente)
				if (anterior == null) {
					// update o mapa
					mapaKeyAtividades.put(atv.getTitulo(), node.getNext());
				} else {
					anterior.setNext(node.getNext());
					node.setNext(null);
				}
			}

			node.getNext();
		}

	}

	public int getTempoTotalAtv(String nomeAtv) throws AtividadeException {
		if (!mapaKeyAtividades.keySet().contains(nomeAtv)) {
			throw new AtividadeException("Não há atividades com este titulo.");
		}

		int tempoTotal = 0;

		NodeAtividade node = mapaKeyAtividades.get(nomeAtv);

		while (node != null) {
			tempoTotal += node.getAtividade().getTempo();
			node = node.getNext();
		}

		return tempoTotal;
	}

	// retorna em ordem do mais recente para o mais antigo
	public ArrayList<Atividade> getAtividades(String titulo)
			throws AtividadeException {
		if (!mapaKeyAtividades.keySet().contains(titulo)) {
			throw new AtividadeException("Não há atividades com este titulo.");
		}

		ArrayList<Atividade> atvs = new ArrayList<Atividade>();

		NodeAtividade node = mapaKeyAtividades.get(titulo);
		while (node != null) {

			atvs.add(node.getAtividade());
			node = node.getNext();
		}

		return atvs;
	}
	
	public ArrayList<Atividade> getAtividadesSemanaAtual(Date dataHoje){
		ArrayList<Atividade> atividadesSemana = new ArrayList<Atividade>();
		
		//TODO
		
		return atividadesSemana;
	}

}
