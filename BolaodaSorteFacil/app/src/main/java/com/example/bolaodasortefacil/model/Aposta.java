package com.example.bolaodasortefacil.model;

import java.io.Serializable;

public class Aposta implements Serializable {

    private String concurso;
    private String vendedor;
    private String telefoneJogador;
    private float valor;
    private String premio;
    private int[] dezenas;

    public  Aposta(String concurso, String v, String tf, float valor, String premio, int d1, int d2, int d3, int d4, int d5,
                   int d6, int d7, int d8, int d9, int d10){

        this.concurso = concurso;
        this.vendedor = v;
        this.premio = premio;
        this.telefoneJogador = tf;
        this.valor = valor;
        this.dezenas = new int[10];
        this.dezenas[0] = d1;
        this.dezenas[1] = d2;
        this.dezenas[2] = d3;
        this.dezenas[3] = d4;
        this.dezenas[4] = d5;
        this.dezenas[5] = d6;
        this.dezenas[6] = d7;
        this.dezenas[7] = d8;
        this.dezenas[8] = d9;
        this.dezenas[9] = d10;


    }


    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTelefoneJogador() {
        return telefoneJogador;
    }

    public void setTelefoneJogador(String telefoneJogador) {
        this.telefoneJogador = telefoneJogador;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public int[] getDezenas() {
        return dezenas;
    }

    public void setDezenas(int[] dezenas) {
        this.dezenas = dezenas;
    }

    public String getConcurso() {
        return concurso;
    }

    public void setConcurso(String concurso) {
        this.concurso = concurso;
    }


}
