package controller;

import java.util.ArrayList;

import model.Atividade;


//Refactoring 1: esta classe deverá ser singleton.
public class ControllerAtividade {
	
	private ArrayList<String> keys;
	
	public ControllerAtividade(){
		keys = carregaKeys();
	}
	
	//Refactoring 2: Carregar do BD ao iniciar o app.
	private ArrayList<String> carregaKeys() {
		ArrayList<String> keysTemp = new ArrayList<String>();
		return keysTemp;
	}

	public void add(Atividade atv){
		
	}

}
