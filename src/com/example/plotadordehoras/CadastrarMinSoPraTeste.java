package com.example.plotadordehoras;

import java.util.ArrayList;

import com.projetopiloto.plotadordehoras.util.BarGraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarMinSoPraTeste extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_min_so_pra_teste);
		
		final EditText editmin1 = (EditText) findViewById(R.id.editMinAtividade);
		final EditText editmin2 = (EditText) findViewById(R.id.editMinAtividade2);
		final EditText editmin3 = (EditText) findViewById(R.id.editMinAtividade3);
		final EditText editmin4 = (EditText) findViewById(R.id.editMinAtividade4);
		
		
		
		final BarGraph barra = new BarGraph();
		
		
		
		Button gerar = (Button) findViewById(R.id.gerarGrafico);
		gerar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Integer min1 = Integer.valueOf(editmin1.getText().toString());
				Integer min2 = Integer.valueOf(editmin2.getText().toString());
				Integer min3 = Integer.valueOf(editmin3.getText().toString());
				Integer min4 = Integer.valueOf(editmin4.getText().toString());
				
				ArrayList<Integer> lista1 = new ArrayList<Integer>();
				lista1.add(min1);
				lista1.add(min2);
				
				ArrayList<Integer> lista2 = new ArrayList<Integer>();
				lista1.add(min3);
				lista1.add(min4); 
				
				barra.criarSerie(lista1, "Universidade");
				barra.criarSerie(lista1, "Lazer");
				
				barra.criarRenderer(true, 1);
				barra.criarRenderer(true, 1);
				
				Intent itt = barra.getIntent(getApplicationContext());
				startActivity(itt);
				
			}
		});
		
		
	}
}
