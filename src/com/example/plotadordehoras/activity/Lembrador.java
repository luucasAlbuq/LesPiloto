package com.example.plotadordehoras.activity;

import java.util.Date;

import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;

import model.Mes;
import DaoBD.ManipulaBD;
import android.content.Context;

public class Lembrador {
	
	private Date today;
	private GerarRelatorioDaSemana relatorio;
	
	public Lembrador() {
		
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

}
