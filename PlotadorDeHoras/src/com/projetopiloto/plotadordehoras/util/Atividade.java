package com.projetopiloto.plotadordehoras.util;

import java.util.Date;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

/**
 * 
 * @author Pablo Herivelton
 * 
 * @param nome - Nome da Atividade
 * @param tempo - Tempo gasto na Atividade (Em Minutos)
 * 
 * @throws AtividadeException - Caso a Atividade criada seja inválida
 *
 */

public class Atividade {
	
	private String nome;
	private int tempo;
	private Date data;
	
	public Atividade(String nome, int tempo, Date data) throws AtividadeException{
		if (!(verificaAtividade())){
			throw new AtividadeException("Atividade Invalida");
		}
		setNome(nome);
		setTempo(tempo);
		setData(data);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		if (tempo < 0 || nome.isEmpty() || nome == null || data ==null) {
			return false;
		}
		return true;
	}
	
}
