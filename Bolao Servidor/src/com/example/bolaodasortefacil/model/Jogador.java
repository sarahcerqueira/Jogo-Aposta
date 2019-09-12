package com.example.bolaodasortefacil.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Jogador implements Serializable {
    private static String telefone;
    private ArrayList<Aposta> apostas;

    public Jogador(){
        apostas = new ArrayList<Aposta>();
    }

    public static String getTelefone() {
        return telefone;
    }

    public static void setTelefone(String t) {
        telefone = t;
    }

    public void apostar(String concurso,String v, float valor,String premio, Date data, int d1, int d2, int d3, int d4, int d5,
                          int d6, int d7, int d8, int d9, int d10){

        Aposta a = new Aposta( concurso, v, telefone, valor, premio, data, d1, d2, d3, d4, d5, d6,d7, d8, d9,d10);
        apostas.add(a);
    }

    public ArrayList<Aposta> getAposta(){
        return apostas;
    }

}
