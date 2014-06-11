package com.example.plotadordehoras;

import java.util.ArrayList;

import model.Atividade;
import DaoBD.ManipulaBD;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;
import com.projetopiloto.plotadordehoras.util.SemanaEnum;

@SuppressWarnings("deprecation")
public class Historico extends TabActivity implements OnTabChangeListener {

	private ListView semanaAtualListView;
	private ListView semanaPassadaListView;
	private ListView semanaRetrasadaListView;
	private static GerarRelatorioDaSemana gerarRelatorio;
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
		mbd = new ManipulaBD(getApplicationContext());

		/*
		 * Instanciando as listVew
		 */
		semanaAtualListView = (ListView) findViewById(R.id.semanaAtual);
		semanaPassadaListView = (ListView) findViewById(R.id.semanaPassada);
		semanaRetrasadaListView = (ListView) findViewById(R.id.semanaRetrasada);

		/*
		 * Modificando o adptar das listView para pode ficar no formato desejado
		 * Com tres colunas
		 */
		semanaAtualListView.setAdapter(new EfficientAdapter(this, 15, SemanaEnum.ATUAL));
		semanaPassadaListView.setAdapter(new EfficientAdapter(this, 15, SemanaEnum.PASSADA));
		semanaRetrasadaListView.setAdapter(new EfficientAdapter(this, 15, SemanaEnum.REPASSADA));

		/*
		 * Aqui carrega todas as atividades de uma semana, e joga tudo dentro de
		 * um array de Strings com o nome da atividade e o tempo [Lucas]
		 */

		ArrayList<String> listaAtividadeSemanaAtual = new ArrayList<String>();
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaAtual.add(atv.getTitulo() + " - "
					+ atv.getTempo() + " Minutos ");
		}

		ArrayList<String> listaAtividadeSemanaPassada = new ArrayList<String>();
		gerarRelatorio.setAtividadesOrdenadasDecrescente(mbd
				.getAtividadesDaSemana("25/05/2014", "31/05/2014"));
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaPassada.add(atv.getTitulo() + " - "
					+ atv.getTempo() + " Minutos ");
		}

		ArrayList<String> listaAtividadeSemanaRetrasada = new ArrayList<String>();
		gerarRelatorio.setAtividadesOrdenadasDecrescente(mbd
				.getAtividadesDaSemana("18/05/2014", "24/05/2014"));
		for (Atividade atv : gerarRelatorio.getAtividadesOrdenadasDecrescente()) {
			listaAtividadeSemanaRetrasada.add(atv.getTitulo() + " - "
					+ atv.getTempo() + " Minutos ");
		}

		tabHost.addTab(tabHost.newTabSpec("Semana Atual")
				.setIndicator("Semana Atual")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						return semanaAtualListView;
					}
				}));

		tabHost.addTab(tabHost.newTabSpec("Semana Passada")
				.setIndicator("Semana Passada")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						return semanaPassadaListView;
					}
				}));

		tabHost.addTab(tabHost.newTabSpec("Semana Retrasada")
				.setIndicator("Semana Retrasada")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						return semanaRetrasadaListView;
					}
				}));

		Button voltarHistorico = (Button) findViewById(R.id.botaoHistorico);
		voltarHistorico.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent telaRelatorioDaSemana = new Intent(Historico.this,
						AtividadesDaSemana.class);
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

	private static class EfficientAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;
		private int quantidadeDeAtividades;
		private SemanaEnum semana;

		public EfficientAdapter(Context context,
				int quantidadeTotalDeAtividades, SemanaEnum semana) {
			layoutInflater = LayoutInflater.from(context);
			quantidadeDeAtividades = quantidadeTotalDeAtividades;
			this.semana = semana;
		}

		@Override
		public int getCount() {
			return quantidadeDeAtividades;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewColuna coluna;
			System.out.println(position);

			if (convertView == null) {
				convertView = layoutInflater
						.inflate(R.layout.two_col_row, null);
				coluna = new ViewColuna();
				coluna.colunaAtividadeTextView = (TextView) convertView
						.findViewById(R.id.idAtividade);
				coluna.colunaTempoInvestidoTextView = (TextView) convertView
						.findViewById(R.id.idTempoInvestido);
				coluna.colunaPorcentagemTextView = (TextView) convertView
						.findViewById(R.id.idPorcentagem);

				convertView.setTag(coluna);
			} else {
				coluna = (ViewColuna) convertView.getTag();
			}
			
			/*
			 * Bloco para Teste, isso é so pq no meu cel o BD da lastaco [Lucas]
			 */
			ArrayList<String> nomesAtividadesTeste = new ArrayList<String>();
			ArrayList<Integer> minTeste = new ArrayList<Integer>();
			ArrayList<String> porcentagemTeste = new ArrayList<String>();

			nomesAtividadesTeste.add("Nadar");
			nomesAtividadesTeste.add("Estudar");
			nomesAtividadesTeste.add("Malhar");
			nomesAtividadesTeste.add("Atividade 1");
			nomesAtividadesTeste.add("Atividade 2");
			nomesAtividadesTeste.add("Atividade 3");
			nomesAtividadesTeste.add("Atividade 4");
			nomesAtividadesTeste.add("Atividade 5");
			nomesAtividadesTeste.add("Atividade 6");
			nomesAtividadesTeste.add("Atividade 7");
			nomesAtividadesTeste.add("Atividade 8");
			nomesAtividadesTeste.add("Atividade 9");
			nomesAtividadesTeste.add("Atividade 10");
			nomesAtividadesTeste.add("Atividade 11");
			nomesAtividadesTeste.add("Atividade 12");

			minTeste.add(20);
			minTeste.add(10);
			minTeste.add(50);
			minTeste.add(100);
			minTeste.add(100);
			minTeste.add(20);
			minTeste.add(10);
			minTeste.add(50);
			minTeste.add(100);
			minTeste.add(100);
			minTeste.add(20);
			minTeste.add(10);
			minTeste.add(50);
			minTeste.add(100);
			minTeste.add(100);

			porcentagemTeste.add("30%");
			porcentagemTeste.add("70%");
			porcentagemTeste.add("20%");
			porcentagemTeste.add("39%");
			porcentagemTeste.add("39%");
			porcentagemTeste.add("30%");
			porcentagemTeste.add("70%");
			porcentagemTeste.add("20%");
			porcentagemTeste.add("39%");
			porcentagemTeste.add("39%");
			porcentagemTeste.add("30%");
			porcentagemTeste.add("70%");
			porcentagemTeste.add("20%");
			porcentagemTeste.add("39%");
			porcentagemTeste.add("39%");
			/*
			 * Fim do bloco de teste
			 */

			if (semana.equals(SemanaEnum.ATUAL)) {
				try {
					gerarRelatorio.populaListaAtividades("11/06/2014","15/06/2014");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (semana.equals(SemanaEnum.PASSADA)) {
				try {
					gerarRelatorio.populaListaAtividades("11/06/2014","15/06/2014");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (semana.equals(SemanaEnum.REPASSADA)) {
				try {
					gerarRelatorio.populaListaAtividades("11/06/2014","15/06/2014");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			coluna.colunaAtividadeTextView.setText(String
					.valueOf(nomesAtividadesTeste.get(position)));
			coluna.colunaTempoInvestidoTextView.setText(String
					.valueOf(minTeste.get(position)));
			coluna.colunaPorcentagemTextView.setText(String
					.valueOf(porcentagemTeste.get(position)));

			return convertView;
		}

	}

	static class ViewColuna {
		TextView colunaTempoInvestidoTextView;
		TextView colunaAtividadeTextView;
		TextView colunaPorcentagemTextView;
	}

	public static GerarRelatorioDaSemana getGerarRelatorio() {
		return gerarRelatorio;
	}

	public static void setGerarRelatorio(GerarRelatorioDaSemana gerarRelatorio) {
		Historico.gerarRelatorio = gerarRelatorio;
	}

}
