package com.example.plotadordehoras.activity;

import DaoBD.ManipulaBD;
import android.content.Context;


public class Lembrador {
	private ManipulaBD mbd;
	
	public Lembrador(Context context) {
		mbd = ManipulaBD.getInstance(context);
		
	}
	
	public boolean wasAtividadeYesterday(String today){
		//TODO
		
		return false;
	}

}
