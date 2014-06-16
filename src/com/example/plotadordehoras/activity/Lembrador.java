package com.example.plotadordehoras.activity;

import java.text.SimpleDateFormat;

import DaoBD.ManipulaBD;
import android.content.Context;
import android.text.format.Time;


public class Lembrador {
	
	private String today;
	
	private ManipulaBD mbd;
	
	public Lembrador(Context context) {
		mbd = ManipulaBD.getInstance(context);
		
	}
	
	public boolean wasAtividadeYesterday(String now){
		//TODO
		
		return false;
	}
	
	public void updateDate(String today){
		this.today = today;
	}

}
