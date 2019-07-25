package com.example.bolaodasortefacil.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Dezena {
    private int id;
    private int[] dezenas;

    public Dezena(int id, int d1, int d2, int d3, int d4, int d5, int d6, int d7, int d8, int d9, int d10){
        this.id = id;
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
        Arrays.sort(dezenas);

    }

    public int getId() {
        return id;
    }

    public int[] getDezenas() {
        return dezenas;
    }
}
