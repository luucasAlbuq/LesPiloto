package com.example.plotadordehoras;

import java.util.ArrayList;

import model.Atividade;
import DaoBD.ManipulaBD;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;

@SuppressWarnings("deprecation")
public class Historico extends TabActivity implements OnTabChangeListener {

	private ListView semanaAtualListView;
	private ListView semanaPassadaListView;
	private ListView semanaRetrasadaListView;
	private GerarRelatorioDaSemana gerarRelatorio;
	private TabHost tabHost;
	private ManipulaBD mbd;

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		mbd = new ManipulaBD(getApplicationContext());

		// Aqui carrega todas as atividades de uma semana, e joga tudo dentro de
		// um array de Strings com o nome da atividade e o tempo [Lucas]
		
		ArrayList<String> listaAtividadeSemanaAtual = new ArrayList<String>();
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaAtual.add(atv.getTitulo() + " - " + atv.getTempo() + " Minutos ");
		}
		
		ArrayList<String> listaAtividadeSemanaPassada = new ArrayList<String>();
		gerarRelatorio.setAtividadesOrdenadasDecrescente(mbd.getAtividadesDaSemana("25/05/2014", "31/05/2014"));
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaPassada.add(atv.getTitulo() + " - " + atv.getTempo() + " Minutos ");
		}
		
		ArrayList<String> listaAtividadeSemanaRetrasada = new ArrayList<String>();
		gerarRelatorio.setAtividadesOrdenadasDecrescente(mbd.getAtividadesDaSemana("18/05/2014", "24/05/2014"));
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaRetrasada.add(atv.getTitulo() + " - " + atv.getTempo() + " Minutos ");
		}

		semanaAtualListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaAtual));

		semanaPassadaListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaPassada));

		semanaRetrasadaListView
				.setAdapter(new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						listaAtividadeSemanaRetrasada));

		
		tabHost.addTab(tabHost.newTabSpec("Semana Atual").setIndicator("Semana Atual").setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						return semanaAtualListView;
					}
				}));
		
		tabHost.addTab(tabHost.newTabSpec("Semana Passada").setIndicator("Semana Passada").setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return semanaPassadaListView;
			}
		}));
		
		tabHost.addTab(tabHost.newTabSpec("Semana Retrasada").setIndicator("Semana Retrasada").setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return semanaRetrasadaListView;
			}
		}));
		
		Button voltarHistorico = (Button) findViewById(R.id.botaoHistorico);
		voltarHistorico.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaRelatorioDaSemana = new Intent(Historico.this, AtividadesDaSemana.class);
				Historico.this.startActivity(telaRelatorioDaSemana);
				
			}
		});
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
