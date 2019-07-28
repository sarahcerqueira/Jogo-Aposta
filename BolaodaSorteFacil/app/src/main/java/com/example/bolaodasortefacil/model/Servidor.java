package com.example.bolaodasortefacil.model;
import java.net.Socket;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;
import java.net.UnknownHostException;



public class Servidor {
    private static final String ipServidor = "192.168.1.106";
    private static final int porta = 1010;
    private Socket servidor ;
    private Scanner carta;
    private PrintStream lapis;


    public Servidor(){}

    public void abrirConexao(){
        try {
            this.servidor = new Socket(this.ipServidor, this.porta);
            this.carta = new Scanner(this.servidor.getInputStream());
            this.lapis = new PrintStream(this.servidor.getOutputStream());
            
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
    	return this.carta.nextLine();
    }
    
    public void escreverParaServidor(String mensagem) {
    	this.lapis.println(mensagem);
    }
    



}
