package com.example.bolaodasortefacil.model;

import java.io.Serializable;

public class Dezena implements Serializable {

	private int dezena;
	private boolean acertou;

	public Dezena(int d) {
		dezena = d;
	}

	public int getDezena() {
		return dezena;
	}

	public void setDezena(int dezena) {
		this.dezena = dezena;
	}

	public boolean isAcertou() {
		return acertou;
	}

	public void setAcertou(boolean acertou) {
		this.acertou = acertou;
	}

}
