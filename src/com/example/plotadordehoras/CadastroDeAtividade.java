package com.example.plotadordehoras;

import java.util.Calendar;
import java.util.Date;

import model.Atividade;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import controller.ControllerAtividade;

/**
 * 
 * @author pablo Herivelton
 * Classe que simula a Tela de Cadastro de Atividades
 *
 */

public class CadastroDeAtividade extends Activity{
	
	private EditText editDataAtividade;
	private AutoCompleteTextView nomeAtividade;
	private EditText editTempoAtividade;
	private static final int DATE_DIALOG_ID = 0;
	private String dataAtual;
	private ArrayAdapter<Object> adapter;
	private ControllerAtividade control = new ControllerAtividade();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_atividades);
        
        Button voltar = (Button) findViewById(R.id.botaoVoltarAtividade);
        Button cadastrar = (Button) findViewById(R.id.botaoCadastrar);
        
        editDataAtividade = (EditText) findViewById(R.id.editDataAtividade);
        editTempoAtividade = (EditText) findViewById(R.id.editTempoAtividade);
        
        nomeAtividade = (AutoCompleteTextView) findViewById(R.id.autoCompleteNome);
        String[] atividades = control.getKeys().toArray(new String[0]);
        adapter = new ArrayAdapter<Object> (this,android.R.layout.simple_list_item_1,atividades);
        nomeAtividade.setAdapter(adapter);
        
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH)+1;
        int ano = calendario.get(Calendar.YEAR);
        dataAtual = (dia < 10 ? "0"+dia: dia) + "/" + (mes < 10 ? "0"+mes : mes) + "/" + ano;
        editDataAtividade.setText(dataAtual);
        
        /**
         * Ação executada ao clicar no Botão Cadastrar
         * Tratada as excecoes para cada campo da tela
         */
        
        cadastrar.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				if ( nomeAtividade.getText().toString() == null || nomeAtividade.getText().toString().isEmpty()){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Nome da Atividade Invalida")
					.setMessage("Nome da Atividade não pode ser vazio ou nula").show();
				}else if (editTempoAtividade.getText().toString() == null || 
						editTempoAtividade.getText().toString().isEmpty() ){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Tempo da Atividade Invalida")
					.setMessage("Tempo da Atividade não pode ser vazia ou nula").show();
				}else if (editDataAtividade.getText().toString() == null || 
						editDataAtividade.getText().toString().isEmpty()){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Data da Atividade Invalida")
					.setMessage("Data da Atividade não pode ser vazio ou nula").show();
				}else{
					
					String nome = nomeAtividade.getText().toString();
					int tempo = Integer.parseInt(editTempoAtividade.getText().toString());
					String data = editDataAtividade.getText().toString();
					int dia = Integer.parseInt(data.substring(0, 2));
					int mes = Integer.parseInt(data.substring(3, 5));
					int ano = Integer.parseInt(data.substring(6, 10));
					
					Atividade atv = new Atividade(nome, tempo, new Date(ano, mes, dia));
					control.add(atv);
					adapter.clear();
					adapter.addAll(control.getKeys());
					
					System.out.println(control.getKeys().size());
			
					new AlertDialog.Builder(CadastroDeAtividade.this).setTitle("Atividade Criada")
					.setMessage("Atividade Criada com Sucesso").show();
				}
			}
		});
        
        /**
         * Ação executada ao clica no botão Voltar
         */
        
        voltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        /**
         * Exibe o Calendário para o usuário escolher a data
         */
        
        editDataAtividade.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if (v == editDataAtividade){
					showDialog(DATE_DIALOG_ID);
				}
			}
		});
        
    }
	
	/**
	 * Cria o Dialog da Data
	 */
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();
		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH)+1;
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
		}
		return null;
		
	}
		
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int ano, int mes, int dia) {
			String data = (dia < 10 ? "0"+String.valueOf(dia) : String.valueOf(dia)) + "/" + 
		    (mes < 10 ? "0"+String.valueOf(mes+1) : String.valueOf(mes+1)) + "/" + String.valueOf(ano);
			editDataAtividade.setText(data);	
		}
	};
	
}
