package com.example.plotadordehoras.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
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
        
        lembrador = new Lembrador(getApplicationContext());
        
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
		
        
        
    }
	
	private void updateTimeAndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentDateandTime = sdf.format(new Date());
		lembrador.updateDate(currentDateandTime);
	}
}
