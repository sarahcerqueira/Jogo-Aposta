package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bolaodasortefacil.CadastroJogador;
import com.example.bolaodasortefacil.MainActivity;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Jogador;
import com.example.bolaodasortefacil.model.Servidor;

import java.io.IOException;

public class LoginJogador extends AppCompatActivity {
    private EditText telefone;
    private Button entrar;
    private String resultado;
    private TextView eLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_jogador);

        this.telefone = (EditText) findViewById(R.id.ed_telefone);
        this.entrar = (Button) findViewById(R.id.bt_entrar);
        eLogin = (TextView) findViewById(R.id.tv_eCadastroMaquina);

        this.telefone.setText(null);

    }


    public void entrar(View view) {
        if (!checkTelefone()) {
            return;
        }

        resultado = null;
        Entrar e = new Entrar();
        e.execute(telefone.getText().toString());

        while (resultado == null) {
        }

        if (resultado.equals("400")) {
            Jogador.setTelefone(telefone.getText().toString());
            Intent cadJogador = new Intent(this, CadastroJogador.class);
            startActivity(cadJogador);

        } else if (resultado.equals("401")) {
            eLogin.setText("Falha ao se comunicar com o servidor. Verifique sua internet ou tente mais tarde");
            eLogin.setVisibility(View.VISIBLE);

        } else if (resultado.equals("402")) {
            eLogin.setText("Erro: 402");
            eLogin.setVisibility(View.VISIBLE);

        }else{
            eLogin.setVisibility(View.INVISIBLE);
            Jogador.setTelefone(telefone.getText().toString());
            Intent aposta = new Intent(this, ApostaActivity.class);
            startActivity(aposta);
        }

    }

    public  boolean checkTelefone(){
        String s = telefone.getText().toString().trim();

        if(s.isEmpty()){
            telefone.setError("Informe o número de telefone");
            return false;

        } else if(s.length()<12){
            telefone.setError("Número incompleto");
            return false;

        } else{
            telefone.setError(null);
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }


    private class Entrar extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public Entrar(){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("101");
                servidor.escreverParaServidor(voids[0]);
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
