package com.projetopiloto.plotadordehoras.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CapturaDataSemanas {
	private String dataAtual;
	
	public ArrayList<String> getDatasSemanaAtual(){
		ArrayList<String> datas = new ArrayList<String>();
		String dataInicio, dataFim;

		Calendar c = Calendar.getInstance();
	    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	    int data = c.get(Calendar.DATE);
	    System.out.println(data);
	    
	    if (Calendar.MONDAY == dayOfWeek) {
	       
	    } else if (Calendar.TUESDAY == dayOfWeek) {
	       
	    } else if (Calendar.WEDNESDAY == dayOfWeek) {
	       
	    } else if (Calendar.THURSDAY == dayOfWeek) {
	        
	    } else if (Calendar.FRIDAY == dayOfWeek) {
	        
	    } else if (Calendar.SATURDAY == dayOfWeek) {
	       
	    } else if (Calendar.SUNDAY == dayOfWeek) {
	        
	    }
		
		return datas;
	}
	
	public static void main(String[] args) {
		CapturaDataSemanas teste = new CapturaDataSemanas();
		
		teste.getDatasSemanaAtual();
	}
}
