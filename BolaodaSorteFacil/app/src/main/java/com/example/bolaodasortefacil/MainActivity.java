package com.example.bolaodasortefacil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bolaodasortefacil.model.Empacotamento;
import com.example.bolaodasortefacil.model.Maquina;
import com.example.bolaodasortefacil.model.Servidor;
import com.example.bolaodasortefacil.model.Vendedor;
import com.example.bolaodasortefacil.view.CadastrarMaquina;
import com.example.bolaodasortefacil.view.LoginJogador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity  {

    private EditText user;
    private EditText senha;
    private String resultado;
    private TextView eLogin;
    private Switch gerenciamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileInputStream fin = null;
        String temp="";

        try {
            fin = openFileInput("maquina");

            int c;
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fin == null){
            Intent main = new Intent(this, CadastrarMaquina.class);
            startActivity(main);

        } else {
            Maquina.setMaquina(Integer.parseInt(temp));

        }

        user = (EditText) findViewById(R.id.ed_telefone);
        senha = (EditText) findViewById(R.id.ed_senha);
        eLogin = (TextView) findViewById(R.id.tverro_login);
        gerenciamento = (Switch) findViewById(R.id.sw_gerenciamento);

        user.setText("");
        senha.setText("");

    }

    public void login(View view) {

        if (!checkSenha() | !checkUsuario()) {
            return;
        }
        String u = user.getText().toString();
        String s = senha.getText().toString();

        Login l = new Login();
        l.execute(s, u);
        resultado = null;

        while (resultado == null) {
        }



        if (resultado.equals("400")) {
            eLogin.setText("Senha ou usuário incorreto");
            eLogin.setVisibility(View.VISIBLE);

        } else if (resultado.equals("401")) {
            eLogin.setText("Falha ao se comunicar com o servidor. Verifique sua internet ou tente mais tarde");
            eLogin.setVisibility(View.VISIBLE);

        } else if (resultado.equals("402")) {
            eLogin.setText("Erro: 402");
            eLogin.setVisibility(View.VISIBLE);


        }else {
            eLogin.setVisibility(View.INVISIBLE);
            Vendedor.setVendedor(user.getText().toString());

            if (gerenciamento.isChecked()) {
                Intent gerenciador = new Intent(this, Gerenciamento.class);
                startActivity(gerenciador);

            } else {
                Intent aposta = new Intent(this, LoginJogador.class);
                startActivity(aposta);
            }
        }
    }

    public boolean checkUsuario(){
        String u = user.getText().toString().trim();

        if(u.isEmpty()){
            user.setError("Informe o usuário");
            return false;
        }else{
            user.setError(null);
        }

        return true;
    }

    public boolean checkSenha(){
        String s = senha.getText().toString().trim();

        if(s.isEmpty()){
            senha.setError("Informe a senha");
            return false;
        }else{ senha.setError(null);
        }

        return true;
    }


    private class Login extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public Login(){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("100");
                servidor.escreverParaServidor(voids[0]);
                servidor.escreverParaServidor(voids[1]);
                resultado = (String) servidor.lerDoServidor();
                servidor.fechaConexao();

            } catch (IOException e)  {
                resultado = "401";

            } catch(ClassNotFoundException e){
                resultado = "402";

            }

            return null;
        }


    }
}
