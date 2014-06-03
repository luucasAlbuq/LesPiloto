package com.example.plotadordehoras;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AtividadesDaSemana extends Activity{
	
	private Button geraRelatorio;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividades_semana);
        
        Spinner mesAno = (Spinner) findViewById(R.id.mesesAno);
        Spinner semanasMes = (Spinner) findViewById(R.id.semanaMes);
        geraRelatorio = (Button) findViewById(R.id.geraRelatorioAtividade);
        
        String[] meses = getResources().getStringArray(R.array.meses_ano);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meses);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		mesAno.setAdapter(arrayAdapter);
		
		String[] semanas = getResources().getStringArray(R.array.semanas_ano);
		ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,semanas);
		arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
		semanasMes.setAdapter(arrayAdapter2);
		
		geraRelatorio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Lucas põe o código aqui....
			}
		});
	}
}
