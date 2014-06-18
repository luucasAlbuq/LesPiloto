package com.example.plotadordehoras.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.plotadordehoras.R;

public class MainActivity extends Activity {
	
	private Lembrador lembrador;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lembrador = new Lembrador();
        
        Button cadastro = (Button) findViewById(R.id.cadastroAtividade);
        cadastro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaAtividade = new Intent(MainActivity.this, CadastroDeAtividade.class);
				MainActivity.this.startActivity(telaAtividade);
				
			}
		});
		
		Button relatorio = (Button) findViewById(R.id.botaoRelatorioAtividade);
		relatorio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaRelatorio = new Intent(MainActivity.this, Historico.class);
				MainActivity.this.startActivity(telaRelatorio);
				
			}
		});
		
        
        updateTimeAndDate();
    }
	
	private void updateTimeAndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		sdf.setTimeZone(TimeZone.getDefault());
		String currentDateandTime = sdf.format(new Date());
		
		int dia = Integer.parseInt(currentDateandTime.substring(6, 8));
		int mes = Integer.parseInt(currentDateandTime.substring(4, 6));
		int ano = Integer.parseInt(currentDateandTime.substring(0, 4));
		
		lembrador.updateDate(dia, mes, ano);
		
		//showMessage(String.valueOf(lembrador.getData().getDay()));
	}
	
	private void showMessage(String msg){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(msg);
		alertDialog.show();
		
	}
	
}
