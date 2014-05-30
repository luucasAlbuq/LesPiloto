package com.example.plotadordehoras;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroDeAtividade extends Activity implements OnClickListener{
	
	private int dia, mes, ano;
	private Calendar calendario;
	private EditText dataAtividade;
	static final int DATE_DIALOG_ID = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_atividades);
        
        dataAtividade = (EditText) findViewById(R.id.editAtividade);
        
        Button voltar = (Button) findViewById(R.id.botaoVoltarAtividade);
        voltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaAtividade = new Intent(CadastroDeAtividade.this, MainActivity.class);
				CadastroDeAtividade.this.startActivity(telaAtividade);
				
			}
		});
    }
	
	@Override
	protected Dialog onCreateDialog(int id){
		   calendario = Calendar.getInstance();
	       dia = calendario.get(Calendar.DAY_OF_MONTH);
	       mes = calendario.get(Calendar.MONTH);
	       ano = calendario.get(Calendar.YEAR);
	       
	       switch(id){
	       case DATE_DIALOG_ID:
	    	   return new DatePickerDialog(this, mDateSetListener , ano, mes, dia);
	       }
	       return null;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int ano, int mes, int dia){
			String data = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(ano);
			Toast.makeText(CadastroDeAtividade.this, data, Toast.LENGTH_LONG).show();
		}
	};
			
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if (v == dataAtividade){
			showDialog(DATE_DIALOG_ID);
		}
		
	}}
