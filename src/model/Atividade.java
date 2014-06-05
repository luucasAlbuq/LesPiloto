package model;
import java.util.Date;
import java.util.List;

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
	public Atividade(String titulo, int tempoInvestido, Date data){
		
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
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Atividade){
			Atividade atv = (Atividade) obj;
			return atv.getTitulo().equals(this.titulo);
		}
		return false;
	}
	
	public int getIndexAtividade(List<Atividade> atividade, Atividade atv){
		for (int i = 0; i < atividade.size(); i++) {
			if (atividade.get(i).equals(atv)){
				return i;
			}
		}
		return -1;
	}
	
}
