package com.example.bolaodasortefacil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolaodasortefacil.view.Aposta;
import com.example.bolaodasortefacil.model.Servidor;
import com.example.bolaodasortefacil.view.LoginJogador;

import java.io.IOException;


public class MainActivity extends AppCompatActivity  {

    private EditText user;
    private EditText senha;
    private String resultado;
    private TextView eLogin;
    private boolean acabou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.ed_telefone);
        senha = (EditText) findViewById(R.id.ed_senha);
        eLogin = (TextView) findViewById(R.id.tverro_login);


    }

    public void login(View view) {

        if (!checkSenha() | !checkUsuario()) {
            return;
        }
        acabou = false;
        String u = user.getText().toString();
        String s = senha.getText().toString();

        Login l = new Login();
        l.execute(s, u);

        while (!acabou) {
        }

        System.out.println(l.getStatus());

        if (resultado.equals("erro")) {
            eLogin.setText("Senha ou usuário incorreto");
            eLogin.setVisibility(View.VISIBLE);

        } else if(resultado.equals("401")){
            eLogin.setText("Falha ao se comunicar com o servidor. Verifique sua internet ou tente mais tarde");
            eLogin.setVisibility(View.VISIBLE);

        }   else{
            eLogin.setVisibility(View.INVISIBLE);

            Intent aposta = new Intent(this, LoginJogador.class);
            startActivity(aposta);
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

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


    private class Login extends AsyncTask<String, Integer, String> {

        private Servidor servidor;

        public Login(){}


        @Override
        protected String doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("login");
                servidor.escreverParaServidor(voids[0]);
                servidor.escreverParaServidor(voids[1]);
                resultado = (String) servidor.lerDoServidor();
                servidor.fechaConexao();

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
            }

            acabou = true;
            return resultado;
        }


    }
}
