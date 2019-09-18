package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bolaodasortefacil.MainActivity;
import  com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Empacotamento;
import com.example.bolaodasortefacil.model.Maquina;
import com.example.bolaodasortefacil.model.Servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CadastrarMaquina extends AppCompatActivity {

    private EditText numero_maquina;
    private Button cadastrar;
    private TextView erro;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_maquina);

        numero_maquina =(EditText) findViewById(R.id.ed_maquina);
        cadastrar = (Button) findViewById(R.id.bt_cadastrarMaquina);
        erro = (TextView) findViewById(R.id.tv_eCadastroMaquina);
    }

    public void cadastrarMaquina(View view){

        if(checkNumeroMaquinaVazio()){
            numero_maquina.setError("Informe o número da máquina");

        }else{
            numero_maquina.setError(null);
            Cadastro cad = new Cadastro();
            resultado = null;
            cad.execute( numero_maquina.getText().toString());

            while(resultado == null){}

            if(resultado.equals("403")){
                erro.setText("Máquina já está cadastrada. Verifique o número e tente novamente.");
                erro.setVisibility(View.VISIBLE);

            } else if (resultado.equals("401")){
                erro.setText("Falha ao se comunicar com o servidor. Verifique sua internet ou tente mais tarde");
                erro.setVisibility(View.VISIBLE);

            } else if(resultado.equals("402")){
                erro.setText("Erro 402");
                erro.setVisibility(View.VISIBLE);

            } else {
                Maquina.setMaquina(Integer.parseInt(numero_maquina.getText().toString()));
                Integer m = Maquina.getMaquina();

                FileOutputStream fOut = null;
                try {
                    fOut = openFileOutput("maquina",MODE_WORLD_READABLE);
                    fOut.write(m.toString().getBytes());
                    fOut.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);

            }




        }

    }

    private boolean checkNumeroMaquinaVazio(){

        return numero_maquina.getText().toString().trim().isEmpty();
    }

    private class Cadastro extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public Cadastro(){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("204");
                servidor.escreverParaServidor(voids[0]);
                resultado = (String) servidor.lerDoServidor();
                servidor.fechaConexao();

                if(resultado.equals("0")){
                    resultado = "403";
                }

            } catch (IOException e)  {
                resultado = "401";

            } catch(ClassNotFoundException e){
                resultado = "402";

            }


            return null;
        }


    }
}
