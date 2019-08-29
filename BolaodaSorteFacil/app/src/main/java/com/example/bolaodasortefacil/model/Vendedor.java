package com.example.bolaodasortefacil.model;

import java.io.Serializable;

public class Vendedor implements Serializable {
    private static String vendedor;

    public static void setVendedor(String v){
        vendedor = v;
    }

    public static String getVendedor(){
        return vendedor;
    }
}
