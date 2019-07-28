package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bolaodasortefacil.MainActivity;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Servidor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private Button bt_login;
    private Servidor servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                servidor = new Servidor();

                servidor.abrirConexao();

                EditText user = (EditText) findViewById(R.id.username);
                EditText senha = (EditText) findViewById(R.id.senha);

                String usuario =  user.getText().toString();
                String s = senha.getText().toString();

                servidor.escreverParaServidor("1");
                servidor.escreverParaServidor(usuario);
                servidor.escreverParaServidor(s);
                s = servidor.lerDoServidor();

                if(s.equals("ok")){
                    Intent gerencimanetoActivity = new Intent(getApplicationContext(), Gerencimento.class);
                    startActivity(gerencimanetoActivity);
                }
            }
        });
    }




}
