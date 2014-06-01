package model;
import java.util.Date;

import com.projetopiloto.plotadordehoras.excecoes.AtividadeException;

/**
 * 
 * @author Pablo Herivelton
 * 
 * @param titulo - Nome da Atividade
 * @param tempoInvestido - Tempo gasto na Atividade (Em Minutos)
 * 
 * @throws AtividadeException - Caso a Atividade criada seja inv�lida
 *
 */

public class Atividade {
	
	private String titulo;
	private int tempoInvestido;
	private Date data;

	//Refactoring 1: A classe que instanciar Atividade dever� tratar esta exce��o como um "popup" no GUI
	public Atividade(String titulo, int tempoInvestido, Date data) throws AtividadeException{
		
		verificaAtividade();
		
		setTitulo(titulo);
		setTempo(tempoInvestido);
		setData(data);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String nome) {
		this.titulo = nome;
	}

	public int getTempo() {
		return tempoInvestido;
	}

	public void setTempo(int tempo) {
		this.tempoInvestido = tempo;
	}
	
	public void verificaAtividade() throws AtividadeException{
		
		if (tempoInvestido < 0) {
			throw new AtividadeException("Tempo Investido inv�lido");
		}else if(titulo.isEmpty() || titulo == null){
			throw new AtividadeException("T�tulo inv�lido");
		}else if(data ==null){
			throw new AtividadeException("T�tulo inv�lido");
		}

	}
	
	public boolean equals(Atividade atv){
		if(atv.getTitulo().equals(this.titulo) && atv.getTempo() == this.tempoInvestido && atv.getData().equals(this.data)){
			return true;
		}
		return false;
	}
	
}
