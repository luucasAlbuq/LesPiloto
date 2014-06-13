package com.projetopiloto.plotadordehoras.testes;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.plotadordehoras.activity.CadastroDeAtividade;
import com.robotium.solo.Solo;

public class TesteCadastroDeAtividade extends ActivityInstrumentationTestCase2<CadastroDeAtividade> {
	
	private Solo mSolo;

	public TesteCadastroDeAtividade() {
		super(CadastroDeAtividade.class);
	} 
	
	protected void setUp() throws Exception {
		mSolo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testeNomeInvalido(){
		
		AutoCompleteTextView nome = (AutoCompleteTextView) mSolo.getView(com.example.plotadordehoras.R.id.autoCompleteNome);
		EditText tempo = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editTempoAtividade);
		EditText data = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editDataAtividade);
		
		mSolo.enterText(nome, "");
		mSolo.enterText(tempo, "143");
		mSolo.enterText(data, "01/06/2014");
		
		View v2 = mSolo.getView(com.example.plotadordehoras.R.id.botaoCadastrar);
		mSolo.clickOnView(v2);
		mSolo.waitForText("Nome da Atividade não pode ser vazio ou nula");
		
	}
	
	public void testeTempoInvalido(){
		
		AutoCompleteTextView nome = (AutoCompleteTextView) mSolo.getView(com.example.plotadordehoras.R.id.autoCompleteNome);
		EditText tempo = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editTempoAtividade);
		EditText data = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editDataAtividade);
		
		mSolo.enterText(nome, "Estudar");
		mSolo.enterText(tempo, "");
		mSolo.enterText(data, "01/06/2014");
		
		View v2 = mSolo.getView(com.example.plotadordehoras.R.id.botaoCadastrar);
		mSolo.clickOnView(v2);
		mSolo.waitForText("Tempo da Atividade não pode ser vazio ou nula");
		
	}
	
	public void testeDataInvalida(){
		
		AutoCompleteTextView nome = (AutoCompleteTextView) mSolo.getView(com.example.plotadordehoras.R.id.autoCompleteNome);
		EditText tempo = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editTempoAtividade);
		EditText data = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editDataAtividade);
		
		mSolo.enterText(nome, "Estudar");
		mSolo.enterText(tempo, "143");
		mSolo.enterText(data, "");
		
		View v2 = mSolo.getView(com.example.plotadordehoras.R.id.botaoCadastrar);
		mSolo.clickOnView(v2);
		mSolo.waitForText("Data da Atividade não pode ser vazio ou nula");
		
	}
	
	public void testeAtividadeValida(){
		
		AutoCompleteTextView nome = (AutoCompleteTextView) mSolo.getView(com.example.plotadordehoras.R.id.autoCompleteNome);
		EditText tempo = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editTempoAtividade);
		EditText data = (EditText) mSolo.getView(com.example.plotadordehoras.R.id.editDataAtividade);
		
		mSolo.enterText(nome, "Estudar");
		mSolo.enterText(tempo, "143");
		mSolo.enterText(data, "01/06/2014");
		
		View v2 = mSolo.getView(com.example.plotadordehoras.R.id.botaoCadastrar);
		mSolo.clickOnView(v2);
		mSolo.waitForText("Atividade Criada com Sucesso");
		
	}
}

