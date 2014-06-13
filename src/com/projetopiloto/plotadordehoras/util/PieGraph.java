package com.projetopiloto.plotadordehoras.util;

import java.util.List;
import java.util.Random;
import model.Atividade;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.content.Context;
import android.content.Intent;

public class PieGraph {

	
	
	public Intent getIntent(Context context, List<Atividade> atividades) {

		Random random = new Random();
		
		CategorySeries series = new CategorySeries("Pie Graph");
		
		
		for(int i=0; i<atividades.size();i++){
			series.add(atividades.get(i).getTitulo(),atividades.get(i).getTempo());
		}


		DefaultRenderer renderer = new DefaultRenderer();
		
		for(int i=0;i<atividades.size();i++){
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(random.nextInt());
			renderer.addSeriesRenderer(r);
		}
		renderer.setChartTitle("Atividades da Semana");
		renderer.setChartTitleTextSize(30);
		renderer.setLabelsTextSize(20);
		renderer.setLegendTextSize(20);
		renderer.setZoomButtonsVisible(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Atividades");
		return intent;
	}
}
