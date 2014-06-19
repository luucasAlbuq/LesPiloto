package com.projetopiloto.plotadordehoras.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.Atividade;
import DaoBD.ManipulaBD;
import android.annotation.SuppressLint;
import android.content.Context;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

/**
 * Classe responsavel por fornecer os dados no formato adequeado para a
 * geraï¿½ï¿½o do relatorio de ativiades da semana corrente.
 * 
 * @author Lucas
 * 
 */
public class GerarRelatorioDaSemana {

	ManipulaBD manipulaBD;
	List<Atividade> atividadesOrdenadasDecrescente;
	OrdenacaoEnum tipoOrdenacao;

	public GerarRelatorioDaSemana(Context context) {
		manipulaBD = ManipulaBD.getInstance(context);

		atividadesOrdenadasDecrescente = new ArrayList<Atividade>();
		try {
			List<String> datas = getDatasDaSemana(SemanaEnum.ATUAL);
			populaListaAtividades(datas.get(0), datas.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Faz uma consulta no BD buscando todas as ativadades da semana corrente.
	 */
	public void populaListaAtividades(String dataInicio, String dataFim)
			throws AtividadeException {
		if (verificaFormatoData(dataFim) && verificaFormatoData(dataInicio)) {
			setAtividadesOrdenadasDecrescente(getManipulaBD()
					.getAtividadesDaSemana(dataInicio, dataFim));
		} else {
			throw new AtividadeException(
					"Data no formato invalido, formato correto deve ser dd/mm/yyyy");
		}

	}

	public ManipulaBD getManipulaBD() {
		return manipulaBD;
	}

	public void setManipulaBD(ManipulaBD manipulaBD) {
		this.manipulaBD = manipulaBD;
	}

	/**
	 * Mï¿½todo que retorna uma lista de atividades ordenadas de forma
	 * decrescente pelo tempo investido em cada uma delas.
	 * 
	 * @return List<Atividade>
	 */
	public List<Atividade> getAtividadesOrdenadasDecrescente(
			OrdenacaoEnum tipoOrdenacao) {
		if (tipoOrdenacao.equals(OrdenacaoEnum.PRIORIDADE)) {
			setTipoOrdenacao(tipoOrdenacao);
			Collections.sort(atividadesOrdenadasDecrescente,
					new ComparadorPorPrioridade());
		} else {
			setTipoOrdenacao(tipoOrdenacao);
			Collections.sort(atividadesOrdenadasDecrescente,
					new ComparadorPorTempo());
		}
		return atividadesOrdenadasDecrescente;
	}

	public void setAtividadesOrdenadasDecrescente(
			List<Atividade> atividadesOrdenadasDecrescente) {
		this.atividadesOrdenadasDecrescente = atividadesOrdenadasDecrescente;
	}

	public List<String> getNomeAtivades() {
		ArrayList<String> nomes = new ArrayList<String>();
		for (Atividade atividade : getAtividadesOrdenadasDecrescente(getTipoOrdenacao())) {
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
		for (Atividade atividade : getAtividadesOrdenadasDecrescente(getTipoOrdenacao())) {
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
	private static class ComparadorPorTempo implements Comparator<Atividade> {
		public int compare(Atividade item1, Atividade item2) {
			int n1 = item1.getTempo();
			int n2 = item2.getTempo();
			return n1 < n2 ? +1 : n1 > n2 ? -1 : 0;
		}
	}

	private static class ComparadorPorPrioridade implements
			Comparator<Atividade> {
		public int compare(Atividade item1, Atividade item2) {
			int saida = 0;
			String n1 = item1.getPrioridade();
			String n2 = item2.getPrioridade();
			if (n1.equals("Alta")) {
				if (n2.equals("Normal") || n2.equals("Baixa")) {
					saida = -1;
				}
			} else if (n1.equals("Normal")) {
				if (n2.equals("Alta"))
					saida = 1;
				else if (n2.equals("Baixa"))
					saida = -1;
			} else {
				if (n2.equals("Alta") || n2.equals("Normal"))
					saida = 1;
			}
			return saida;

		}
	}

	/**
	 * Calcula a porcentagem de tempo investido de uma atividade em relaï¿½ï¿½o
	 * ao tempo total de investido em uma semana
	 * 
	 * @return List<String> porcentagens
	 */
	public List<String> porcentagemDecrescente() {
		ArrayList<String> porcentagens = new ArrayList<String>();
		double totalTempo = getTotalTempo();
		for (Atividade atv : getAtividadesOrdenadasDecrescente(getTipoOrdenacao())) {
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
		for (Atividade atv : getAtividadesOrdenadasDecrescente(getTipoOrdenacao())) {
			contador += atv.getTempo();
		}
		return contador;
	}

	/**
	 * Atualiza a lista de atividades de acordo com o tipo de ordenação passado
	 * como parametro
	 * 
	 * @param ordenacao
	 */
	public void ordenaAtividades(OrdenacaoEnum ordenacao) {
		setAtividadesOrdenadasDecrescente(getAtividadesOrdenadasDecrescente(ordenacao));
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

	public List<String> getPrioridadeAtivades() {
		ArrayList<String> temp = new ArrayList<String>();
		for (Atividade atividade : getAtividadesOrdenadasDecrescente(getTipoOrdenacao())) {
			temp.add(String.valueOf(atividade.getPrioridade()));
		}

		return temp;
	}

	public OrdenacaoEnum getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	public void setTipoOrdenacao(OrdenacaoEnum tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}

	@SuppressLint("SimpleDateFormat")
	public List<String> getDatasDaSemana(SemanaEnum semana) {
		List<String> datas = new ArrayList<String>();
		Date data = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		if (semana.equals(SemanaEnum.ATUAL)) {
			calendar.set(Calendar.WEEK_OF_YEAR,
					calendar.get(Calendar.WEEK_OF_YEAR));
			
		} else if (semana.equals(SemanaEnum.PASSADA)) {
			calendar.set(Calendar.WEEK_OF_YEAR,
					calendar.get(Calendar.WEEK_OF_YEAR)-1);
			
			
		} else {
			calendar.set(Calendar.WEEK_OF_YEAR,
					calendar.get(Calendar.WEEK_OF_YEAR) - 2);
			
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dataInicial = formataData.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String dataFinal = formataData.format(calendar.getTime());
		datas.add(dataInicial);
		datas.add(dataFinal);

		return datas;
	}

}
