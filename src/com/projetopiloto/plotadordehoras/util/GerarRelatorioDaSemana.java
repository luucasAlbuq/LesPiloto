package com.projetopiloto.plotadordehoras.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import model.Atividade;
import DaoBD.ManipulaBD;

public  class GerarRelatorioDaSemana {

	ManipulaBD manipulaBD;
	List<Atividade> atividadesOrdenadasDecrescente;
	

	public  GerarRelatorioDaSemana(Context context) {
		manipulaBD = new ManipulaBD(context);
		atividadesOrdenadasDecrescente = new ArrayList<Atividade>();
		populaListaAtividades();
	}
	
	

	public void populaListaAtividades() {
		setAtividadesOrdenadasDecrescente(getManipulaBD()
				.getAtividadesDaSemana("01/06/2014", "07/06/2014"));
	}

	private void ordenaAtividades() {
		// TODO
		// Collections.sort(getAtividadesOrdenadasDecrescente());
	}

	public ManipulaBD getManipulaBD() {
		return manipulaBD;
	}

	public void setManipulaBD(ManipulaBD manipulaBD) {
		this.manipulaBD = manipulaBD;
	}

	public List<Atividade> getAtividadesOrdenadasDecrescente() {
		Collections.sort(atividadesOrdenadasDecrescente, new AtividadeComparator());
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

	public List<Integer> getTempoAtivades() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (Atividade atividade : getAtividadesOrdenadasDecrescente()) {
			temp.add(new Integer(atividade.getTempo()));
		}

		return temp;
	}
	
	private static class AtividadeComparator implements Comparator<Atividade> {
		public int compare(Atividade item1, Atividade item2) {
			int n1 = item1.getTempo();
			int n2 = item2.getTempo();
			return n1 < n2 ? +1 : n1 > n2 ? -1 : 0;
		}
	}

}
