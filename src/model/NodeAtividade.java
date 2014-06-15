package model;

public class NodeAtividade {
	
	private NodeAtividade next;
	private Atividade atv;
	
	public NodeAtividade(Atividade atv) {
		this.atv = atv;
	}
	
	public NodeAtividade(Atividade atv, NodeAtividade next) {
		this.atv = atv;
		this.next = next;
	}
	
	public void setNext(NodeAtividade next){
		this.next = next;
	}
	
	public Atividade getAtividade(){
		return this.atv;
	}
	
	public NodeAtividade getNext(){
		return this.next;
	}

}
