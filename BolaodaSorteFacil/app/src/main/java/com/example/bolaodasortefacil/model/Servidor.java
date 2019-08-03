package com.example.bolaodasortefacil.model;
import android.os.AsyncTask;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;



public class Servidor {

    private static final String ipServidor = "192.168.1.105";
    private static final int porta = 1010;
    private Socket servidor ;
    private ObjectInputStream  ler;
    private ObjectOutputStream escrever;


    public Servidor(){}


    public void abrirConexao(){
        try {
            this.servidor = new Socket(this.ipServidor, this.porta);
            this.ler = new ObjectInputStream(this.servidor.getInputStream());
            this.escrever = new ObjectOutputStream(this.servidor.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fechaConexao() throws IOException{
    	this.escrever.close();
    	this.ler.close();
        this.servidor.close();
    }


    public void escreverParaServidor(Object o) throws IOException {
        this.escrever.flush();
        this.escrever.writeObject(o);
    }

    public Object lerDoServidor() throws ClassNotFoundException, IOException {
        return ler.readObject();
    }





}
