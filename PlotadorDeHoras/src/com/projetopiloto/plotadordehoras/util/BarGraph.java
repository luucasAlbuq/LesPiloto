package com.projetopiloto.plotadordehoras.util;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

/**
 * Classe geradora de gráficos do formato barra.
 * 
 * @author Lucas
 * 
 */
public class BarGraph {

	private XYMultipleSeriesDataset dataSet;
	private XYMultipleSeriesRenderer grafico;
	private ArrayList<CategorySeries> listaSeries;
	private ArrayList<XYSeriesRenderer> listaRenderers;

	public BarGraph() {
		dataSet = new XYMultipleSeriesDataset();
		grafico = new XYMultipleSeriesRenderer();
		listaSeries = new ArrayList<CategorySeries>();
		listaRenderers = new ArrayList<XYSeriesRenderer>();
	}

	/**
	 * Costumisar elementos do gráfico, título, nome do eixo X e nome do eixo Y
	 * 
	 * @param String
	 *            titulo
	 * @param String
	 *            xTitulo
	 * @param String
	 *            yTitulo
	 */
	public void customisarGrafico(String titulo, String xTitulo, String yTitulo) {
		grafico.setChartTitle(titulo);
		grafico.setXTitle(xTitulo);
		grafico.setYTitle(yTitulo);
		grafico.setAxesColor(Color.GREEN);
		grafico.setLabelsColor(Color.RED);
	}

	/**
	 * Cria e retorna uma serie de inteiros, que guarda os valores que serão
	 * expressos no gráfiico.
	 * 
	 * @param ArrayList
	 *            <Integer> listaDeHoras
	 * @param String
	 *            nomeSerie
	 * @return CategorySeries serie
	 */
	public CategorySeries criarSerie(ArrayList<Integer> listaDeHoras,
			String nomeSerie) {
		CategorySeries serie;
		if (nomeSerie == null || nomeSerie.trim().equals("")) {
			serie = new CategorySeries("Barra 1");
		} else {
			serie = new CategorySeries(nomeSerie);
		}

		for (int i = 0; i < listaDeHoras.size(); i++) {
			serie.add("Atividade " + (i + 1), listaDeHoras.get(i));
		}
		
		getListaSeries().add(serie);
		return serie;
	}

	/**
	 * Cria um Renderer que vai ser usado para customisar o gráfico.
	 * 
	 * @param boolean displayValues
	 * @param float valuesSpacing
	 * @return XYSeriesRenderer renderer
	 */
	public XYSeriesRenderer criarRenderer(boolean displayValues,
			float valuesSpacing) {

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(displayValues);
		renderer.setChartValuesSpacing(valuesSpacing);
		listaRenderers.add(renderer);
		return renderer;

	}

	/**
	 * Monta um gráfico inserindo nele todas as CategorySeries e
	 * XYSeriesRenderer criados.
	 * 
	 * @param ArrayList
	 *            <CategorySeries> listaDeSeries
	 * @param XYSeriesRenderer
	 *            rendererX
	 * @param XYSeriesRenderer
	 *            rendererY
	 */
	public void montarGrafico(ArrayList<CategorySeries> listaDeSeries,
			ArrayList<XYSeriesRenderer> listaRenderes) {

		for (CategorySeries sereis : listaDeSeries) {
			getDataSet().addSeries(sereis.toXYSeries());
		}

		for (XYSeriesRenderer renderer : listaRenderes) {
			getGrafico().addSeriesRenderer(renderer);
		}

	}

	/**
	 * Cria um Intent com um gráfico formato barra
	 * 
	 * @param Context
	 *            context
	 * @return Intent intent
	 */
	public Intent getIntent(Context context) {

		montarGrafico(getListaSeries(), getListaRenderers());

		Intent intent = ChartFactory.getBarChartIntent(context, getDataSet(),
				getGrafico(), Type.DEFAULT);

		return intent;
	}

	public XYMultipleSeriesDataset getDataSet() {
		return dataSet;
	}

	public void setDataSet(XYMultipleSeriesDataset dataSet) {
		this.dataSet = dataSet;
	}

	public XYMultipleSeriesRenderer getGrafico() {
		return grafico;
	}

	public void setGrafico(XYMultipleSeriesRenderer grafico) {
		this.grafico = grafico;
	}

	public ArrayList<CategorySeries> getListaSeries() {
		return listaSeries;
	}

	public void setListaSeries(ArrayList<CategorySeries> listaSeries) {
		this.listaSeries = listaSeries;
	}

	public ArrayList<XYSeriesRenderer> getListaRenderers() {
		return listaRenderers;
	}

	public void setListaRenderers(ArrayList<XYSeriesRenderer> listaRenderers) {
		this.listaRenderers = listaRenderers;
	}

}
