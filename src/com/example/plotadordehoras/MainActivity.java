package com.example.plotadordehoras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	int[] firstData = { 23, 145, 67, 78, 86, 190, 46, 78, 167, 164 };
	int[] secondData = { 83, 45, 168, 138, 67, 150, 64, 87, 144, 188 };

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button cadastro = (Button) findViewById(R.id.cadastroAtividade);
        cadastro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaAtividade = new Intent(MainActivity.this, CadastroDeAtividade.class);
				MainActivity.this.startActivity(telaAtividade);
				
			}
		});
        
       
		//////////////////////////////////////////////////////////////
		
		Button grafico = (Button) findViewById(R.id.cadastroMin);
		
		grafico.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent telaGrafico = new Intent(MainActivity.this, CadastrarMinSoPraTeste.class);
				MainActivity.this.startActivity(telaGrafico);
				
			}
		});
		
		
		Button relatorio = (Button) findViewById(R.id.botaoRelatorioAtividade);
		relatorio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaRelatorio = new Intent(MainActivity.this, AtividadesDaSemana.class);
				MainActivity.this.startActivity(telaRelatorio);
				
			}
		});
		
        
        
    }
}
