package com.example.plotadordehoras;

import java.util.ArrayList;

import model.Atividade;

import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class Historico extends TabActivity implements OnTabChangeListener {

	private ListView semanaAtualListView;
	private ListView semanaPassadaListView;
	private ListView semanaRetrasadaListView;
	private GerarRelatorioDaSemana gerarRelatorio;
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico);

		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		gerarRelatorio = new GerarRelatorioDaSemana(this);
		semanaAtualListView = (ListView) findViewById(R.id.semanaAtual);
		semanaPassadaListView = (ListView) findViewById(R.id.semanaPassada);
		semanaRetrasadaListView = (ListView) findViewById(R.id.semanaRetrasada);

		// Aqui carrega todas as atividades de uma semana, e joga tudo dentro de
		// um array de Strings com o nome da atividade e o tempo [Lucas]
		ArrayList<String> listaAtividadeSemanaAtual = new ArrayList<String>();
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaAtual.add("Minutos: "+atv.getTempo() + " - "
					+ atv.getTitulo());
		}

		semanaAtualListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaAtual));

		semanaPassadaListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaAtual));

		semanaRetrasadaListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaAtual));

		
		tabHost.addTab(tabHost.newTabSpec("Semana 1").setIndicator("Semana 1").setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						return semanaAtualListView;
					}
				}));
		
		tabHost.addTab(tabHost.newTabSpec("Semana 2").setIndicator("Semana 2").setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return semanaPassadaListView;
			}
		}));
		
		tabHost.addTab(tabHost.newTabSpec("Semana 3").setIndicator("Semana 3").setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return semanaRetrasadaListView;
			}
		}));
	}

	public ListView getSemanaAtualListView() {
		return semanaAtualListView;
	}

	public void setSemanaAtualListView(ListView semanaAtualListView) {
		this.semanaAtualListView = semanaAtualListView;
	}

	public ListView getSemanaPassadaListView() {
		return semanaPassadaListView;
	}

	public void setSemanaPassadaListView(ListView semanaPassadaListView) {
		this.semanaPassadaListView = semanaPassadaListView;
	}

	public ListView getSemanaRetrasadaListView() {
		return semanaRetrasadaListView;
	}

	public void setSemanaRetrasadaListView(ListView semanaRetrasadaListView) {
		this.semanaRetrasadaListView = semanaRetrasadaListView;
	}

	@Override
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub

	}

}
