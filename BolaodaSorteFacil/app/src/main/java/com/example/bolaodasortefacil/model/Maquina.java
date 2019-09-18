package com.example.bolaodasortefacil.model;

import java.io.Serializable;

public class Maquina implements Serializable {
    private static int maquina;

    public static int getMaquina(){
        return maquina;
    }

    public static void setMaquina(int m){
        maquina = m;
    }


}
