package com.projetopiloto.plotadordehoras.util;

public class Hora {
	private String hora;
	private String minutos;
	
	public Hora(String hora, String minutos) {
		setHora(hora);
		setMinutos(minutos);
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}
	
	@Override
	public String toString(){
		return hora + ":" + minutos;
	}
}
