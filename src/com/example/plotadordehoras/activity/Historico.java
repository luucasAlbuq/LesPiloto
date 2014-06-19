package com.example.plotadordehoras.activity;

import java.util.ArrayList;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.example.plotadordehoras.R;
import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;
import com.projetopiloto.plotadordehoras.util.OrdenacaoEnum;
import com.projetopiloto.plotadordehoras.util.PieGraph;
import com.projetopiloto.plotadordehoras.util.SemanaEnum;

@SuppressWarnings("deprecation")
public class Historico extends TabActivity implements OnTabChangeListener {

	private ListView semanaAtualListView;
	private ListView semanaPassadaListView;
	private ListView semanaRetrasadaListView;
	private static GerarRelatorioDaSemana gerarRelatorio;
	private TabHost tabHost;
	private ManipulaBD mbd;
	private RadioGroup radioGrupoOrdenacao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico);

		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		gerarRelatorio = new GerarRelatorioDaSemana(this);
		mbd = ManipulaBD.getInstance(getApplicationContext());

		/*
		 * Instanciando as listVew
		 */
		semanaAtualListView = (ListView) findViewById(R.id.semanaAtual);
		semanaPassadaListView = (ListView) findViewById(R.id.semanaPassada);
		semanaRetrasadaListView = (ListView) findViewById(R.id.semanaRetrasada);

		/*
		 * Instanciando os radios button do tipo de ordenacao
		 */
		radioGrupoOrdenacao = (RadioGroup) findViewById(R.id.ordenacaoRadio);

		/*
		 * Modificando o adptar das listView para pode ficar no formato desejado
		 * Com tres colunas
		 */
		if (semanaAtualListView.isClickable()) {
			semanaAtualListView.setAdapter(new EfficientAdapter(this,
					gerarRelatorio.getAtividadesOrdenadasDecrescente(
							OrdenacaoEnum.TEMPO).size(), SemanaEnum.ATUAL));
		} else if (semanaPassadaListView.isClickable()) {
			semanaPassadaListView.setAdapter(new EfficientAdapter(this,
					gerarRelatorio.getAtividadesOrdenadasDecrescente(
							OrdenacaoEnum.TEMPO).size(), SemanaEnum.PASSADA));

		} else if (semanaRetrasadaListView.isClickable()) {
			semanaRetrasadaListView.setAdapter(new EfficientAdapter(this,
					gerarRelatorio.getAtividadesOrdenadasDecrescente(
							OrdenacaoEnum.TEMPO).size(), SemanaEnum.REPASSADA));
		}

		/*
		 * Aqui carrega todas as atividades de uma semana, e joga tudo dentro de
		 * um array de Strings com o nome da atividade e o tempo [Lucas]
		 */

		tabHost.addTab(tabHost.newTabSpec("Semana Atual")
				.setIndicator("Semana Atual")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						limparTabelas(semanaAtualListView);
						return semanaAtualListView;
					}
				}));

		tabHost.addTab(tabHost.newTabSpec("Semana Passada")
				.setIndicator("Semana Passada")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						limparTabelas(semanaPassadaListView);
						return semanaPassadaListView;
					}
				}));

		tabHost.addTab(tabHost.newTabSpec("Semana Retrasada")
				.setIndicator("Semana Retrasada")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {
						limparTabelas(semanaRetrasadaListView);
						return semanaRetrasadaListView;
					}
				}));

		/*
		 * Verificando quando o tipo de ordenação é selecionado
		 */
		radioGrupoOrdenacao
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// Aqui vc pode colocar um switch/case para fazer uma
						// tarefa para casa radio checado
						switch (checkedId) {
						case R.id.prioridadeRadio:

							getGerarRelatorio().ordenaAtividades(
									OrdenacaoEnum.PRIORIDADE);
							semanaAtualListView
									.setAdapter(new EfficientAdapter(
											getApplicationContext(),
											gerarRelatorio
													.getAtividadesOrdenadasDecrescente(
															OrdenacaoEnum.PRIORIDADE)
													.size(), SemanaEnum.ATUAL));

							break;
						case R.id.tempoRadio:
							getGerarRelatorio().ordenaAtividades(
									OrdenacaoEnum.TEMPO);
							semanaAtualListView
									.setAdapter(new EfficientAdapter(
											getApplicationContext(),
											gerarRelatorio
													.getAtividadesOrdenadasDecrescente(
															OrdenacaoEnum.TEMPO)
													.size(), SemanaEnum.ATUAL));

							break;
						}
					}
				});

		/*
		 * Ação de cadastra uma atividade
		 */
		Button cadastratAtividade = (Button) findViewById(R.id.cadastroAtividade);
		cadastratAtividade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent telaCadastratAtividade = new Intent(Historico.this,
						CadastroDeAtividade.class);
				Historico.this.startActivity(telaCadastratAtividade);

			}
		});

		/*
		 * Ação de gerar gráficos
		 */
		Button botaoGerarGraficos = (Button) findViewById(R.id.botaoGerarGrafico);
		botaoGerarGraficos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				PieGraph pie = new PieGraph();

				if (semanaAtualListView.isClickable()) {
					// Aqui futuramente vai substituir
					// getAtividadesOrdenadasDecrescente() por
					// getAtividadesSemanaAtual()
					Intent intent = pie.getIntent(
							getApplicationContext(),
							getGerarRelatorio()
									.getAtividadesOrdenadasDecrescente(
											OrdenacaoEnum.TEMPO));
					startActivity(intent);

				} else if (semanaPassadaListView.isClickable()) {
					// Aqui futuramente vai substituir
					// getAtividadesOrdenadasDecrescente() por
					// getAtividadesSemanaPassada()
					Intent intent = pie.getIntent(
							getApplicationContext(),
							getGerarRelatorio()
									.getAtividadesOrdenadasDecrescente(
											OrdenacaoEnum.TEMPO));
					startActivity(intent);

				} else if (semanaRetrasadaListView.isClickable()) {
					// Aqui futuramente vai substituir
					// getAtividadesOrdenadasDecrescente() por
					// getAtividadesSemanaRetrasada()
					Intent intent = pie.getIntent(
							getApplicationContext(),
							getGerarRelatorio()
									.getAtividadesOrdenadasDecrescente(
											OrdenacaoEnum.TEMPO));
					startActivity(intent);
				}

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
				coluna.colunaPrioridadeTextView = (TextView) convertView
						.findViewById(R.id.idPrioridade);

				convertView.setTag(coluna);
			} else {
				coluna = (ViewColuna) convertView.getTag();
			}

			/*
			 * Fim do bloco de teste
			 */

			// if (semana.equals(SemanaEnum.ATUAL)) {
			// try {
			// gerarRelatorio.populaListaAtividades("11/06/2014",
			// "15/06/2014");
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// } else if (semana.equals(SemanaEnum.PASSADA)) {
			// try {
			// gerarRelatorio.populaListaAtividades("11/06/2014",
			// "15/06/2014");
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// } else if (semana.equals(SemanaEnum.REPASSADA)) {
			// try {
			// gerarRelatorio.populaListaAtividades("11/06/2014",
			// "15/06/2014");
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }

			coluna.colunaAtividadeTextView.setText(String
					.valueOf(gerarRelatorio.getNomeAtivades().get(position)));
			coluna.colunaTempoInvestidoTextView.setText(String
					.valueOf(gerarRelatorio.getTempoAtivades().get(position)));
			coluna.colunaPorcentagemTextView.setText(String
					.valueOf(gerarRelatorio.porcentagemDecrescente().get(
							position)));
			coluna.colunaPrioridadeTextView.setText(String
					.valueOf(gerarRelatorio.getPrioridadeAtivades().get(
							position)));

			return convertView;
		}

	}

	static class ViewColuna {
		TextView colunaTempoInvestidoTextView;
		TextView colunaAtividadeTextView;
		TextView colunaPorcentagemTextView;
		TextView colunaPrioridadeTextView;
	}

	public static GerarRelatorioDaSemana getGerarRelatorio() {
		return gerarRelatorio;
	}

	public static void setGerarRelatorio(GerarRelatorioDaSemana gerarRelatorio) {
		Historico.gerarRelatorio = gerarRelatorio;
	}

	private void limparTabelas(ListView view) {
		if (view.isDirty()) {
			view.clearAnimation();
			view.clearChoices();
			view.clearDisappearingChildren();
			view.clearFocus();
			view.clearTextFilter();
		}

	}
}
