package com.example.plotadordehoras;

import java.util.Calendar;

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
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_atividades);
        
        Button voltar = (Button) findViewById(R.id.botaoVoltarAtividade);
        Button cadastrar = (Button) findViewById(R.id.botaoCadastrar);
        
        editDataAtividade = (EditText) findViewById(R.id.editDataAtividade);
        editTempoAtividade = (EditText) findViewById(R.id.editTempoAtividade);
        
        nomeAtividade = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextNome);
        String[] countries = getResources().getStringArray(R.array.sugestao_atividades);
        adapter = new ArrayAdapter<Object> (this,android.R.layout.simple_list_item_1,countries);
        nomeAtividade.setAdapter(adapter);
        
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH)+1;
        int ano = calendario.get(Calendar.YEAR);
        dataAtual = dia + "/" + mes + "/" + ano;
        editDataAtividade.setText(dataAtual);
        
        /**
         * A��o executada ao clicar no Bot�o Cadastrar
         * Tratada as excecoes para cada campo da tela
         */
        
        cadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (nomeAtividade.getText().toString().isEmpty() || nomeAtividade.getText().toString() == null){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Nome da Atividade Invalida")
					.setMessage("Nome da Atividade n�o pode ser vazio ou nula").show();
				}else if (editTempoAtividade.getText().toString().isEmpty() || 
						  editTempoAtividade.getText().toString() == null){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Tempo da Atividade Invalida")
					.setMessage("Tempo da Atividade n�o pode ser vazia ou nula").show();
				}else if (editDataAtividade.getText().toString().isEmpty() || editDataAtividade.getText().toString() == null){
					new AlertDialog.Builder(CadastroDeAtividade.this)
					.setTitle("Data da Atividade Invalida")
					.setMessage("Data da Atividade n�o pode ser vazio ou nula").show();
				}else{
					new AlertDialog.Builder(CadastroDeAtividade.this).setTitle("Atividade Criada")
					.setMessage("Atividade Criada com Sucesso").show();
				}
			}
		});
        
        /**
         * A��o executada ao clica no bot�o Voltar
         */
        
        voltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaAtividade = new Intent(CadastroDeAtividade.this, MainActivity.class);
				CadastroDeAtividade.this.startActivity(telaAtividade);
				
			}
		});
        
        /**
         * Exibe o Calend�rio para o usu�rio escolher a data
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
			String data = String.valueOf(dia) + "/" + String.valueOf(mes+1) + "/" + String.valueOf(ano);
			editDataAtividade.setText(data);
				
		}
	};
	
}
