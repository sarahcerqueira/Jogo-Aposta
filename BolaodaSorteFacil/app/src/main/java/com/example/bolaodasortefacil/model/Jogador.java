package com.example.bolaodasortefacil.model;

import java.util.ArrayList;

public class Jogador {
    private int id;
    private String nome;
    private long telefone;

    public Jogador(int id, String nome, long telefone){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public void apostar(){}
}
