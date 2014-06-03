package com.example.plotadordehoras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * 
 * @author Pablo
 *
 * Classe que Exibe o Relatorio das Atividades para o usuário
 * 
 */

public class AtividadesDaSemana extends Activity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividades_semana);
        
        Spinner mesAno = (Spinner) findViewById(R.id.mesesAno);
        Spinner semanasMes = (Spinner) findViewById(R.id.semanaMes);
        Button geraRelatorio = (Button) findViewById(R.id.botaoGeraRelatorioAtividade);
        Button geraHistorico = (Button) findViewById(R.id.botaoHistoricoComparativo);
        Button voltarRelatorio = (Button) findViewById(R.id.botaoVoltar);
        
        String[] meses = getResources().getStringArray(R.array.meses_ano);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meses);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		mesAno.setAdapter(arrayAdapter);
		
		String[] semanas = getResources().getStringArray(R.array.semanas_ano);
		ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,semanas);
		arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
		semanasMes.setAdapter(arrayAdapter2);
		
		/**
		 * Gera o relatório das atividades da semana atual para que o usuario
		 * possa ver como o tempo investido está distribuido nas atividades
		 */
		
		geraRelatorio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Lucas põe o código aqui....
			}
		});
		
		/**
		 * Exibe o Historico da Semana ATUAL com as 2 semanas anteriores
		 */
		
		geraHistorico.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Lucas Põe o Código aqui
				
			}
		});
		
		/**
		 * Volta para a Activity principal
		 */
		
		voltarRelatorio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaPrincipal = new Intent(AtividadesDaSemana.this, MainActivity.class);
				AtividadesDaSemana.this.startActivity(telaPrincipal);
				
			}
		});
	}
}
