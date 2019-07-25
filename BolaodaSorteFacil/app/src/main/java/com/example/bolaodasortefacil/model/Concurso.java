package com.example.bolaodasortefacil.model;
import java.util.ArrayList;
import java.util.Date;

public class Concurso {
    private int id;
    private Date dataInicial;
    private int horaInicial;
    private Date dataFinal;
    private int horaFinal;
    private boolean aberto;
    private Dezena dezena;
    private ArrayList<Aposta> apostas;

    public Concurso(){}
}
