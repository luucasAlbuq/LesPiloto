package com.projetopiloto.plotadordehoras.util;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

/**
 * 
 * @author Pablo Herivelton
 * 
 * @param nome - Nome da Atividade
 * @param tempo - Tempo gasto na Atividade (Em Minutos)
 * 
 * @throws AtividadeException - Caso a Atividade criada seja inv�lida
 *
 */

public class Atividade {
	
	private String nome;
	private int tempo;
	
	public Atividade(String nome, int tempo) throws AtividadeException{
		if (!(verificaAtividade())){
			throw new AtividadeException("Atividade Invalida");
		}
		setNome(nome);
		setTempo(tempo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public boolean verificaAtividade(){
		if (tempo < 0 || nome.isEmpty() || nome == null) {
			return false;
		}
		return true;
	}
	
}
