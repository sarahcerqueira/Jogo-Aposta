package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Servidor;

import java.io.IOException;


public class Aposta extends AppCompatActivity {

    private String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aposta);

    }

    private class Solicitacao extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public Solicitacao (){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("loginJogador");
                servidor.escreverParaServidor(voids[0]);
                resultado = (String) servidor.lerDoServidor();
                servidor.fechaConexao();

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
            }


            return null;
        }


    }

}
