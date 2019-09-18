package com.example.bolaodasortefacil.model;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Empacotamento {

    // serialização: gravando o objetos no arquivo binário "nomeArq"

    public static void gravarArquivoBinario(Object objeto, String nomeArq) {
        File arq = new File(nomeArq);
        try {
            if(arq.exists()){
            arq.delete();}

            arq.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(objeto);
            objOutput.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    // desserialização: recuperando os objetos gravados no arquivo binário "nomeArq"
    public static Object lerArquivoBinario(String nomeArq) {
        Object objeto = null;
        try {
            File arq = new File(Environment.getExternalStorageState() +"/"+ nomeArq);

            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                objeto = objInput.readObject();
                objInput.close();

            }
        } catch(IOException erro1) {
            System.out.printf("Erro: %s", erro1.getMessage());
        } catch(ClassNotFoundException erro2) {
            System.out.printf("Erro: %s", erro2.getMessage());
        }

        return objeto;
    }

}