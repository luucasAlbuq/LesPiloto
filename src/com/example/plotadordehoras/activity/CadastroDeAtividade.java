package com.example.plotadordehoras.activity;

import java.util.Calendar;
import java.util.List;

import model.Atividade;
import DaoBD.CriaBD;
import DaoBD.ManipulaBD;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.plotadordehoras.R;

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
	private SQLiteDatabase sql = null;
	private RadioGroup grupoPrioridade;
	private RadioButton botaoPrioridade;
	private RadioButton prioridadeAlta;
	
	
	@SuppressLint("ShowToast")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_atividades);
        
        Button voltar = (Button) findViewById(R.id.botaoVoltarAtividade);
        Button cadastrar = (Button) findViewById(R.id.botaoCadastrar);
        
        editDataAtividade = (EditText) findViewById(R.id.editDataAtividade);
        editTempoAtividade = (EditText) findViewById(R.id.editTempoAtividade);
        
        prioridadeAlta = (RadioButton) findViewById(R.id.radioAlta);
        
        atualizaAutoComplete();
        
        CriaBD bd = new CriaBD(getBaseContext());
        sql = bd.getReadableDatabase();
        if (!verificaTabela()){
    		bd.onCreate(sql);
    	}
        
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH)+1;
        int ano = calendario.get(Calendar.YEAR);
        dataAtual = (dia < 10 ? "0"+dia: dia) + "/" + (mes < 10 ? "0"+mes : mes) + "/" + ano;
        editDataAtividade.setText(dataAtual);
        
        /**
         * A��o executada ao clicar no Bot�o Cadastrar
         * Tratada as excecoes para cada campo da tela
         */
        final Toast alertaSucesso = Toast.makeText(this, "Atividade Criada com Sucesso", Toast.LENGTH_LONG);
        final Toast nomeAtividadeInvalido = Toast.makeText(this, "Nome da Atividade n�o pode ser vazio ou nula", Toast.LENGTH_LONG);
        final Toast atividadeTempoInvalido = Toast.makeText(this, "Tempo da Atividade n�o pode ser vazia ou nula", Toast.LENGTH_LONG);
        final Toast dataInvalido = Toast.makeText(this, "Tempo da Atividade n�o pode ser vazia ou nula", Toast.LENGTH_LONG);
        
        cadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if ( nomeAtividade.getText().toString() == null || nomeAtividade.getText().toString().isEmpty()){
					nomeAtividadeInvalido.show();
				}else if (editTempoAtividade.getText().toString() == null || 
						editTempoAtividade.getText().toString().isEmpty() ){
					atividadeTempoInvalido.show();
					
				}else if (editDataAtividade.getText().toString() == null || 
						editDataAtividade.getText().toString().isEmpty()){
					dataInvalido.show();
				}else{
					
					String nome = nomeAtividade.getText().toString();
					int tempo = Integer.parseInt(editTempoAtividade.getText().toString());
					String data = editDataAtividade.getText().toString();
					String prioridade = getPrioridade();
			
					ManipulaBD mdb = ManipulaBD.getInstance(getApplicationContext());
					
					mdb.criaAtividade(nome, tempo, data, prioridade);
					atualizaAutoComplete();
					
					nomeAtividade.setText("");
					editTempoAtividade.setText("");
					nomeAtividade.requestFocus();
					prioridadeAlta.setChecked(true);
					editDataAtividade.setText(dataAtual);
					
					alertaSucesso.show();
					
				}
			}
		});
        
        /**
         * A��o executada ao clica no bot�o Voltar
         */
        
        voltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
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
			String data = (dia < 10 ? "0"+String.valueOf(dia) : String.valueOf(dia)) + "/" + 
		    (mes < 10 ? "0"+String.valueOf(mes+1) : String.valueOf(mes+1)) + "/" + String.valueOf(ano);
			editDataAtividade.setText(data);	
		}
	};
	
	public boolean verificaTabela(){
		SQLiteDatabase sql = null;
		CriaBD bd = new CriaBD(getBaseContext());
		sql = bd.getReadableDatabase();
		Cursor cursor = sql.rawQuery("select * from atividade", null);
		return cursor.getColumnCount() > 0;
	}
	
	public void atualizaAutoComplete(){
		ManipulaBD mdb = ManipulaBD.getInstance(getApplicationContext());

		nomeAtividade = (AutoCompleteTextView) findViewById(R.id.autoCompleteNome);
        String[] atividades = (String[]) mdb.getNomeAtividades().toArray(new String[0]);
        adapter = new ArrayAdapter<Object> (this,android.R.layout.simple_list_item_1,atividades);
        nomeAtividade.setAdapter(adapter);
	}
	
	public String getPrioridade(){
		
		grupoPrioridade = (RadioGroup) findViewById(R.id.radioGroupPrioridade);
		int opcao = grupoPrioridade.getCheckedRadioButtonId();
		botaoPrioridade = (RadioButton) findViewById(opcao);
		String prioridade = botaoPrioridade.getText().toString();
		
		return prioridade;
	}
	
}
