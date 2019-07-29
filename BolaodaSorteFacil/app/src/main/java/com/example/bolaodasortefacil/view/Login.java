package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.example.bolaodasortefacil.MainActivity;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Servidor;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText user;
    private EditText senha;
    private Servidor servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view){


        user = (EditText) findViewById(R.id.username);
        senha = (EditText) findViewById(R.id.senha);

        String usuario =  user.getText().toString();
        String s = senha.getText().toString();

        servidor = new Servidor();
        servidor.execute("1",usuario, s);
        String x = servidor.getResultador();


        if(x.equals("ok")){
            Intent gerencimanetoActivity = new Intent(getApplicationContext(), Gerencimento.class);
            startActivity(gerencimanetoActivity);
        }
    }



}
