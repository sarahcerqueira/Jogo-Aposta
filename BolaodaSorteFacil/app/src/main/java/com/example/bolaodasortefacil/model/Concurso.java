package com.example.bolaodasortefacil.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Concurso implements Serializable{

    private String id;
    private String hora_final;
    private String data_final;
    private int[] dezena;
    private ArrayList<Aposta> apostas;

    public Concurso(String id, String h, String d){
        this.id = id;
        this.data_final = d;
        this.hora_final = h;
        apostas = new ArrayList<Aposta>();
        dezena = null;

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

    public int[] getDezena() {
        return dezena;
    }

    public void setDezena(int[] dezena) {
        this.dezena = dezena;
    }

    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(ArrayList<Aposta> apostas) {
        this.apostas = apostas;
    }

    public void addApostas(Aposta a) {
        apostas.add(a);
    }

    public int getMes() {
        Date d = new Date(data_final);

        return d.getMonth();
    }

    public int getAno() {


        return Integer.parseInt(this.data_final.substring(0,4));
    }


}