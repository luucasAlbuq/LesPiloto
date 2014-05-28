package com.projetopiloto.plotadordehoras.util;

public class Atividade {
	private String nome;
	private Hora hora;
	
	public Atividade(String nome, Hora hora) {
		setNome(nome);
		setHora(hora);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}
	
}
