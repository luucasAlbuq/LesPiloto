package com.example.plotadordehoras.activity;

import java.util.Date;
import java.util.List;

import model.Atividade;
import model.Mes;
import android.content.Context;

import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;

public class Lembrador {
	
	private Date today;
	private GerarRelatorioDaSemana relatorio;
	
	public Lembrador(Context context) {
		relatorio = new GerarRelatorioDaSemana(context);
		
	}
	
	public boolean wasAtividadeYesterday(String now){
		Date data = new Date(2014, Mes.JUN.ordinal(), 17);
		data.getDay();
		return false;
	}
	
	public void updateDate(int dia, int mes, int ano){
		Date today = new Date(ano, mes -1, dia - 1);
		this.today = today;
	}
	
	public Date getData(){
		return today;
	}
	
	public List<Atividade> getTeste(){
		System.out.println(">>>>");
		System.out.println(relatorio == null);

		return relatorio.getAtividadesOrdenadasDecrescente();
	}
	
	public String getTesteString(){

		return relatorio.getTeste();
	}

}
