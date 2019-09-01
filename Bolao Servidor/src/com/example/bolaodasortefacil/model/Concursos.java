package com.example.bolaodasortefacil.model;
import java.io.Serializable;


public class Concursos implements Serializable {
	
	 	private String id;
	    private String hora_final;
	    private String data_final;
	    
	    public Concursos(String id, String h, String d){
	    	this.id = id;
	    	this.data_final = d;
	    	this.hora_final = h;
	    	
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

}
