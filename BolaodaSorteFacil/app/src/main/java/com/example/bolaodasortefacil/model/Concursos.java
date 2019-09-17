package com.example.bolaodasortefacil.model;
import java.io.Serializable;

public class Concursos implements Serializable {

	private String hora_final;
	private String data_final;

	public Concursos( String h, String d){
		this.data_final = d;
		this.hora_final = h;

	}

	public String getHora_final() {
		return hora_final;
	}

	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}

	public String getData_final() {
		return data_final;
	}

	public void setData_final(String data_final) {
		this.data_final = data_final;
	}

	public int getAno() {


		return Integer.parseInt(this.data_final.substring(0,4));
	}


}

