package com.example.bolaodasortefacil.model;

import java.io.Serializable;
import java.util.Date;

public class Aposta implements Serializable, Comparable<Aposta> {

    private int maquina;
    private String concurso;
    private String vendedor;
    private String nomeJogador;
    private String telefoneJogador;
    private Date data;
    private float valor;
    private String premio;
    private Dezena[] dezenas;
    private int pontos;

    public  Aposta(int maquina, String concurso, String v, String tf, float valor, String premio, Date data, int d1, int d2, int d3, int d4, int d5,
                   int d6, int d7, int d8, int d9, int d10){

        dezenas = new Dezena[10];
        this.maquina = maquina;
        this.concurso = concurso;
        this.vendedor = v;
        this.premio = premio;
        this.telefoneJogador = tf;
        this.valor = valor;
        this.data = data;
        dezenas[0] = new Dezena(d1);
        dezenas[1] = new Dezena(d2);
        dezenas[2] = new Dezena(d3);
        dezenas[3] = new Dezena(d4);
        dezenas[4] = new Dezena(d5);
        dezenas[5] = new Dezena(d6);
        dezenas[6] = new Dezena(d7);
        dezenas[7] = new Dezena(d8);
        dezenas[8] = new Dezena(d9);
        dezenas[9] = new Dezena(d10);




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

    public void setJogador(String nome){
        this.nomeJogador = nome;
    }

    public String getJogador(){
        return this.nomeJogador;
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

    public Date getData() {
        return data;
    }


    public void setData(Date data) {
        this.data = data;
    }


    public Dezena[] getDezenas() {
        return dezenas;
    }

    public int getDezenas(int i) {
        return dezenas[i].getDezena();
    }

    public void setAcertou(boolean b, int i) {
        dezenas[i].setAcertou(b);
    }

    public boolean getAcertou( int i) {
        return dezenas[i].isAcertou();
    }



    public void setDezenas(Dezena[] dezenas) {
        this.dezenas = dezenas;
    }


    public String getConcurso() {
        return concurso;
    }

    public void setConcurso(String concurso) {
        this.concurso = concurso;
    }

    public Date getdata() {
        return data;
    }



    public int getPontos() {
        return pontos;
    }


    public void setPontos(int pontos) {
        this.pontos = pontos;
    }


    @Override
    public int compareTo(Aposta aposta) {

        if (this.pontos > aposta.getPontos()) {
            return -1;
        } if (this.pontos < aposta.getPontos()) {
            return 1;
        }
        return 0;
    }


    public int getMaquina() {
        // TODO Auto-generated method stub
        return maquina;
    }




}
