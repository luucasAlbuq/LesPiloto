package com.projetopiloto.plotadordehoras.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

import android.content.Context;
import model.Atividade;
import DaoBD.ManipulaBD;

/**
 * Classe responsavel por fornecer os dados no formato adequeado para a gera��o
 * do relatorio de ativiades da semana corrente.
 * 
 * @author Lucas
 * 
 */
public class GerarRelatorioDaSemana {

	ManipulaBD manipulaBD;
	List<Atividade> atividadesOrdenadasDecrescente;

	public GerarRelatorioDaSemana(Context context) {
		manipulaBD = ManipulaBD.getInstance(context);

		atividadesOrdenadasDecrescente = new ArrayList<Atividade>();
		try {
			populaListaAtividades("01/06/2014", "07/06/2014");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Faz uma consulta no BD buscando todas as ativadades da semana corrente.
	 */
	public void populaListaAtividades(String dataInicio, String dataFim) throws AtividadeException {
		if(verificaFormatoData(dataFim)&&verificaFormatoData(dataInicio)){
			setAtividadesOrdenadasDecrescente(getManipulaBD()
					.getAtividadesDaSemana(dataInicio, dataFim));
		}else{
			throw new AtividadeException("Data no formato invalido, formato correto deve ser dd/mm/yyyy");
		}
		
	}

	public ManipulaBD getManipulaBD() {
		return manipulaBD;
	}

	public void setManipulaBD(ManipulaBD manipulaBD) {
		this.manipulaBD = manipulaBD;
	}

	/**
	 * M�todo que retorna uma lista de atividades ordenadas de forma decrescente
	 * pelo tempo investido em cada uma delas.
	 * 
	 * @return List<Atividade>
	 */
	public List<Atividade> getAtividadesOrdenadasDecrescente() {
		Collections.sort(atividadesOrdenadasDecrescente,
				new AtividadeComparator());
		return atividadesOrdenadasDecrescente;
	}

	public void setAtividadesOrdenadasDecrescente(
			List<Atividade> atividadesOrdenadasDecrescente) {
		this.atividadesOrdenadasDecrescente = atividadesOrdenadasDecrescente;
	}

	public List<String> getNomeAtivades() {
		ArrayList<String> nomes = new ArrayList<String>();
		for (Atividade atividade : getAtividadesOrdenadasDecrescente()) {
			nomes.add(atividade.getTitulo());
		}

		return nomes;
	}

	/**
	 * Retorna uma lista com o tempo investido de cada atividade de uma semana
	 * 
	 * @return List<Integer> tempoInvestido
	 */
	public List<Integer> getTempoAtivades() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (Atividade atividade : getAtividadesOrdenadasDecrescente()) {
			temp.add(Integer.valueOf(atividade.getTempo()));
		}

		return temp;
	}

	/**
	 * Compara duas atividades
	 * 
	 * @author Pablo
	 * 
	 */
	private static class AtividadeComparator implements Comparator<Atividade> {
		public int compare(Atividade item1, Atividade item2) {
			int n1 = item1.getTempo();
			int n2 = item2.getTempo();
			return n1 < n2 ? +1 : n1 > n2 ? -1 : 0;
		}
	}

	/**
	 * Calcula a porcentagem de tempo investido de uma atividade em rela��o ao
	 * tempo total de investido em uma semana
	 * 
	 * @return List<String> porcentagens
	 */
	public List<String> porcentagemDecrescente() {
		ArrayList<String> porcentagens = new ArrayList<String>();
		double totalTempo = getTotalTempo();
		for (Atividade atv : getAtividadesOrdenadasDecrescente()) {
			double porcentagemAtv = (atv.getTempo() / totalTempo) * 100;
			porcentagens.add(String.format("%.2f", porcentagemAtv) + "%");
		}
		return porcentagens;
	}

	/**
	 * Calcula o tempo total investido em atividades de uma semana
	 * 
	 * @return double totalTempoInvestido
	 */
	private double getTotalTempo() {
		float contador = 0;
		for (Atividade atv : getAtividadesOrdenadasDecrescente()) {
			contador += atv.getTempo();
		}
		return contador;
	}

	// TODO
	/**
	 * Verifica se data esta no formato dd/mm/yyyy
	 * 
	 * @param String
	 *            data
	 * @return boolean
	 */
	protected boolean verificaFormatoData(String data) {
		return true;
	}

}
