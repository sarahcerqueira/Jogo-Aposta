package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.view.Gerencimento;

public class OpcaoAdm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_adm);
    }

    public void gerenciamentoActivity(View view){
        Intent gerencimanetoActivity = new Intent(getApplicationContext(), Gerencimento.class);
        startActivity(gerencimanetoActivity);
    }

    public void loginJogadorActivity(View view){
        Intent ljogadorActivity = new Intent(getApplicationContext(), LoginJogador.class);
        startActivity(ljogadorActivity);
    }

}
