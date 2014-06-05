package com.example.plotadordehoras;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Atividade;
import android.app.Activity;
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
import android.widget.TextView;

import com.projetopiloto.plotadordehoras.util.CountriesList;
import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;
import com.projetopiloto.plotadordehoras.util.PieGraph;

public class RelatorioDaSemana extends Activity {

	private ListView listView;
	private static GerarRelatorioDaSemana gerarRelatorio;
	private Button gerarGrafico;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatorio_da_semana);

		gerarRelatorio = new GerarRelatorioDaSemana(this);
		listView = (ListView) findViewById(R.id.lv_country);
		listView.setAdapter(new EfficientAdapter(this, getGerarRelatorio()
				.getNomeAtivades().size()));

		gerarGrafico = (Button) findViewById(R.id.botaoGerarGrafico);
		gerarGrafico.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				PieGraph pie = new PieGraph();

				Intent intent = pie
						.getIntent(getApplicationContext(), getGerarRelatorio()
								.getAtividadesOrdenadasDecrescente());
				startActivity(intent);

			}
		});
		
		
	}

	private static class EfficientAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;
		private int quantidadeDeAtividades;

		public EfficientAdapter(Context context, int quantidadeTotalDeAtividades) {
			layoutInflater = LayoutInflater.from(context);
			quantidadeDeAtividades = quantidadeTotalDeAtividades;
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

			GerarRelatorioDaSemana rela = gerarRelatorio;

			if (convertView == null) {
				convertView = layoutInflater
						.inflate(R.layout.two_col_row, null);
				coluna = new ViewColuna();
				coluna.colunaTempoInvestidoTextView = (TextView) convertView
						.findViewById(R.id.idTempoInvestido);
				coluna.colunaAtividadeTextView = (TextView) convertView
						.findViewById(R.id.idAtividade);
				coluna.colunaPorcentagemTextView = (TextView) convertView
						.findViewById(R.id.idPorcentagem);

				convertView.setTag(coluna);
			} else {
				coluna = (ViewColuna) convertView.getTag();
			}

			coluna.colunaTempoInvestidoTextView.setText(String
					.valueOf(getGerarRelatorio().getTempoAtivades().get(
							position)));
			coluna.colunaAtividadeTextView.setText(String
					.valueOf(getGerarRelatorio().getNomeAtivades()
							.get(position)));
			coluna.colunaPorcentagemTextView.setText(String
					.valueOf(getGerarRelatorio().porcentagemDecrescente().get(position)));

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

	public void setGerarRelatorio(GerarRelatorioDaSemana gerarRelatorio) {
		this.gerarRelatorio = gerarRelatorio;
	}
}
