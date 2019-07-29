package com.example.bolaodasortefacil.model;
import android.os.AsyncTask;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.net.UnknownHostException;



public class Servidor extends AsyncTask<String, Void, String> {

    private static final String ipServidor = "221.1.1.108";
    private static final int porta = 1010;
    private Socket servidor ;
    private Scanner carta;
    private PrintWriter lapis;
    private String resultado;


    public Servidor(){}

    @Override
    protected String doInBackground(String... voids) {

        try {
            this.servidor = new Socket(this.ipServidor, this.porta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream ler = new ObjectInputStream(this.servidor.getInputStream());
            try {
                this.resultado = (String)ler.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public void abrirConexao(){
        try {
            this.servidor = new Socket(this.ipServidor, this.porta);
            this.carta = new Scanner(this.servidor.getInputStream());
            this.lapis = new PrintWriter(this.servidor.getOutputStream());
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void fechaConexao() throws IOException{
    	this.lapis.close();
    	this.carta.close();
        this.servidor.close();
    }
    
    public boolean temProxLinha() {
    	return this.carta.hasNext();
    }
    
    public String lerDoServidor() {
        if(temProxLinha()){
    	return this.carta.nextLine();}

        return null;
    }
    
    public void escreverParaServidor(String mensagem) {
    	this.lapis.println(mensagem);
    }

    public String getResultador(){
        return this.resultado;
    }
    



}
